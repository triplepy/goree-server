package com.goree.api.controller;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.goree.api.Application;
import com.goree.api.domain.Member;
import com.goree.api.domain.Member.Gender;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes={Application.class})
public class MemberTest {

    @Autowired
    private MemberController controller;
    
    @Test
    public void findMemberAll() {
        List<Member> members = controller.findMemberAll();
        Assert.assertNotNull(members);
        Assert.assertFalse(members.isEmpty());
    }
    
    @Test
    public void registerMember() {
        Member expected = new Member();
        expected.setEmail("rpxhdnjsdud"+new Date().getTime()+"@nate.com");
        expected.setPassword("qlalfqjsgh");
        expected.setFullName("Wonyoung Ju");
        expected.setAge(22);
        expected.setGender(Gender.M);
        expected.setPhone("010-8826-0173");
        
        Member registered = controller.registerMember(expected);
        Assert.assertNotNull(registered);
        Assert.assertEquals(expected.getEmail(), registered.getEmail());
    }
    
    @Test
    public void deleteMemberById() {
        List<Member> members = controller.findMemberAll();
        Member toBeDeleted = members.get(members.size()-1);
        controller.deleteMemberById(toBeDeleted.getId());
        
        boolean deleted = !controller.findMemberAll().stream()
                .anyMatch(m-> m.getId() == toBeDeleted.getId());
        Assert.assertTrue(deleted);
    }
}
