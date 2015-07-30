package com.goree.api.auth;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import com.restfb.types.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 원영 on 2015-07-30.
 */
@Service
public class FacebookService {
    @Autowired
    private FacebookSettings settings;

    public User retrieveUserProfile() {
        String longLivedToken = AuthTokenContext.token();
        FacebookClient fbClient = new DefaultFacebookClient(longLivedToken, settings.appSecret(), Version.VERSION_2_4);
        return fbClient.fetchObject("me", User.class);
    }
}
