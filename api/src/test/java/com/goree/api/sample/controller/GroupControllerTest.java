package com.goree.api.sample.controller;

import java.util.ArrayList;
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
    public void joinedGroupTest {
        Member member = new Member();
        member.setId(1);
        
        Group group1 = new Group();
        Group group2 = new Group();
        group1.setId(1);
        group2.setId(2);
        group1.setName("Java");
        group2.setName("Python");
        
       
        
        List<Group> expected = new ArrayList<>();
        expected.add(group1);
        expected.add(group2);
        
        
        List<Group> groupList = groupController.joined(member);
        Assert.assertEquals(expected.get(0).get, groupList);
        
        
    }
}
