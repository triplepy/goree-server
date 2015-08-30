package com.goree.api.service;

import com.goree.api.domain.Group;
import com.goree.api.domain.Meeting;
import com.goree.api.domain.Member;
import com.goree.api.domain.Place;
import com.goree.api.mapper.MeetingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.google.common.base.Preconditions.*;
import static java.util.Objects.*;
import static org.apache.commons.lang3.StringUtils.*;

@Service
public class MeetingService {
    @Autowired
    private MeetingMapper meetingMapper;
    @Autowired
    private PlaceService placeService;
    @Autowired
    private GroupService groupService;

    public Meeting createMeeting(Meeting meeting) {
        checkArgument(meeting != null);
        checkArgument(isNotBlank(meeting.getTitle()));
        requireNonNull(meeting.getGroup(), "group must not be null.");
        requireNonNull(meeting.getDate(), "date must not be null");
        checkArgument(meeting.getDate().after(new Date()));
        requireNonNull(meeting.getPromoter(), "promoter must not be null.");
        requireNonNull(meeting.getPlace(),"place must not be null");


        Place placeExists = placeService.findPlaceByItself(meeting.getPlace());

        boolean placeIsNotExists = placeExists == null;
        if(placeIsNotExists) {
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

    public Meeting findMeetingById(long meetingId) {
        return meetingMapper.findMeetingById(meetingId);
    }

    public List<Meeting> findMeetingsByGroupId(long groupId) {
        return meetingMapper.selectMeetingByGroupId(groupId);
    }

    public List<Meeting> findMeetingsByGroups(List<Group> groups) {
        return meetingMapper.selectMeetingsByGroups(groups);
    }

    public List<Meeting> findMeetingsByMemberId(long memberId) {
        Member member = new Member();
        member.setId(memberId);

        List<Group> groupsJoined = groupService.findRegistedGroupsByMember(memberId);
        return findMeetingsByGroups(groupsJoined);
    }

    public List<Meeting> commingUpMeetingsOfMember(long memberId) {
        List<Group> groupsJoined = groupService.findRegistedGroupsByMember(memberId);
        return meetingMapper.selectCommingUpMeetingsOfGroups(groupsJoined);
    }

    public List<Meeting> commingUpMeetingsOfMember(Member member) {
        return commingUpMeetingsOfMember(member.getId());
    }

    public List<Meeting> doneMeetingsOfMember(long memberId) {
        List<Group> groupsJoined = groupService.findRegistedGroupsByMember(memberId);
        return meetingMapper.selectDoneMeetingsOfGroups(groupsJoined);
    }
    
    public List<Meeting> doneMeetingsOfMember(Member member) {
        return doneMeetingsOfMember(member.getId());
    }


}
