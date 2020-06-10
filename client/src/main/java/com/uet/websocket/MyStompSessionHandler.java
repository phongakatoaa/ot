package com.uet.websocket;

import com.uet.model.OTMessage;
import com.uet.model.ResponseMessage;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;

import java.lang.reflect.Type;

public class MyStompSessionHandler implements StompSessionHandler {
    @Override
    public void afterConnected(StompSession stompSession, StompHeaders stompHeaders) {
        stompSession.subscribe("/topic/1.public", this);
        stompSession.send("/app/message/1", new OTMessage() {{
            setOtOperation("insert");
            setOtFileId("1");
        }});
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
        return ResponseMessage.class;
    }

    @Override
    public void handleFrame(StompHeaders stompHeaders, Object o) {
        System.out.println("Received");
        System.out.println("Received response: " + ((ResponseMessage) o).getMessage());
    }
}
