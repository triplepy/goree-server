package com.goree.api.auth;

import com.restfb.types.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FacebookController {
    @Autowired
    private FacebookService facebookService;

    @RequestMapping(value = "/auth/user", method = RequestMethod.GET)
    public User userProfile() {
        return facebookService.retrieveUserProfile();
    }
}
