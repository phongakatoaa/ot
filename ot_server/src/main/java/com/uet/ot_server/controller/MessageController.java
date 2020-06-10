package com.uet.ot_server.controller;

import com.google.gson.Gson;
import com.uet.ot_server.model.OTMessage;
import com.uet.ot_server.model.ResponseMessage;
import com.uet.ot_server.service.OTMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import static java.lang.String.format;

@Controller
public class MessageController {
    @Autowired
    private SimpMessageSendingOperations sendingOperations;
    @Autowired
    private OTMessageService otMessageService;

    private final Gson gson = new Gson();

    @MessageMapping("/message/{id}")
    public void sendMessage(@DestinationVariable String id, @Payload OTMessage message) {
        System.out.println("Id: " + id);
        System.out.println("Operation: " + message.getOtOperation());
        sendingOperations.convertAndSend(format("/topic/%s.public", id), new ResponseMessage() {{
            setMessage("Received");
        }});
    }
}
