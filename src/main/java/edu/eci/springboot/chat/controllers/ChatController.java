package edu.eci.springboot.chat.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import edu.eci.springboot.chat.models.documents.Message;

import java.util.Date;

@Controller
public class ChatController {
    @MessageMapping("/message")
    @SendTo("/chat/message")
    public Message recibirMensaje( Message message){
        message.setDate(new Date().getTime());
        message.setText("Received by broker: "+ message.getText());
        return message;
    }


}
