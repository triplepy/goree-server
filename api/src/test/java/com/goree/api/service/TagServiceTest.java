package com.goree.api.service;

import com.goree.api.Application;
import com.goree.api.domain.Tag;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { Application.class })
@Transactional
public class TagServiceTest {
    @Autowired
    private TagService tagService;
    
	private Tag expected;

	@Before
	public void setUp() {
		expected = new Tag();
		expected.setName("Go!ree");

		// 테스트의 목적에만 집중할 수 있도록, 로직을 간단하게 하기위해 expected를 공통으로 사용한다.
		expected = tagService.creatingTag(expected);

	}

	@Test
	public void creatingTag() {
		// insert 테스트시엔 공통변수를 사용할 수 없기 때문에 새로운 expected를 만들어 준다.(duplicated key
		// 방지)
		Tag expected = new Tag();
		expected.setName("creatingTest");

		Tag actual = tagService.creatingTag(expected);
		Assert.assertEquals(expected.getName(), actual.getName());
	}

	@Test
	public void findTagByName() {
		Tag actual = tagService.findTagByName(expected.getName());
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void findTagById() {
		Tag actual = tagService.findTagById(expected.getId());
		Assert.assertEquals(expected, actual);
	}


}
