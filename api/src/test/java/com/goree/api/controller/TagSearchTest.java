package com.goree.api.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
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
import com.goree.api.domain.Member;
import com.goree.api.domain.Tag;
import com.goree.api.domain.Member.Gender;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes={Application.class})
@Transactional
public class TagSearchTest {
    @Autowired
    private TagController tagController;
    @Autowired
    private MemberController memberController;
    
    private List<Tag> expecteds;
    
    @Before
    public void setUp(){
        expecteds = new ArrayList<>();
        
        for (int i = 0; i < 10; i++) {
            Tag tagForSearch = new Tag();
            tagForSearch.setTagName("Search" + i);
            
            // 값을 삽입한 뒤 반환값은 expectedList에 추가한다.
            expecteds.add(tagController.creatingTag(tagForSearch));
        }

        // 검색 테스트를 위해 TagName이 Search로 시작하는 태그를 삽입한다.
        for(int i = 0; i < 3 ; i++){
            Member member = new Member();
            member.setEmail("test0"+i+"@gmail.com");
            member.setPassword("qlalfqjsgh");
            member.setFullName("Wonyoung Ju");
            member.setAge(22);
            member.setGender(Gender.M);
            member.setPhone("010-8826-0173");
            List<Tag> tags = null;
            switch (i) {
            case 0:
                tags = Arrays.asList(expecteds.get(0));
                break;
            case 1:
                tags = Arrays.asList(expecteds.get(0), expecteds.get(1),expecteds.get(2));
                break;
            case 2:
                tags = Arrays.asList(expecteds.get(0), expecteds.get(1));
                break;
            }
            member.setTags(tags);
            memberController.registerMember(member);
        }
        expecteds.get(0).setCount(3);
        expecteds.get(1).setCount(2);
        expecteds.get(2).setCount(1);
        
        
    }
    
    @Test
    public void searchTagsByStartWord() {
        // 추후 매핑시에는 아래와 같은 방법으로 테스트가 불가능 하여 다른 방법으로 Assert 필요함
        // (순서가 변경될시엔 아래 테스트가 실패하게 됨)
        
        List<Tag> actuals = tagController.searchTagsByStartWord("Search");
        Assert.assertTrue(actuals.containsAll(expecteds));
    }
    
    @Test
    public void searchTagsByStartWordOrdered() {
        List<Tag> expecteds = new ArrayList<>(this.expecteds);
        for (Tag tag : expecteds){
            System.out.println("-=-=-=-=-=-=-=-=-= expectedCount = " + tag);
        }
        
        expecteds.sort(new Comparator<Tag>() {
            @Override
            public int compare(Tag o1, Tag o2) {
                return o2.getCount() - o1.getCount();
            }
        });
        
        System.out.println("-=-=-=-=-=-=-=-=-= expectedCount = " +expecteds.get(1));
        
        List<Tag> actuals = tagController.searchTagsByStartWord("Search");
        

        for(int i = 0 ; i < expecteds.size(); i++){
            Assert.assertEquals(expecteds.get(i), actuals.get(i));
        }
        
    }
    
}
