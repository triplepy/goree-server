package com.goree.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goree.api.domain.Member;
import com.goree.api.mapper.MemberMapper;

@Service
@Transactional
public class MemberService {
    @Autowired
    private MemberMapper memberMapper;
    
    public Member registerMember(Member member) {
        memberMapper.insertMember(member);
        Member registered = memberMapper.selectMemberByEmail(member.getEmail());
        return registered;
    }

    public List<Member> findMemberAll() {
        return memberMapper.selectMemberAll();
    }

    public void deleteMemberById(int id) {
        memberMapper.deleteMemberById(id);
    }

}
