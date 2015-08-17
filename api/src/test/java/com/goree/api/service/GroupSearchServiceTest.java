package com.goree.api.service;


import com.goree.api.domain.Group;
import com.goree.api.domain.Tag;
import com.goree.api.util.TestWithDBUnit;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class GroupSearchServiceTest extends TestWithDBUnit{
    @Autowired
    private GroupService groupService;

    @Autowired
    private TagService tagService;

    @Override
    public String getDatasetFilePath() {
        return "src/test/resources/testdataset/group_search_test_setup.xml";
    }

    @Test
    public void findGroupsByTagOrderByMemberCount() {
        Tag tag = new Tag();
        tag.setName("test_tag");

        List<Group> expecteds = groupService.findGroupAll();
        expecteds.sort((group1, group2) -> NumberUtils.compare(group2.getMemberCount(), group1.getMemberCount()));

        List<Group> actuals = groupService.findGroupsByTagOrderByMemberCount(tag);

        for (int i=0; i<actuals.size(); i++) {
            Group actual = actuals.get(i);
            Group expected = expecteds.get(i);
            assertThat(actual.getId(), is(expected.getId()));
            assertThat(actual.getName(), is(expected.getName()));

        }
    }

    @Test
    public void findGroupsByTagOrderByMemberCount_dataNotFound() {
        Tag tag = new Tag();
        tag.setName("test_tag2");

        List<Group> actual = groupService.findGroupsByTagOrderByMemberCount(tag);

        assertThat(actual, is(empty()));
    }

    @Test
    public void findGroupsByTagOrderByMemberCount_tagNotExists() {
        Tag tagNotExists = new Tag();
        tagNotExists.setName("not exists tag");

        List<Group> actual = groupService.findGroupsByTagOrderByMemberCount(tagNotExists);

        assertThat(actual, is(empty()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void findGroupsByTagOrderByMemberCount_nullTag() {
        Tag nullTag = null;
        groupService.findGroupsByTagOrderByMemberCount(nullTag);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findGroupsByTagOrderByMemberCount_blankString() {
        String blank = "       ";
        groupService.findGroupsByTagOrderByMemberCount(blank);
    }

}
