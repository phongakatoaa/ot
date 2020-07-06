package com.uet.websocket;

public class JsonResponseMessage {
    private String type;
    private String jsonMessage;

    public JsonResponseMessage() {

    }

    public JsonResponseMessage(String type, String jsonMessage) {
        this.type = type;
        this.jsonMessage = jsonMessage;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setJsonMessage(String jsonMessage) {
        this.jsonMessage = jsonMessage;
    }

    public String getType() {
        return type;
    }

    public String getJsonMessage() {
        return jsonMessage;
    }
}
