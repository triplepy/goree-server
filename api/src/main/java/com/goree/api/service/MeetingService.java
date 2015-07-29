package com.goree.api.service;

import com.goree.api.domain.Group;
import com.goree.api.domain.Meeting;
import com.goree.api.domain.Member;
import com.goree.api.domain.Place;
import com.goree.api.mapper.MeetingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeetingService {
    @Autowired
    private MeetingMapper meetingMapper;
    @Autowired
    private PlaceService placeService;
    @Autowired
    private GroupService groupService;

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

    public Meeting findMeetingById(int meetingId) {
        return meetingMapper.findMeetingById(meetingId);
    }

    public List<Meeting> findMeetingByGroupId(int groupId) {
        return meetingMapper.selectMeetingByGroupId(groupId);
    }

    public List<Meeting> findMeetingsByGroups(List<Group> groups) {
        return meetingMapper.selectMeetingsByGroups(groups);
    }

    public List<Meeting> findMeetingsByMemberId(int memberId) {
        Member member = new Member();
        member.setId(memberId);

        List<Group> groupsJoined = groupService.findRegistedGroupsByMember(member);
        return findMeetingsByGroups(groupsJoined);
    }

    public List<Meeting> commingUpMeetingsOfMember(Member member) {
        List<Group> groupsJoined = groupService.findRegistedGroupsByMember(member);
        return meetingMapper.selectCommingUpMeetingsOfGroups(groupsJoined);
    }

    public List<Meeting> doneMeetingsOfMember(Member member) {
        List<Group> groupsJoined = groupService.findRegistedGroupsByMember(member);
        return meetingMapper.selectDoneMeetingsOfGroups(groupsJoined);
    }
}
