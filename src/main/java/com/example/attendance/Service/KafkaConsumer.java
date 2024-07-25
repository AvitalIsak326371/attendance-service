package com.example.attendance.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @Autowired
    private SimpMessagingTemplate template;

    @KafkaListener(topics = "lesson_start", groupId = "attendance-group")
    public void listen(String message) {
        System.out.println("Received Message: " + message);
        template.convertAndSend("/topic/socket_lesson_start", message);
    }

    @KafkaListener(topics = "lesson_end", groupId = "attendance-group")
    public void listenLessonEnd(String message) {
        System.out.println("Received lesson end message: " + message);
        template.convertAndSend("/topic/socket_lesson_end", message);
    }

}
