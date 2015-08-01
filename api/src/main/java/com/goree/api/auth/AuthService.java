package com.goree.api.auth;

import com.goree.api.domain.Member;
import com.goree.api.service.MemberService;
import com.restfb.types.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 원영 on 2015-08-01.
 */
@Service
public class AuthService {

    @Autowired
    private FacebookService facebookService;

    @Autowired
    private MemberService memberService;

    public Member findMemberLoggedIn() {
        User user = facebookService.retrieveUserProfile();
        Member member = memberService.findMemberByFacebookUserId(user.getId());
        return member;
    }
}
