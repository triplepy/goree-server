package com.goree.api.auth;

import com.goree.api.Application;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * Created by 원영 on 2015-08-01.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes={Application.class})
@WebAppConfiguration
public class FacebookClientFactoryTest {
    @Autowired
    private FacebookClientFactory facebookClientFactory;
    @Autowired
    private FacebookSettings settings;

    @Test
    public void factoryCreatesClientInstanceSuccessfully() {
        assertThat(facebookClientFactory, is(not(nullValue())));
        FacebookClient facebookClient = facebookClientFactory.createFacebookClient();
        assertThat(facebookClient, is(not(nullValue())));
        assertThat(facebookClient, is(instanceOf(DefaultFacebookClient.class)));
    }
}
