package com.uet.ot_server.model;

public class OperationMessage {
    private String type;
    private String operationJson;
    private String documentStateString;
    private int delay;

    public OperationMessage(String type, String operationJson, String documentStateString, int delay) {
        this.type = type;
        this.operationJson = operationJson;
        this.documentStateString = documentStateString;
        this.delay = delay;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOperationJson() {
        return operationJson;
    }

    public void setOperationJson(String operationJson) {
        this.operationJson = operationJson;
    }

    public String getDocumentStateString() {
        return documentStateString;
    }

    public void setDocumentStateString(String documentStateString) {
        this.documentStateString = documentStateString;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }
}
