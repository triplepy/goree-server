package com.goree.api.auth;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by 원영 on 2015-08-01.
 */
@Component
public class FacebookClientFactory {
    @Autowired
    private FacebookSettings settings;

    private FacebookClient usingTokenAs(String token) {
        String appSecret = settings.appSecret();
        return new DefaultFacebookClient(token, appSecret, Version.VERSION_2_4);
    }

    public FacebookClient createFacebookClient() {
        return usingTokenAs(AuthContext.token());
    }
}
