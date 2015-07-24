package com.goree.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.goree.api.domain.Meeting;
import com.goree.api.service.MeetingService;

@RestController
public class MeetingController {
    @Autowired
    private MeetingService meetingService;
    
    public Meeting createMeeting(Meeting meeting) {
        return meetingService.createMeeting(meeting);
    }

    public Meeting findMeetingById(int meetingId) {
        return meetingService.findMeetingById(meetingId);
    }

}
