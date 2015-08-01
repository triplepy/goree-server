package com.goree.api.auth;

import com.goree.api.domain.Member;
import com.goree.api.service.MemberService;
import com.goree.api.util.TestWithDBUnit;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * Created by 원영 on 2015-08-01.
 */
public class AuthServiceTest extends TestWithDBUnit {
    @Autowired
    private AuthService authService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private FacebookSettings settings;

    private Member testMember;

    @Before
    public void setUp() {
        super.setUp();
        AuthContext.token(settings.longLivedTokenForTest());
        testMember = memberService.findMemberById(1L);
    }

    @Override
    public String getDatasetFilePath() {
        return "src/test/resources/testdataset/auth_test_setup.xml";
    }

    @Test
    public void findMemberLoggedIn() {
        Member memberLoggedIn = authService.findMemberLoggedIn();

        assertThat(memberLoggedIn, is(not(nullValue())));
        assertThat(memberLoggedIn.getId(), is(testMember.getId()));
        assertThat(memberLoggedIn.getEmail(), is(testMember.getEmail()));
    }
}
