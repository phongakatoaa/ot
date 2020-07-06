package com.uet.ot_server.model;

public class JsonResponseMessage {
    private final String type;
    private final String jsonMessage;

    public JsonResponseMessage(String type, String jsonMessage) {
        this.type = type;
        this.jsonMessage = jsonMessage;
    }

    public String getType() {
        return type;
    }

    public String getJsonMessage() {
        return jsonMessage;
    }
}
