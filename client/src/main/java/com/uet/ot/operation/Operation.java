package com.uet.ot.operation;

import com.uet.ot.helper.ElementFinder;
import org.jdom2.Document;

import java.util.Objects;

public abstract class Operation {
    private String userId;
    private String timestamp;
    private String type;

    public Operation() {

    }

    public Operation(String userId, String timestamp, String type) {
        this.userId = userId;
        this.timestamp = timestamp;
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operation operation = (Operation) o;
        return Objects.equals(userId, operation.userId) &&
                Objects.equals(timestamp, operation.timestamp) &&
                Objects.equals(type, operation.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, timestamp, type);
    }

    public abstract void apply(Document document, ElementFinder elementFinder);
}
