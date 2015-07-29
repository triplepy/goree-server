package com.goree.api.mapper;

import com.goree.api.domain.Group;
import com.goree.api.domain.Meeting;

import java.util.List;

public interface MeetingMapper {
    void insertMeeting(Meeting meeting);
    Meeting findMeetingByItself(Meeting meeting);
    Meeting findMeetingById(int id);
    List<Meeting> selectMeetingByGroupId(int groupId);
    List<Meeting> selectMeetingsByGroups(List<Group> groups);
    List<Meeting> selectCommingUpMeetingsOfMember(List<Group> member);
}
