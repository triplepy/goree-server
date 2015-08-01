package com.goree.api.controller;

import com.goree.api.domain.Member;
import com.goree.api.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberService memberService;
    
    @RequestMapping(value="", method=RequestMethod.POST)
    public Member registerMember(@RequestBody Member member) {
        return memberService.registerMember(member);
    }

    @RequestMapping(value="", method=RequestMethod.GET)
    public List<Member> findMemberAll() {
        return memberService.findMemberAll();
    }

    @RequestMapping(value="/id/{id}", method=RequestMethod.DELETE)
    public void deleteMemberById(@PathVariable long id) {
        memberService.deleteMemberById(id);
    }

    @RequestMapping(value="/id/{id}", method=RequestMethod.GET)
    public Member findMemberById(@PathVariable long id) {
        return memberService.findMemberById(id);
    }
}
