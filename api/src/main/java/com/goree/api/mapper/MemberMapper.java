package com.goree.api.mapper;

import com.goree.api.domain.Member;

import java.util.List;

public interface MemberMapper {

    int insertMember(Member member);

    Member selectMemberByEmail(String email);

    List<Member> selectMemberAll();

    void deleteMemberById(int id);

    void insertMemberHasTag(int memberId, int tagId);

    Member selectMemberById(int id);
}
