package com.uet.websocket;

import com.google.gson.Gson;
import com.uet.ot.UMLDocumentControl;
import com.uet.ot.operation.Delete;
import com.uet.ot.operation.Insert;
import com.uet.ot.operation.Operation;
import com.uet.ot.operation.Update;
import com.uet.parser.MyXMLParserException;
import com.uet.ui.ChatWidget;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;

import javax.swing.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;

public class MyStompSessionHandler implements StompSessionHandler {
    private final String id;
    private final Gson gson;
    private ChatWidget chatWidget;

    public MyStompSessionHandler(String id) {
        this.id = id;
        this.gson = new Gson();
    }

    @Override
    public void afterConnected(StompSession stompSession, StompHeaders stompHeaders) {
        stompSession.subscribe("/topic/" + id + ".public", this);
    }

    @Override
    public void handleException(StompSession stompSession, StompCommand stompCommand, StompHeaders stompHeaders, byte[] bytes, Throwable throwable) {
        System.out.println(throwable.getMessage());
    }

    @Override
    public void handleTransportError(StompSession stompSession, Throwable throwable) {
        System.out.println("transport error");
    }

    @Override
    public Type getPayloadType(StompHeaders stompHeaders) {
        //stompHeaders.keySet().forEach(k -> System.out.println(k + ": " + stompHeaders.get(k)));
        return JsonResponseMessage.class;
    }

    @Override
    public void handleFrame(StompHeaders stompHeaders, Object o) {
        JsonResponseMessage responseMessage = (JsonResponseMessage) o;
        if (responseMessage.getType().equals("chat")) {
            ChatMessage chatMessage = gson.fromJson(responseMessage.getJsonMessage(), ChatMessage.class);
            System.out.println(chatMessage.getUserId() + " (" + chatMessage.getTimestamp() + "): " + chatMessage.getMessage());
            if (chatWidget != null) {
                chatWidget.appendMessage(chatMessage);
            }
        } else if (responseMessage.getType().equals("operation")) {
            OperationMessage operationMessage = gson.fromJson(responseMessage.getJsonMessage(), OperationMessage.class);
            Operation operation;
            switch (operationMessage.getType()) {
                case "insert":
                    operation = gson.fromJson(operationMessage.getOperationJson(), Insert.class);
                    break;
                case "update":
                    operation = gson.fromJson(operationMessage.getOperationJson(), Update.class);
                    break;
                case "delete":
                    operation = gson.fromJson(operationMessage.getOperationJson(), Delete.class);
                    break;
                default:
                    return;
            }
            System.out.println(operation);
            final ArrayList<Operation> ds = new ArrayList<>();
            if (!operationMessage.getDocumentStateString().isEmpty()) {
                Arrays.stream(operationMessage.getDocumentStateString().split("//")).forEach(s -> {
                    if (s.contains("insert")) {
                        ds.add(gson.fromJson(s, Insert.class));
                    } else if (s.contains("update")) {
                        ds.add(gson.fromJson(s, Update.class));
                    } else if (s.contains("delete")) {
                        ds.add(gson.fromJson(s, Delete.class));
                    }
                });
            }
            //System.out.println(operation.getUserId() + " (" + operation.getTimestamp() + "): " + operation.getClass().getSimpleName());
            try {
                UMLDocumentControl.getInstance().applyRemote(operation, ds);
            } catch (MyXMLParserException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        } else {
            System.out.println("Undefined message type.");
        }
    }

    public ChatWidget getChatWidget() {
        return chatWidget;
    }

    public void setChatWidget(ChatWidget chatWidget) {
        this.chatWidget = chatWidget;
    }
}
