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
@SpringApplicationConfiguration(classes = {Application.class})
@WebAppConfiguration
public class AuthTokenRegisterInterceptorTest {
    @Autowired
    private WebApplicationContext webAppContext;

    @Autowired
    private FacebookSettings settings;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
    }

    @Test
    public void registerAuthToken() throws Exception {
        // given
        AuthTokenContext.token(null);
        assertThat(AuthTokenContext.token(), is(nullValue()));

        // when
        mockMvc.perform(get("/auth/user").header("AuthToken", settings.longLivedTokenForTest()))
                .andExpect(status().isOk());
        // then
        assertThat(AuthTokenContext.token(), is(not(nullValue())));
    }
}
