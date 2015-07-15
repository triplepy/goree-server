package com.goree.api.mapper;

import java.util.List;

import com.goree.api.domain.Member;

public interface MemberMapper {

    int insertMember(Member member);

    Member selectMemberByEmail(String email);

    List<Member> selectMemberAll();

    void deleteMemberById(int id);

    void insertMemberHasTag(int memberId, int tagId);
}
