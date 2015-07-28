package com.goree.api.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by 원영 on 2015-07-28.
 */
@Component
public class FacebookSettings {
    @Value("${facebook.app-id}")
    private String appId;
    @Value("${facebook.app-secret}")
    private String appSecret;

    public String appId() {
        return appId;
    }

    public String appSecret() {
        return appSecret;
    }
}
