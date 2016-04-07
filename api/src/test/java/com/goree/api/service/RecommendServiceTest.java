package com.goree.api.service;

import com.goree.api.domain.Group;
import com.goree.api.util.TestWithDBUnit;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class RecommendServiceTest extends TestWithDBUnit {

    @Autowired
    private RecommendService recommendService;

    @Autowired
    private GroupService groupService;

    @Override
    public String getDatasetFilePath() {
        return "src/test/resources/testdataset/recommend_test_setup.xml";
    }

    @Test
    public void recommendationsOfMember() {
        // given
         List<Group> expected = groupService.findGroupAll().stream().filter(group -> group.getId() != 3 && group.getId() != 5)
                .sorted((group1, group2) -> {
                    return NumberUtils.compare(group2.getMemberCount(), group1.getMemberCount());
                }).collect(Collectors.toList());

        // when
        long memberId = 1L;
        List<Group> actual = recommendService.groupRecommendationOfMember(memberId);

        // then
        assertThat(actual, contains(expected.toArray()));
    }
}
