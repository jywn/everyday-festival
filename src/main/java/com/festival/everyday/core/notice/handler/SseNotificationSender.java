package com.festival.everyday.core.notice.handler;

import com.festival.everyday.core.notice.domain.Notice;
import com.festival.everyday.core.notice.dto.command.NotificationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class SseNotificationSender {

    //각 사용자마다의 고유한 연결 통로(SseEmitter)를 저장하는 명단
    private final Map<Long, SseEmitter> emitters = new ConcurrentHashMap<>();

    //새로운 사용자가 생기면 명단에 추가하고 연결 통로 개방
    public SseEmitter subscribe(Long userId) {
        SseEmitter emitter = new SseEmitter(60L * 1000);
        emitters.put(userId, emitter);

        emitter.onCompletion(() -> emitters.remove(userId));
        emitter.onTimeout(()-> emitters.remove(userId));

        sendToClient(emitter, "connected", "연결되었습니다.");

        return emitter;
    }

    //알림을 받을 사람의 Id를 찾아서, 그 사람의 연결통로로 알림데이터를 쏴줌
    public void send(NotificationDto dto) {
        SseEmitter emitter = emitters.get(dto.getReceiverId());

        if(emitter != null) {
            sendToClient(emitter,"newNotice", dto);
        }
    }

    //실제 데이터를 쏴주는 함수
    private void sendToClient(SseEmitter emitter, String eventName, Object dto) {
        try{
            emitter.send(SseEmitter.event()
                    .name(eventName)
                    .data(dto));
        }
        catch (IOException e) {
            emitters.values().remove(emitter);
        }
    }
}
