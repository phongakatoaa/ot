package com.uet.ot_server.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class OTMessage {
    @Id
    private String _id;
    private String otOperation;
    private String otFileId;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getOtOperation() {
        return otOperation;
    }

    public void setOtOperation(String otOperation) {
        this.otOperation = otOperation;
    }

    public String getOtFileId() {
        return otFileId;
    }

    public void setOtFileId(String otFileId) {
        this.otFileId = otFileId;
    }
}
