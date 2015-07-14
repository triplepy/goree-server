package com.goree.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goree.api.domain.Group;
import com.goree.api.domain.Member;
import com.goree.api.mapper.GroupMapper;

@Service
@Transactional
public class GroupService {
    @Autowired
    private GroupMapper groupMapper; 
    public List<Group> findRegistedGroupsByMember(Member member) {
        return groupMapper.selectGroupsByMemberId(member);
    }
    public Group makingGroup(Group group) {
        groupMapper.insertGroup(group);
        Group makedGroup = groupMapper.selectGroupByName(group.getName());
        return makedGroup;
    }
    public Group findGroupById(int id) {
        Group group = new Group();
        group.setId(id);
        return group;
    }
    public List<Group> findGroupAll() {
        return groupMapper.selectGroupAll();
    }
    public Group findGroupByName(String name) {
        return groupMapper.selectGroupByName(name);
    }
    public void joinMember(int groupId, int memberId) {
        groupMapper.insertToGroupHasMember(groupId, memberId);
    }
}
