package com.goree.api.sample.controller;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.goree.api.Application;
import com.goree.api.controller.GroupController;
import com.goree.api.domain.Group;
import com.goree.api.domain.Member;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes={Application.class})
public class GroupControllerTest {
    @Autowired
    private GroupController groupController;
    
    @Test
    public void joinedGroupTest() {
        Member member = new Member();
        member.setId(1);
        
        List<Group> joinedGroups = groupController.groupsJoined(member);
        Assert.assertNotNull(member);
        Assert.assertTrue(joinedGroups.size() > 0);
    }
}
