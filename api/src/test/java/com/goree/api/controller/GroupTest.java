package com.goree.api.controller;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.goree.api.domain.Group;
import com.goree.api.domain.Member;
import com.goree.api.mapper.MemberMapper;
import com.goree.api.util.TestWithDBUnit;

public class GroupTest extends TestWithDBUnit {
	@Autowired
	private GroupController groupController;

	@Autowired
	private MemberMapper memberMapper;

	
	@Override
	public String getDatasetFilePath() {
		return "src/test/resources/testdataset/group_test_setup.xml";
	}
	
	
	@Test
	public void findGroupsJoined() throws Exception {
		Member member = new Member();
		member.setId(1);

		List<Group> joinedGroups = groupController.findGroupsJoined(member);
		
		Assert.assertTrue(joinedGroups.size() == 1);
		Assert.assertTrue(joinedGroups.get(0).getId() == 1);
	}

	@Test
	public void createGroup() {
		Member leader = memberMapper.selectMemberByEmail("arst@arst.com");

		Group expected = new Group();
		expected.setName("It is Group" + new Date().getTime());
		expected.setDescription("It is description");
		expected.setLeader(leader);

		Group actual = groupController.makingGroup(expected);

		Assert.assertEquals(expected.getName(), actual.getName());
		Assert.assertEquals(expected.getDescription(), actual.getDescription());
		Assert.assertEquals(expected.getLeader(), actual.getLeader());
	}

	@Test
	public void findGroupById() {
		Group expected = groupController.findGroupAll().get(0);
		Group actual = groupController.findGroupById(expected.getId());
		
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void findGroupByName() {
		Group expected = groupController.findGroupAll().get(0);
		Group actual = groupController.findGroupByName(expected.getName());

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void findGroupAll() {
		List<Group> groups = groupController.findGroupAll();
		
		Assert.assertTrue(groups.size() > 0);
	}



}
