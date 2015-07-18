package com.goree.api.controller;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.dbunit.dataset.IDataSet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.goree.api.Application;
import com.goree.api.domain.Group;
import com.goree.api.domain.Member;
import com.goree.api.mapper.MemberMapper;
import com.goree.api.util.DBUnitOperator;
import com.goree.api.util.IDataSetFactory;

import scala.annotation.meta.getter;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { Application.class })
public class GroupTest {
	@Autowired
	private GroupController groupController;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private MemberMapper memberMapper;

	@Before
	public void setUp() {
		DBUnitOperator.setDataSource(dataSource);
		IDataSet dataset = IDataSetFactory.fromXml("src/test/resources/testdataset/groupt_test_setup.xml");
		DBUnitOperator.cleanInsert(dataset);
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
