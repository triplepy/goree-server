package com.goree.api.controller;

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

	@Before
	public void setUp() {
		expected = new Tag();
		expected.setTagName("Go!ree");

		// 테스트의 목적에만 집중할 수 있도록, 로직을 간단하게 하기위해 expected를 공통으로 사용한다.
		expected = tagController.creatingTag(expected);

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


	
	
//	@Test
//	public void insertTagListByJoinUser() {
//	    List<Tag> expects = new ArrayList<>();
//
//	    String insertValueByUser = "#abc #abcd #abcde #adsarrst";
//	    String[] values = insretValueByUser.split(" ");
//	    for (String value : values) {
//	        value.replace("#", "");
//	    }
//
//	        
//	    for (int i = 0 ; i < values.length() ; i++) {
//	        Tag item = new Tag();
//	        item.setTagName(values[i]);
//	        expects.add(item);
//	    }
//	    
//	    List<Tag> actuals = tagController.insertTagListByJoinUser(insertValueByUser);   
//	    
//	    for(int i = 0 ; i < actuals.size(); i++ ) {
//	        Assert.assertEquals(expects.get(i).getTagName(), actuals.get(i).getTagName());
//	    }
//
//	}

	

}
