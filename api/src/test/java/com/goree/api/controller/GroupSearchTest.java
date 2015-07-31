package com.goree.api.controller;


import com.goree.api.domain.Group;
import com.goree.api.domain.Tag;
import com.goree.api.util.TestWithDBUnit;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class GroupSearchTest extends TestWithDBUnit{
    @Autowired
    private GroupController groupController;

    @Test
    public void findGroupsByTagOrderByMemberCount() {
        Tag tag = new Tag();
        tag.setName("test_tag");

        List<Group> expecteds = groupController.findGroupAll();
        expecteds.sort((group1, group2) ->
             group2.getMemberCount() > group1.getMemberCount() ? 1 :
                    group2.getMemberCount() == group1.getMemberCount() ? 0 : -1
        );
        List<Group> actuals = groupController.findGroupsByTagOrderByMemberCount(tag);

        for (int i=0; i<actuals.size(); i++) {
            Group actual = actuals.get(i);
            Group expected = expecteds.get(i);
            assertThat(actual.getId(), is(expected.getId()));
            assertThat(actual.getName(), is(expected.getName()));

        }
    }



    @Override
    public String getDatasetFilePath() {
        return "src/test/resources/testdataset/group_search_test_setup.xml";
    }
}
