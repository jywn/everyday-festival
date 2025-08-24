package com.festival.everyday.core.common.dummy;

import com.festival.everyday.core.image.domain.OwnerType;
import com.festival.everyday.core.image.dto.common.ImageDto;
import com.festival.everyday.core.image.service.ImageCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Order(9)
@Component
@RequiredArgsConstructor
public class DummyImageRunner implements CommandLineRunner {

    private final ImageCommandService imageCommandService;

    @Value("${DUMMY_FESTIVAL_URL}")
    String folderPathFestival;

    @Value("${DUMMY_USER_URL}")
    String folderPathUser;

    @Override
    public void run(String... args) {


        for (int i = 1; i <= 30; i++) {
            uploadIfExists(folderPathFestival, i, OwnerType.FESTIVAL);
        }

        for (int i = 1; i <= 100; i++) {
            OwnerType type;
            if (i <= 60) {
                type = OwnerType.COMPANY;
            } else if (i <= 90) {
                type = OwnerType.LABOR;
            } else {
                type = OwnerType.HOLDER;
            }
            uploadIfExists(folderPathUser, i, type);
        }
    }

    /**
     * (i).png / (i).jpg / (i).jpeg 파일 있으면 업로드
     */
    private void uploadIfExists(String folderPath, int i, OwnerType ownerType) {
        Long ownerId = (long) i;

        File pngFile = new File(folderPath, "(" + i + ").png");
        File jpgFile = new File(folderPath, "(" + i + ").jpg");
        File jpegFile = new File(folderPath, "(" + i + ").jpeg");

        File targetFile = null;
        if (pngFile.exists()) {
            targetFile = pngFile;
        } else if (jpgFile.exists()) {
            targetFile = jpgFile;
        } else if (jpegFile.exists()) {
            targetFile = jpegFile;
        }

        if (targetFile == null) {
            System.out.println("[DummyImageRunner] " + ownerType + " " + i + "번 파일 없음, 스킵");
            return;
        }

        try {
            MultipartFile multipartFile = toMultipartFile(targetFile);

            Long savedId = imageCommandService.upload(
                    new ImageDto(ownerId, ownerType),
                    multipartFile
            );

            System.out.println("[DummyImageRunner] 저장 완료: ownerType=" + ownerType +
                    ", ownerId=" + ownerId +
                    ", file=" + targetFile.getName() +
                    " → id=" + savedId);

        } catch (Exception e) {
            System.err.println("[DummyImageRunner] 저장 실패: ownerType=" + ownerType +
                    ", ownerId=" + ownerId +
                    ", file=" + targetFile.getName() +
                    " → " + e.getMessage());
        }
    }

    /** File → MultipartFile (커스텀 구현체 사용) */
    private MultipartFile toMultipartFile(File file) {
        String ext = getExt(file.getName());
        String contentType = switch (ext) {
            case "png" -> "image/png";
            case "jpg", "jpeg" -> "image/jpeg";
            default -> "application/octet-stream";
        };
        return new FileSystemMultipartFile(file, "image", file.getName(), contentType);
    }

    private String getExt(String name) {
        int pos = name.lastIndexOf('.');
        return (pos == -1) ? "" : name.substring(pos + 1).toLowerCase();
    }
}
