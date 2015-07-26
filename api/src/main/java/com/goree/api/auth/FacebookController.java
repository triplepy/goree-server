package com.goree.api.auth;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import com.restfb.types.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FacebookController {
    @RequestMapping(value = "short-lived-token/{shorLivedToken}", method = RequestMethod.GET)
    public String shortLivedToken(@PathVariable String shorLivedToken) {
        String appId = "878498855553723";
        String appSecret = "13d264f3ac0c7559c651afab6d544289";

        FacebookClient.AccessToken accessToken = new DefaultFacebookClient(Version.VERSION_2_4)
                .obtainExtendedAccessToken(appId, appSecret, shorLivedToken);

        String longLivedToken = accessToken.getAccessToken();
        TokenContext.longLivedToken(longLivedToken);

        return longLivedToken;
    }

    @RequestMapping(value = "user_profile", method = RequestMethod.GET)
    public User userProfile() {
        String appId = "878498855553723";
        String appSecret = "13d264f3ac0c7559c651afab6d544289";
        FacebookClient fbClient = new DefaultFacebookClient(TokenContext.longLivedToken(), appSecret, Version.VERSION_2_4);
        String appSecretProof = fbClient.obtainAppSecretProof(TokenContext.longLivedToken(), appSecret);
        return fbClient.fetchObject("me", User.class);
    }
}
