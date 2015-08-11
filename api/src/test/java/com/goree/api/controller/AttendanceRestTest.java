package com.goree.api.controller;


import com.goree.api.util.RestTestWithDBUnit;
import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class AttendanceRestTest extends RestTestWithDBUnit {

    @Override
    public String getDatasetFilePath() {
        return "src/test/resources/testdataset/attendance_test_setup.xml";
    }

    @Test
    public void findAttendanceByMemberAndMeeting() throws Exception {
        int meetingId = 1;
        int memberId = 1;

        performSet(get("/attendance/find/member/" + memberId + "/meeting/" + meetingId))
        .andExpect(jsonPath("$.meeting.id").value(meetingId))
        .andExpect(jsonPath("$.member.id").value(memberId))
        .andExpect(jsonPath("$.status").value("N"));
    }



    @Test
    public void mapMeetingAndAttendanceCaseIsN() throws Exception {
        int meetingId = 1;
        int memberId = 1;
        String status = "Y";

        performSet(put("/attendance/map/member/" + memberId + "/meeting/" + meetingId + "/status/" + status));

        performSet(get("/attendance/find/member/" + memberId + "/meeting/" + meetingId))
                .andExpect(jsonPath("$.meeting.id").value(meetingId))
                .andExpect(jsonPath("$.member.id").value(memberId))
                .andExpect(jsonPath("$.status").value("Y"));
    }

    @Test
    public void mapMeetingAndAttendanceCaseIsY() throws Exception {
        int meetingId = 1;
        int memberId = 2;
        String status = "Y";

        performSet(put("/attendance/map/member/" + memberId + "/meeting/" + meetingId + "/status/" + status));

        performSet(get("/attendance/find/member/" + memberId + "/meeting/" + meetingId))
                .andExpect(jsonPath("$.meeting.id").value(meetingId))
                .andExpect(jsonPath("$.member.id").value(memberId))
                .andExpect(jsonPath("$.status").value("Y"));
    }
    @Test
    public void mapMeetingAndAttendanceCaseIsX() throws Exception {
        int meetingId = 1;
        int memberId = 3;
        String status = "Y";

        performSet(put("/attendance/map/member/" + memberId + "/meeting/" + meetingId + "/status/" + status));

        performSet(get("/attendance/find/member/" + memberId + "/meeting/" + meetingId))
                .andExpect(jsonPath("$.meeting.id").value(meetingId))
                .andExpect(jsonPath("$.member.id").value(memberId))
                .andExpect(jsonPath("$.status").value("Y"));
    }

}
