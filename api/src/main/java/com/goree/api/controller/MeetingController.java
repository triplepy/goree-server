package com.goree.api.controller;

import com.goree.api.domain.Group;
import com.goree.api.domain.Meeting;
import com.goree.api.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/meeting")
public class MeetingController {
    @Autowired
    private MeetingService meetingService;

    /**
     * @api
     * @apiGroup
     * @apiDescription
     */
    @RequestMapping(value="", method= RequestMethod.POST)
    public Meeting createMeeting(@RequestBody Meeting meeting) {
        return meetingService.createMeeting(meeting);
    }

    /**
     * @api
     * @apiGroup
     * @apiDescription
     */
    @RequestMapping(value="/{meetingId}",method=RequestMethod.GET)
    public Meeting findMeetingById(@PathVariable long meetingId) {
        return meetingService.findMeetingById(meetingId);
    }

    /**
     * @api
     * @apiGroup
     * @apiDescription
     */
    @RequestMapping(value="/group/{groupId}")
    public List<Meeting> findMeetingsByGroupId(@PathVariable long groupId) {
        return meetingService.findMeetingsByGroupId(groupId);
    }

    /**
     * @api
     * @apiGroup
     * @apiDescription
     */
    @RequestMapping(value="/groups", method=RequestMethod.POST)
    public List<Meeting> findMeetingsByGroups(@RequestBody List<Group> groups) {
        return meetingService.findMeetingsByGroups(groups);
    }

    /**
     * @api
     * @apiGroup
     * @apiDescription
     */
    @RequestMapping(value="/member/{memberId}", method=RequestMethod.GET)
    public List<Meeting> findMeetingsByMemberId(@PathVariable long memberId) {
        return meetingService.findMeetingsByMemberId(memberId);
    }

    /**
     * @api
     * @apiGroup
     * @apiDescription
     */
    @RequestMapping(value="/commingUp/member/{memberId}", method=RequestMethod.GET)
    public List<Meeting> commingUpMeetingsOfMember(@PathVariable long memberId) {
        return meetingService.commingUpMeetingsOfMember(memberId);
    }
    /**
     * @api
     * @apiGroup
     * @apiDescription
     */
    @RequestMapping(value="/done/member/{memberId}", method=RequestMethod.GET)
    public List<Meeting> doneMeetingsOfMember(@PathVariable long memberId) {
        return meetingService.doneMeetingsOfMember(memberId);
    }
}
