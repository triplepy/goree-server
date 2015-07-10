package com.goree.api.mapper;

import java.util.List;

import com.goree.api.domain.Group;
import com.goree.api.domain.Member;

public interface GroupMapper {

    
    List<Group> selectGroupsByMemberId(Member member);

}
