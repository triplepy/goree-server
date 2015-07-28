package com.goree.api.auth;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import com.restfb.types.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FacebookController {
    @Autowired
    private FacebookSettings settings;

    @RequestMapping(value = "user_profile", method = RequestMethod.GET)
    public User userProfile() {
        // TODO currently not work.
        FacebookClient fbClient = new DefaultFacebookClient(AuthTokenContext.token(), settings.appSecret(), Version.VERSION_2_4);
        return fbClient.fetchObject("me", User.class);
    }
}
