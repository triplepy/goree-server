package com.goree.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goree.api.domain.Group;
import com.goree.api.domain.Member;
import com.goree.api.mapper.GroupMapper;

@Service
public class GroupService {
    @Autowired
    private GroupMapper groupMapper; 
    public List<Group> findRegistedGroupsByMember(Member member) {
        return groupMapper.selectGroupsByMemberId(member);
    }
}
