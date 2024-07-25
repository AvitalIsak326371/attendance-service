package com.example.attendance.Controller;

// WebSocketController.java

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @MessageMapping("/hello")
    @SendTo("/topic/socket_lesson_start")
    public String greeting(String message) {
        return "Hello, " + message + "!";
    }

    @MessageMapping("/lesson_end")
    @SendTo("/topic/socket_lesson_end")
    public String endGreeting(String message) {
        return "Lesson ended, " + message + "!";
    }
}