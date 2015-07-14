package com.goree.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.goree.api.Application;
import com.goree.api.domain.Tag;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { Application.class })
@Transactional
public class TagTest {
	@Autowired
	private TagController tagController;

	private Tag expected;

	private List<Tag> expecteds;

	@Before
	public void setUp() {
		expected = new Tag();
		expected.setTagName("Go!ree");

		// 테스트의 목적에만 집중할 수 있도록, 로직을 간단하게 하기위해 expected를 공통으로 사용한다.
		expected = tagController.creatingTag(expected);

		// 검색 테스트를 위해 TagName이 Search로 시작하는 태그를 삽입한다.
		Tag tagForSearch = new Tag();
		expecteds = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			tagForSearch.setTagName("Search" + i);

			// 값을 삽입한 뒤 반환값은 expectedList에 추가한다.
			expecteds.add(tagController.creatingTag(tagForSearch));
		}
	}

	@Test
	public void creatingTag() {
		// insert 테스트시엔 공통변수를 사용할 수 없기 때문에 새로운 expected를 만들어 준다.(duplicated key
		// 방지)
		Tag expected = new Tag();
		expected.setTagName("creatingTest");

		Tag actual = tagController.creatingTag(expected);
		Assert.assertEquals(expected.getTagName(), actual.getTagName());
	}

	@Test
	public void findTagByName() {
		Tag actual = tagController.findTagByName(expected.getTagName());
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void findTagById() {
		Tag actual = tagController.findTagById(expected.getId());
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void searchTagsByStartWord() {
		// 추후 매핑시에는 아래와 같은 방법으로 테스트가 불가능 하여 다른 방법으로 Assert 필요함
		// (순서가 변경될시엔 아래 테스트가 실패하게 됨)
		List<Tag> actuals = tagController.searchTagsByStartWord("Search");
		for (int i = 0; i < expecteds.size(); i++) {
			Assert.assertEquals(expecteds.get(i), actuals.get(i));
		}
	}

}
