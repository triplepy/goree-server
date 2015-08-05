package com.goree.api.mapper;

import com.goree.api.domain.Group;
import com.goree.api.domain.Tag;

import java.util.List;

public interface GroupMapper {

    
    List<Group> selectGroupsByMemberId(long member);

    void insertGroup(Group group);
    
    Group selectGroupById(long id);
    
    List<Group> selectGroupAll();
    
    Group selectGroupByName(String name);
    
    void deleteGroupMemberMappingByMemberId(long id);
    
    void insertToGroupHasMember(long groupId, long memberId);

    List<Group> selectGroupsByTagOrderByMemberCount(Tag tag);

    void updateImagePath(long id, String fileName);
}
