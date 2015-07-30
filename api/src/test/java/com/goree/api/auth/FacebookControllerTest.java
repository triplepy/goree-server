package com.goree.api.auth;

import com.goree.api.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by 원영 on 2015-07-30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes={Application.class})
@WebAppConfiguration
public class FacebookControllerTest {
    @Autowired
    private FacebookService facebookService;

    @Autowired
    private WebApplicationContext webAppContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
    }

    @Test
    public void retrieveUserProfile() throws Exception {
        // given
        String expectedUserId = "1434948110166610";
        AuthTokenContext.token(null);
        assertThat(AuthTokenContext.token(), is(nullValue()));
        String longLivedToken = "CAAMeZCXQ4irsBAB9febXPNBWBmJUYMqZAJ7aXKraZCf1OcqDJ98JvGpOxZBS0FGZBFVOPXVbiXbxxvxfXZA4z9Kw5xsacuSGufDBsYYMbIhazZB0Iv7cuGu8eZBuJ0I04PAJMFjGtgjIIAB9roaObJv00h8jS7QjPzQyYnXhEq4eaWaKRhkNzdQZBjAL71nnLAckZD";

        // when then
        mockMvc.perform(get("/auth/user").header("AuthToken", longLivedToken))
                .andExpect(jsonPath("$.id", is(expectedUserId)));
    }
}
