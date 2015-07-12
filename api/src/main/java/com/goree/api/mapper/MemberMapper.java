package com.goree.api.mapper;

import com.goree.api.domain.Member;

public interface MemberMapper {

    int insertMember(Member member);

    Member selectMemberByEmail(String email);

}
