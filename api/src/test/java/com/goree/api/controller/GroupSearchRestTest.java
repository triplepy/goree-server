package com.goree.api.controller;


import com.goree.api.domain.Group;
import com.goree.api.domain.Tag;
import com.goree.api.service.GroupService;
import com.goree.api.util.RestTestWithDBUnit;
import com.goree.api.util.TestWithDBUnit;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class GroupSearchRestTest extends RestTestWithDBUnit {
    @Autowired
    private GroupService groupService;

    @Override
    public String getDatasetFilePath() {
        return "src/test/resources/testdataset/group_search_test_setup.xml";
    }

    @Test
    public void findGroupsByTagOrderByMemberCount() throws Exception {
        String tagName = "test_tag";
        performSet(get("/group/orderByMemberCount/tagName/" + tagName))
        .andExpect(jsonPath("$.[0].id").value(1))
        .andExpect(jsonPath("$.[1].id").value(3))
        .andExpect(jsonPath("$.[2].id").value(4))
        .andExpect(jsonPath("$.[3].id").value(2));
    }

}
