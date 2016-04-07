package com.goree.api.controller;


import com.goree.api.util.RestTestWithDBUnit;
import org.junit.Test;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class MeetingFindByMemberRestTest extends RestTestWithDBUnit{
    @Override
    public String getDatasetFilePath() {
        return "src/test/resources/testdataset/meeting_find_by_member_test_setup.xml";
    }

    @Test
    public void findMeetingsByMemberId() throws Exception {
        int memberId = 1;

        performSet(get("/meeting/member/"+memberId))
            .andExpect(jsonPath("$",hasSize(4)));
    }

    @Test
    public void commingUpMeetingsOfMember() throws Exception {
        long memberId = 1;

        performSet(get("/meeting/comingUp/member/" + memberId))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$.[0].id").value(2))
                .andExpect(jsonPath("$.[1].id").value(3))
                .andExpect(jsonPath("$.[2].id").value(4));
    }

    @Test
    public void doneMeetingsOfMember() throws Exception {
        long memberId = 1;

        performSet(get("/meeting/done/member/" + memberId))
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$.[0].id").value(1));
    }
}

