package com.goree.api.service;

import com.goree.api.domain.Group;
import com.goree.api.domain.Member;
import com.goree.api.mapper.MemberMapper;
import com.goree.api.util.TestWithDBUnit;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class GroupServiceTest extends TestWithDBUnit {
	@Autowired
	private GroupService groupService;

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

		List<Group> joinedGroups = groupService.findRegistedGroupsByMember(member.getId());
		
		Assert.assertTrue(joinedGroups.size() == 1);
		Assert.assertTrue(joinedGroups.get(0).getId() == 1);
		Assert.assertTrue(joinedGroups.get(0).getMemberCount() == 1);
	}

	@Test
	public void createGroup() {
		Member leader = memberMapper.selectMemberByEmail("arst@arst.com");

		Group expected = new Group();
		expected.setName("It is Group" + new Date().getTime());
		expected.setDescription("It is description");
		expected.setLeader(leader);

		Group actual = groupService.makingGroup(expected);

		Assert.assertEquals(expected.getName(), actual.getName());
		Assert.assertEquals(expected.getDescription(), actual.getDescription());
		Assert.assertEquals(expected.getLeader(), actual.getLeader());
		Assert.assertEquals(expected.getMemberCount(), actual.getMemberCount());
	}

	@Test
	public void findGroupById() {
		Group expected = groupService.findGroupAll().get(0);
		Group actual = groupService.findGroupById(expected.getId());
		
		Assert.assertEquals(expected, actual);
		Assert.assertEquals(1, actual.getMemberCount());
	}

	@Test
	public void findGroupByName() {
		Group expected = groupService.findGroupAll().get(0);
		Group actual = groupService.findGroupByName(expected.getName());

		Assert.assertEquals(expected, actual);
		Assert.assertEquals(1, actual.getMemberCount());
	}

	@Test
	public void findGroupAll() {
		List<Group> groups = groupService.findGroupAll();
		
		Assert.assertTrue(groups.size() > 0);
		Assert.assertTrue(groups.get(0).getMemberCount() == 1);
	}

}
