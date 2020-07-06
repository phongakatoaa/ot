package com.uet.websocket;

public class OperationMessage {
    private String type;
    private String operationJson;
    private String documentStateString;

    public OperationMessage(String type, String operationJson, String documentStateString) {
        this.type = type;
        this.operationJson = operationJson;
        this.documentStateString = documentStateString;
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
}
