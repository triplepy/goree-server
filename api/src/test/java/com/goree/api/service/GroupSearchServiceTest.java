package com.goree.api.service;


import com.goree.api.domain.Group;
import com.goree.api.domain.Tag;
import com.goree.api.util.TestWithDBUnit;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class GroupSearchServiceTest extends TestWithDBUnit{
    @Autowired
    private GroupService groupService;

    @Override
    public String getDatasetFilePath() {
        return "src/test/resources/testdataset/group_search_test_setup.xml";
    }

    @Test
    public void findGroupsByTagOrderByMemberCount() {
        Tag tag = new Tag();
        tag.setName("test_tag");

        List<Group> expecteds = groupService.findGroupAll();
        expecteds.sort((group1, group2) ->
             group2.getMemberCount() > group1.getMemberCount() ? 1 :
                    group2.getMemberCount() == group1.getMemberCount() ? 0 : -1
        );
        List<Group> actuals = groupService.findGroupsByTagOrderByMemberCount(tag);

        for (int i=0; i<actuals.size(); i++) {
            Group actual = actuals.get(i);
            Group expected = expecteds.get(i);
            assertThat(actual.getId(), is(expected.getId()));
            assertThat(actual.getName(), is(expected.getName()));

        }
    }




}
