package com.uet.ot_server.model;

import org.springframework.data.annotation.Id;

public class OTFile {
    @Id
    private String _id;
    private String fileName;
    private String path;
    private boolean inEdit;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isInEdit() {
        return inEdit;
    }

    public void setInEdit(boolean inEdit) {
        this.inEdit = inEdit;
    }
}
