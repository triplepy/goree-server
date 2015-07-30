package com.goree.api.mapper;

import com.goree.api.domain.Group;
import com.goree.api.domain.Meeting;

import java.util.List;

public interface MeetingMapper {
    void insertMeeting(Meeting meeting);
    Meeting findMeetingByItself(Meeting meeting);
    Meeting findMeetingById(long id);
    List<Meeting> selectMeetingByGroupId(long groupId);
    List<Meeting> selectMeetingsByGroups(List<Group> groups);
    List<Meeting> selectCommingUpMeetingsOfGroups(List<Group> groupsJoined);
    List<Meeting> selectDoneMeetingsOfGroups(List<Group> groupsJoined);
}
