package com.goree.api.mapper;

import com.goree.api.domain.Meeting;

public interface MeetingMapper {
    public void insertMeeting(Meeting meeting);
    public Meeting findMeetingByItself(Meeting meeting);
}
