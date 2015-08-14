package com.goree.api.service;

import com.goree.api.domain.Group;
import com.goree.api.domain.Member;
import com.goree.api.domain.Member.Gender;
import com.goree.api.domain.Tag;
import com.goree.api.util.TestWithDBUnit;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;


public class MemberServiceTest extends TestWithDBUnit{

    @Autowired
    private MemberService memberService;
    @Autowired
    private GroupService groupService;
    
    private Member testMember;


    @Override
    public String getDatasetFilePath() {
        return "src/test/resources/testdataset/member_test_setup.xml";
    }
    
    @Override
    @Before
    public void setUp() {
        super.setUp();
        testMember = memberService.findMemberAll().get(0);
    }
    
    @Test
    public void findMemberAll() {
        List<Member> members = memberService.findMemberAll();
        Assert.assertNotNull(members);
        Assert.assertFalse(members.isEmpty());
    }
    
    @Test 
    public void registerMember() {
        Member expected = new Member();
        expected.setEmail("rpxhdnjsdud" + new Date().getTime() + "@nate.com");
        expected.setPassword("qlalfqjsgh");
        expected.setFullName("Wonyoung Ju");
        expected.setAge(22);
        expected.setNickname("nickname");
        expected.setGender(Gender.M);
        expected.setPhone("010-8826-0173");
        expected.setJob("programmer");
        expected.setFacebookUserId("facebookuserid");

        Tag tag = new Tag();
        tag.setName("memberTest");
        expected.setTags(Arrays.asList(tag));
        Member registered = memberService.registerMember(expected);
        Assert.assertNotNull(registered);
        Assert.assertEquals(expected.getEmail(), registered.getEmail());
        Assert.assertEquals(expected.getFacebookUserId(), registered.getFacebookUserId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void registerMemberWithoutFacebookUserId() {
        Member expected = new Member();
        expected.setEmail("rpxhdnjsdud" + new Date().getTime() + "@nate.com");
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
        Member registered = memberService.registerMember(expected);
        Assert.assertNotNull(registered);
        Assert.assertEquals(expected.getEmail(), registered.getEmail());
        Assert.assertEquals(expected.getFacebookUserId(), registered.getFacebookUserId());;
    }
    
    @Test
    public void deleteMemberById() {
        memberService.deleteMemberById(testMember.getId());
        
        boolean deleted = !memberService.findMemberAll().stream()
                .anyMatch(m-> m.getId() == testMember.getId());
        Assert.assertTrue(deleted);
        
        List<Group> groupsHasDeletedMember = 
                groupService.findRegistedGroupsByMember(testMember.getId());
        Assert.assertTrue(groupsHasDeletedMember.isEmpty());
    }
    
    @Test
    public void joinMemberToGroup() {
        Group toBeJoined = groupService.findGroupAll().get(0);
        long toBeJoinedGroupId = toBeJoined.getId();
        groupService.joinMember(toBeJoinedGroupId, testMember.getId());
        
        List<Group> joinedGroups = groupService.findRegistedGroupsByMember(testMember.getId());
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
        expected.setFacebookUserId("12345");
        List<Tag> tags = new ArrayList<>();
        Tag tag1 = new Tag();
        tag1.setId(1);
        tag1.setName("tag1");
        tag1.setProvided('N');
        Tag tag2 = new Tag();
        tag1.setId(2);
        tag2.setName("tag3");
        tag2.setProvided('Y');
        expected.setTags(tags);

        Member actual = memberService.findMemberById(expected.getId());

        Assert.assertEquals(expected.getEmail(), actual.getEmail());
        Assert.assertEquals(expected.getNickname(), actual.getNickname());
        Assert.assertEquals(expected.getFacebookUserId(), actual.getFacebookUserId());
        Assert.assertTrue(actual.getTags().containsAll(expected.getTags()));
    }

    @Test
    public void findMemberByFacebookUserId() {
        Member expected = memberService.findMemberById(1L);
        Member actual = memberService.findMemberByFacebookUserId("12345");

        assertThat(actual.getId(), is(expected.getId()));
        assertThat(actual.getEmail(), is(expected.getEmail()));
    }

}
