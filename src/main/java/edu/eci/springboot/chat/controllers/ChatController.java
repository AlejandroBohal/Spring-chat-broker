package edu.eci.springboot.chat.controllers;

import edu.eci.springboot.chat.models.service.IChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import edu.eci.springboot.chat.models.documents.Message;

import java.util.Date;
import java.util.Random;

@Controller
public class ChatController {
    private String[] colors = {"red","green","blue","magenta","purple","orange"};

    @Autowired
    private IChatService chatService;

    @Autowired
    private SimpMessagingTemplate webSocket;

    @MessageMapping("/message")
    @SendTo("/chat/message")
    public Message recibirMensaje( Message message){
        message.setDate(new Date().getTime());
        if (message.getType().equals("NEW_USER")){
            message.setColor(colors[new Random().nextInt(colors.length)]);
            message.setText( "se ha conectado.");
        }else{
            chatService.save(message);
        }
        return message;
    }
    @MessageMapping("/writing")
    @SendTo("/chat/writing")
    public String isWriting(String username){
        return username + (" está escribiendo...");
    }
    @MessageMapping("/history")

    public void history(String clienteId){
        webSocket.convertAndSend("/chat/history/"+clienteId,chatService.getLast10());

    }



}
