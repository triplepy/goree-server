package com.goree.api.auth;

import com.goree.api.Application;
import com.goree.api.domain.Member;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * Created by 원영 on 2015-07-28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes={Application.class})
@WebAppConfiguration
public class AuthContextTest {

    private static final String TEST_TOKEN = "test_token";

    @Before
    public void setUp() {
        AuthContext.token(null);
        AuthContext.memberInfo(null);
    }

    @Test
    public void token() {
        String before = AuthContext.token();
        assertThat(before, is(nullValue()));

        AuthContext.token(TEST_TOKEN);

        String after = AuthContext.token();
        assertThat(after, is(TEST_TOKEN));
    }

    @Test
    public void memberInfo() {
        // given
        Member before = AuthContext.memberInfo();
        assertThat(before, is(nullValue()));

        Member member = new Member();
        member.setId(1);
        member.setEmail("rpxhdnjsdud@nate.com");

        // when
        AuthContext.memberInfo(member);
        Member after = AuthContext.memberInfo();

        // then
        assertThat(after, is(not(nullValue())));
        assertThat(after.getId(), is(member.getId()));
        assertThat(after.getEmail(), is(member.getEmail()));
    }

    @After
    public void tearDown() {
        AuthContext.token(null);
        AuthContext.memberInfo(null);
    }
}
