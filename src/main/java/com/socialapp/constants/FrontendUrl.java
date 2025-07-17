package com.socialapp.constants;

import lombok.Getter;

@Getter
public enum FrontendUrl {
    LOCALHOST("http://localhost:8081");
//    PRODUCTION(" ")

    private final String url;

    FrontendUrl(String url) {
        this.url = url;
    }

//    public String getUrl() {
//        return url;
//    }
}
