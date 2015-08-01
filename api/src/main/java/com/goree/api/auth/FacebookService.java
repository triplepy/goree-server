package com.goree.api.auth;

import com.restfb.FacebookClient;
import com.restfb.types.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 원영 on 2015-07-30.
 */
@Service
public class FacebookService {
    @Autowired
    private FacebookClientFactory facebookClientFactory;

    public User retrieveUserProfile() {
        FacebookClient fbClient = facebookClientFactory.createFacebookClient();
        return fbClient.fetchObject("me", User.class);
    }
}
