package com.goree.api.controller;


import com.goree.api.Application;
import com.goree.api.auth.FacebookSettings;
import com.goree.api.domain.Attendance;
import com.goree.api.domain.Group;
import com.goree.api.domain.Meeting;
import com.goree.api.domain.Member;
import com.goree.api.service.AttendanceService;
import com.goree.api.util.HttpHeaderConstants;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
@WebAppConfiguration
public class AttendanceRestTest {
    private MockMvc mockMvc;

    @InjectMocks
    private AttendanceController attendanceController;

    @Mock
    private AttendanceService attendanceService;

    @Autowired
    private FacebookSettings settings;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(attendanceController).build();
    }

    @Test
    public void findAttendanceByMemberAndMeeting() throws Exception {
        // given
        long memberId = 1L;
        long meetingId = 1L;

        Meeting meeting = new Meeting();
        meeting.setId(meetingId);
        meeting.setTitle("Meeting ing");
        Member promoter = new Member();
        promoter.setId(1121);
        meeting.setPromoter(promoter);

        Group group = new Group();
        group.setId(123123L);
        group.setName("arvoinearvsonei");
        group.setLeader(promoter);
        group.setDescription("arvarvsrsav");
        meeting.setGroup(group);

        Member member = new Member();
        member.setId(memberId);


        Attendance attendance = new Attendance();
        attendance.setMeeting(meeting);
        attendance.setMember(member);
        attendance.setStatus(Attendance.Status.Y);
        when(attendanceService.findAttendanceByMemberAndMeeting(memberId, meetingId)).thenReturn(attendance);

        // when

        // then
        mockMvc.perform(get("/attendance/find/member/"+memberId+"/meeting/" + meetingId)
                .header(HttpHeaderConstants.AUTH_TOKEN, settings.longLivedTokenForTest()))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.member.id").value((int) memberId))
                    .andExpect(jsonPath("$.meeting.id").value((int) memberId))
                    .andExpect(jsonPath("$.meeting.title").value(meeting.getTitle()))
                    .andExpect(jsonPath("$.status", is(attendance.getStatus().toString())));

    }




}
