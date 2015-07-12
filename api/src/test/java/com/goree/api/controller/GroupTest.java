package com.goree.api.controller;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.goree.api.Application;
import com.goree.api.domain.Group;
import com.goree.api.domain.Member;
import com.goree.api.service.MemberService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes={Application.class})
public class GroupTest {
    @Autowired
    private GroupController groupController;
    @Autowired
    private MemberService memberService;
    
    @Test
    public void joinedGroup() {
        Member member = new Member();
        member.setId(1);
        
        List<Group> joinedGroups = groupController.groupsJoined(member);
        Assert.assertNotNull(member);
        Assert.assertTrue(joinedGroups.size() > 0);
    }
    
//    @Test
//    public void createGroup() {
//        Group group = new Group();
//        group.setName("develop_t!e@s#t$");
//        group.setDescription("description");
//        Member leader = memberService.
//        
//        group.setLeader(leader);
//    }
}
