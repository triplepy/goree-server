package com.goree.api.auth;

import com.goree.api.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by 원영 on 2015-07-28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes={Application.class})
@WebAppConfiguration
public class AuthTokenContextTest {

    private static final String TEST_TOKEN = "test_token";

    @Test
    public void setAndGet() {
        String before = AuthTokenContext.token();
        assertThat(before, is(nullValue()));

        AuthTokenContext.token(TEST_TOKEN);

        String after = AuthTokenContext.token();
        assertThat(after, is(TEST_TOKEN));
    }
}
