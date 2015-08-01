package com.goree.api.controller;

import com.goree.api.domain.Group;
import com.goree.api.domain.Member;
import com.goree.api.domain.Member.Gender;
import com.goree.api.domain.Tag;
import com.goree.api.service.MemberService;
import com.goree.api.util.TestWithDBUnit;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;


public class MemberTest extends TestWithDBUnit{

    @Autowired
    private MemberController memberController;
    @Autowired
    private GroupController groupController;
    
    private Member testMember;

    @Autowired
    private MemberService memberService;

    @Override
    public String getDatasetFilePath() {
        return "src/test/resources/testdataset/member_test_setup.xml";
    }
    
    @Override
    @Before
    public void setUp() {
        super.setUp();
        testMember = memberController.findMemberAll().get(0);
    }
    
    @Test
    public void findMemberAll() {
        List<Member> members = memberController.findMemberAll();
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
        expected.setNickname("nickname");
        expected.setGender(Gender.M);
        expected.setPhone("010-8826-0173");
        expected.setJob("programmer");
        Tag tag = new Tag();
        tag.setName("memberTest");
        expected.setTags(Arrays.asList(tag));
        Member registered = memberController.registerMember(expected);
        Assert.assertNotNull(registered);
        Assert.assertEquals(expected.getEmail(), registered.getEmail());
    }
    
    @Test
    public void deleteMemberById() {
        memberController.deleteMemberById(testMember.getId());
        
        boolean deleted = !memberController.findMemberAll().stream()
                .anyMatch(m-> m.getId() == testMember.getId());
        Assert.assertTrue(deleted);
        
        List<Group> groupsHasDeletedMember = 
                groupController.findGroupsJoined(testMember);
        Assert.assertTrue(groupsHasDeletedMember.isEmpty());
    }
    
    @Test
    public void joinMemberToGroup() {
        Group toBeJoined = groupController.findGroupAll().get(0);
        long toBeJoinedGroupId = toBeJoined.getId();
        groupController.joinMember(toBeJoinedGroupId, testMember.getId());
        
        List<Group> joinedGroups = groupController.findGroupsJoined(testMember);
        boolean joined = joinedGroups.contains(toBeJoined);
        Assert.assertTrue(joined);
    }

    @Test
    public void findMemberById() {
        Member expected = new Member();
        expected.setId(1);
        expected.setEmail("arst@arst.com");
        expected.setPassword("arstarst");
        expected.setFullName("arstarstar");
        expected.setAge(20);
        expected.setNickname("arstarst");
        expected.setGender(Gender.M);
        Member actual = memberController.findMemberById(expected.getId());

        Assert.assertEquals(expected.getEmail(), actual.getEmail());
        Assert.assertEquals(expected.getNickname(), actual.getNickname());
    }

    @Test
    public void findMemberByFacebookUserId() {
        Member expected = memberController.findMemberById(1L);
        Member actual = memberService.findMemberByFacebookUserId("12345");

        assertThat(actual.getId(), is(expected.getId()));
        assertThat(actual.getEmail(), is(expected.getEmail()));
    }

}
