package com.goree.api.auth;

import com.goree.api.domain.Member;
import com.goree.api.util.HttpHeaderConstants;
import com.goree.api.util.TestWithDBUnit;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

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

    @Autowired
    private AuthInterceptor authInterceptor;

    @Before
    public void setUp() {
        super.setUp();
        mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();

        AuthContext.token(null);
        assertThat(AuthContext.token(), is(nullValue()));
    }

    @Override
    public String getDatasetFilePath() {
        return "src/test/resources/testdataset/auth_test_setup.xml";
    }

    @Test
    public void registerToContext() throws Exception {
        // given
        MockHttpServletRequest mockRequest = new MockHttpServletRequest();
        mockRequest.addHeader(HttpHeaderConstants.AUTH_TOKEN, settings.longLivedTokenForTest());
        MockHttpServletResponse mockResponse = new MockHttpServletResponse();

        // when
        authInterceptor.preHandle(mockRequest, mockResponse, null);
        Member member = AuthContext.memberInfo();

        // then
        assertThat(AuthContext.token(), is(settings.longLivedTokenForTest()));
        assertThat(member, is(not(nullValue())));
        assertThat(member.getEmail(), is("rpxhdnjsdud@nate.com"));

        // when
        authInterceptor.postHandle(mockRequest, mockResponse, null, null);

        // then
        assertThat(AuthContext.token(), is(nullValue()));
        assertThat(AuthContext.memberInfo(), is(nullValue()));
    }
}
