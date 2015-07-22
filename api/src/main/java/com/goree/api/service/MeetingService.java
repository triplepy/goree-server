package com.goree.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goree.api.domain.Meeting;
import com.goree.api.domain.Place;
import com.goree.api.mapper.MeetingMapper;

@Service
public class MeetingService {
    @Autowired
    private MeetingMapper meetingMapper;
    @Autowired
    private PlaceService placeService;
    
    public Meeting createMeeting(Meeting meeting) {
        Place placeExists = placeService.findPlaceByItself(meeting.getPlace());
        if (placeExists == null) {
            Place created = placeService.createPlace(meeting.getPlace());
            meeting.setPlace(created);
        }
        
        meetingMapper.insertMeeting(meeting);
        Meeting created = findMeetingByItself(meeting);
        return created;
    }

    public Meeting findMeetingByItself(Meeting meeting) {
        return meetingMapper.findMeetingByItself(meeting);
    }

}
