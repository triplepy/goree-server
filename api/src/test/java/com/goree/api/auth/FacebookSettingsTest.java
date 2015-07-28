package com.goree.api.auth;

import com.goree.api.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by 원영 on 2015-07-28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes={Application.class})
@WebAppConfiguration
public class FacebookSettingsTest {
    @Autowired
    private FacebookSettings settings;

    @Test
    public void retrieveSettings() {
        String appId = settings.appId();
        String appSecret = settings.appSecret();

        assertThat(appId, is("878498855553723"));
        assertThat(appSecret, is("13d264f3ac0c7559c651afab6d544289"));
    }
}
