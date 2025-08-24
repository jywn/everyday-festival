package com.festival.everyday.core.common.dummy;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;

public class FileSystemMultipartFile implements MultipartFile {
    private final File file;
    private final String name;              // form field name (예: "image")
    private final String originalFilename;  // 원본 파일명
    private final String contentType;       // image/png, image/jpeg 등

    public FileSystemMultipartFile(File file, String name, String originalFilename, String contentType) {
        this.file = file;
        this.name = name;
        this.originalFilename = originalFilename;
        this.contentType = contentType;
    }

    @Override public String getName() { return name; }
    @Override public String getOriginalFilename() { return originalFilename; }
    @Override public String getContentType() { return contentType; }
    @Override public boolean isEmpty() { return file.length() == 0; }
    @Override public long getSize() { return file.length(); }
    @Override public byte[] getBytes() throws IOException { return Files.readAllBytes(file.toPath()); }
    @Override public InputStream getInputStream() throws IOException { return new FileInputStream(file); }
    @Override public void transferTo(File dest) throws IOException { Files.copy(file.toPath(), dest.toPath()); }
}

