package com.uet.ot_server.model;

public class ChatMessage {
    private final String userId;
    private final String message;
    private final String timestamp;

    public ChatMessage(String userId, String message, String timestamp) {
        this.userId = userId;
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getUserId() {
        return userId;
    }

    public String getMessage() {
        return message;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
