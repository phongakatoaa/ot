package com.uet.websocket;

import com.google.gson.Gson;
import com.uet.config.UserConfig;
import com.uet.ot.operation.Operation;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MySocketClient {
    private final String id;
    private final String userId;
    private final StompSession stompSession;
    private final StompSessionHandler stompSessionHandler;
    private final String url;
    private final String messageRoute;
    private final String operationRoute;
    private final Gson gson;

    public MySocketClient(String channelId, String userId, String host) throws ExecutionException, InterruptedException {
        this.id = channelId;
        this.userId = userId;
        this.url = "ws://" + host + ":8080/ot";
        this.messageRoute = "/app/message/" + id;
        this.operationRoute = "/app/operation/" + id;
        this.gson = new Gson();
        WebSocketClient client = new StandardWebSocketClient();
        WebSocketStompClient stompClient = new WebSocketStompClient(client);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        stompSessionHandler = new MyStompSessionHandler(channelId);
        stompSession = stompClient.connect(url, stompSessionHandler).get();
    }

    public void sendMessage(String message) {
        ChatMessage chatMessage = new ChatMessage(userId, message, LocalDateTime.now().toString());
        stompSession.send(messageRoute, chatMessage);
    }

    public void sendOperation(Operation operation, ArrayList<Operation> documentState) {
        String type = operation.getClass().getSimpleName().toLowerCase();
        StringBuilder dsString = new StringBuilder();
        for (Operation o : documentState) {
            dsString.append(gson.toJson(o)).append("//");
        }
        stompSession.send(operationRoute, new OperationMessage(type, gson.toJson(operation), dsString.toString(), UserConfig.getInstance().getDelay()));
    }

    public StompSessionHandler getStompSessionHandler() {
        return stompSessionHandler;
    }
}
