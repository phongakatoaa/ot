package com.uet.config;

public class UserConfig {
    private String userId;
    private String channelId;
    private String host;
    private static final UserConfig instance = new UserConfig();

    private UserConfig() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public static UserConfig getInstance() {
        return instance;
    }
}
