package com.goree.api.auth;

import com.goree.api.Application;
import com.restfb.types.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * Created by 원영 on 2015-07-30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes={Application.class})
public class FacebookServiceTest {
    @Autowired
    private FacebookService facebookService;

    @Test
    public void retrieveUserProfile() {
        // given
        String expectedUserId = "1434948110166610";

        String longLivedToken = "CAAMeZCXQ4irsBAB9febXPNBWBmJUYMqZAJ7aXKraZCf1OcqDJ98JvGpOxZBS0FGZBFVOPXVbiXbxxvxfXZA4z9Kw5xsacuSGufDBsYYMbIhazZB0Iv7cuGu8eZBuJ0I04PAJMFjGtgjIIAB9roaObJv00h8jS7QjPzQyYnXhEq4eaWaKRhkNzdQZBjAL71nnLAckZD";
        AuthTokenContext.token(longLivedToken);

        // when
        User user = facebookService.retrieveUserProfile();

        // then
        assertThat(user.getId(), is(expectedUserId));
    }
}
