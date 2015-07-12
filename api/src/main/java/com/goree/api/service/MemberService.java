package com.goree.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goree.api.domain.Member;
import com.goree.api.mapper.MemberMapper;

@Service
public class MemberService {
    @Autowired
    private MemberMapper memberMapper;
    
    public Member registerMember(Member member) {
        memberMapper.insertMember(member);
        Member registered = memberMapper.selectMemberByEmail(member.getEmail());
        return registered;
    }

}
