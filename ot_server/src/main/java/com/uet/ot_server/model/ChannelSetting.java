package com.uet.ot_server.model;

public class ChannelSetting {
    private String channelName;
    private String downloadPath;

    public ChannelSetting() {

    }

    public ChannelSetting(String channelName, String downloadPath) {
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
