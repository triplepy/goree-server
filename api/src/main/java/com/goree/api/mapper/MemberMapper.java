package com.goree.api.mapper;

import com.goree.api.domain.Member;

import java.util.List;

public interface MemberMapper {

    void insertMember(Member member);

    Member selectMemberByEmail(String email);

    List<Member> selectMemberAll();

    void deleteMemberById(long id);

    void insertMemberHasTag(long memberId, long tagId);

    Member selectMemberById(long id);
}
