package com.nelldora.mall.file.vo;

public class TransFile {

    private String originalName;
    private String serverName;

    public TransFile(String originalName, String serverName) {
        this.originalName = originalName;
        this.serverName = serverName;
    }

    public String getOriginalName() {
        return originalName;
    }

    public String getServerName() {
        return serverName;
    }
}
