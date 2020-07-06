package com.uet.ot_server.controller;

import com.google.gson.Gson;
import com.uet.ot_server.model.ChatMessage;
import com.uet.ot_server.model.JsonResponseMessage;
import com.uet.ot_server.model.OperationMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import java.util.concurrent.TimeUnit;

import static java.lang.String.format;

@Controller
public class MessageController {
    @Autowired
    private SimpMessageSendingOperations sendingOperations;
    @Autowired
    private Gson gson;

    @MessageMapping("/message/{id}")
    public void sendMessage(@DestinationVariable String id, @Payload ChatMessage message) {
        System.out.println(message.getUserId() + " (" + message.getTimestamp() + "): " + message.getMessage());
        sendingOperations.convertAndSend(format("/topic/%s.public", id),
                new JsonResponseMessage("chat", gson.toJson(message)));
    }

    @MessageMapping("/operation/{id}")
    public void sendOperation(@DestinationVariable String id, @Payload OperationMessage operationMessage) {
//        Operation operation;
        System.out.println("operation: " + operationMessage.getOperationJson());
        System.out.println("documentState: " + operationMessage.getDocumentStateString());
        if (operationMessage.getDelay() > 0) {
            System.out.println("delaying for: " + operationMessage.getDelay() + "ms");
            try {
                TimeUnit.MILLISECONDS.sleep(operationMessage.getDelay());
            } catch (Exception ignored) {

            }
        }
        System.out.println("============================================================");
//        switch (operationMessage.getType()) {
//            case "insert":
//                operation = gson.fromJson(operationMessage.getOperationJson(), Insert.class);
//                break;
//            case "update":
//                operation = gson.fromJson(operationMessage.getOperationJson(), Update.class);
//                break;
//            case "delete":
//                operation = gson.fromJson(operationMessage.getOperationJson(), Delete.class);
//                break;
//            default:
//                return;
//        }
//        System.out.println(operation.getUserId() + " (" + operation.getTimestamp() + "): " + operation.getClass().getSimpleName());
        sendingOperations.convertAndSend(format("/topic/%s.public", id),
                new JsonResponseMessage("operation", gson.toJson(operationMessage)));
    }
}
