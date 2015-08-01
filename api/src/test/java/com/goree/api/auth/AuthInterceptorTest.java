package com.goree.api.auth;

import com.goree.api.domain.Member;
import com.goree.api.util.TestWithDBUnit;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
@WebAppConfiguration
public class AuthInterceptorTest extends TestWithDBUnit {
    @Autowired
    private WebApplicationContext webAppContext;

    @Autowired
    private FacebookSettings settings;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        super.setUp();
        mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();

        AuthContext.token(null);
        assertThat(AuthContext.token(), is(nullValue()));

        httpRequestToController();
    }

    private void httpRequestToController() {
        try {
            mockMvc.perform(get("/auth/user").header("AuthToken", settings.longLivedTokenForTest()))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getDatasetFilePath() {
        return "src/test/resources/testdataset/auth_test_setup.xml";
    }

    @Test
    public void registerAuthToken() throws Exception {
        // then
        assertThat(AuthContext.token(), is(not(nullValue())));
    }

    @Test
    public void registerMemberInfo() throws Exception {
        // when
        Member member = AuthContext.memberInfo();

        // then
        assertThat(member, is(not(nullValue())));
        assertThat(member.getEmail(), is("rpxhdnjsdud@nate.com"));
    }
}
