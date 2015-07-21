package com.goree.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.goree.api.domain.Member;
import com.goree.api.service.MemberService;

@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberService memberService;
    
    @RequestMapping(value="", method=RequestMethod.POST)
    public Member registerMember(Member member) {
        return memberService.registerMember(member);
    }

    @RequestMapping(value="", method=RequestMethod.GET)
    public List<Member> findMemberAll() {
        return memberService.findMemberAll();
    }

    @RequestMapping(value="/id/{id}", method=RequestMethod.DELETE)
    public void deleteMemberById(@PathVariable int id) {
        memberService.deleteMemberById(id);
    }
}
