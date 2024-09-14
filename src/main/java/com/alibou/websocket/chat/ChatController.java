package com.alibou.websocket.chat;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;


@Controller
public class ChatController {

    @MessageMapping("/chat.sendMessage/{classroom}")
    @SendTo("/topic/{classroom}")
    public ChatMessage sendMessage(
            @Payload ChatMessage chatMessage,
            @DestinationVariable String classroom
    ) {
        // You might want to use the classroom variable here
        return chatMessage;
    }

    @MessageMapping("/chat.addUser/{classroom}")
    @SendTo("/topic/{classroom}")
    public ChatMessage addUser(
            @Payload ChatMessage chatMessage,
            @DestinationVariable String classroom,
            SimpMessageHeaderAccessor headerAccessor
    ) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }
}