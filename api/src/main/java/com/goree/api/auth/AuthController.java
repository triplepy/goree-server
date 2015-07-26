package com.goree.api.auth;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @RequestMapping(value = "short-lived-token/{token}", method = RequestMethod.GET)
    public String shortLivedToken(@PathVariable String token) {
        return token;
    }
}
