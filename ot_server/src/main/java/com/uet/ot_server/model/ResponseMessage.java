package com.uet.ot_server.model;

public class ResponseMessage {
    private String channelName;
    private String downloadPath;

    public ResponseMessage(String channelName, String downloadPath) {
        this.channelName = channelName;
        this.downloadPath = downloadPath;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getDownloadPath() {
        return downloadPath;
    }

    public void setDownloadPath(String downloadPath) {
        this.downloadPath = downloadPath;
    }
}
