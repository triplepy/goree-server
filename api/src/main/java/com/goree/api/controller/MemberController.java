package com.goree.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.goree.api.domain.Member;
import com.goree.api.service.MemberService;

@RestController
public class MemberController {
    @Autowired
    private MemberService memberService;
    
    @RequestMapping(value="/member", method=RequestMethod.GET)
    public Member registerMember(Member member) {
        return memberService.registerMember(member);
    }

    public List<Member> findMemberAll() {
        return memberService.findMemberAll();
    }

    public void deleteMemberById(int id) {
        memberService.deleteMemberById(id);
    }
}
