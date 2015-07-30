package com.goree.api.mapper;

import java.util.List;

import com.goree.api.domain.Group;
import com.goree.api.domain.Member;

public interface GroupMapper {

    
    List<Group> selectGroupsByMemberId(Member member);

    void insertGroup(Group group);
    
    Group selectGroupById(long id);
    
    List<Group> selectGroupAll();
    
    Group selectGroupByName(String name);
    
    void deleteGroupMemberMappingByMemberId(long id);
    
    void insertToGroupHasMember(long groupId, long memberId);
}
