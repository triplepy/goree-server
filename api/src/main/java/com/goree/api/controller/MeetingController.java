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
     * @api {post} /meeting Create Meeting
     * @apiName Create Meeting
     * @apiGroup Meeting
     * @apiParam (Meeting)  {json}   Meeting-info Meeting information to create
     * @apiParamExample {json} Request-Example:
     * {
     *   "title": "createMeetingBBBBB",
     *
     *   "group": {
     *     "id": 1
     *   },
     *
     *   "place": {
     *     "name": "Place the createMeeting",
     *     "address": "Adddddddddddreeeesss",
     *     "xCoordinate": 36.017194,
     *     "yCoordinate": 128.6978236
     *   },
     *
     *   "promoter": {
     *     "id": 1
     *   },
     *
     *   "date": 1438009200000,
     *   "description": "etsnsaeitetaenharsithaneitnatre"
     *
     *   }
     *
     * @apiDescription 미팅 정보를 json 형태로 받아서 등록한다.
     */
    @RequestMapping(value="", method= RequestMethod.POST)
    public Meeting createMeeting(@RequestBody Meeting meeting) {
        return meetingService.createMeeting(meeting);
    }

    /**
     * @api {get} /meeting/:meetingId Get a meeting by meetingId
     * @apiName Get a meeting by meetingId
     * @apiGroup Meeting
     * @apiParam {Number}   meetingId   Meeting ID (sequence)
     * @apiSampleRequest /meeting/1
     * @apiDescription 미팅 ID에 해당하는 미팅의 정보를 가져온다.
     */
    @RequestMapping(value="/{meetingId}",method=RequestMethod.GET)
    public Meeting findMeetingById(@PathVariable long meetingId) {
        return meetingService.findMeetingById(meetingId);
    }

    /**
     * @api {get} /meeting/group/:groupId Get Meetings by Group ID
     * @apiName Get Meetings by Group ID
     * @apiGroup Meeting
     * @apiParam {Number}   groupId   Group ID (sequence)
     * @apiSampleRequest /meeting/group/1
     * @apiDescription 그룹 ID에 해당하는 미팅의 리스트를 가져온다.
     */
    @RequestMapping(value="/group/{groupId}", method=RequestMethod.GET)
    public List<Meeting> findMeetingsByGroupId(@PathVariable long groupId) {
        return meetingService.findMeetingsByGroupId(groupId);
    }

    /**
     * @api {post} /meeting/groups Get Meetings By Group List
     * @apiName Get Meetings By Group List
     * @apiGroup Meeting
     * @apiParam (Meeting)  {json}  Group-List(id)
     * @apiParamExample {json} Request-example:
     *  [{"id":1},{"id":2}]
     * @apiDescription 그룹 리스트(id값)을 받아서 미팅 리스트를 가져온다. (사실 필요없어 보인다...)
     */
    @RequestMapping(value="/groups", method=RequestMethod.POST)
    public List<Meeting> findMeetingsByGroups(@RequestBody List<Group> groups) {
        return meetingService.findMeetingsByGroups(groups);
    }

    /**
     * @api {get} /meeting/member/:memberId Get Meetings By Member ID
     * @apiName Get Meetings By Member ID
     * @apiGroup Meeting
     * @apiParam {number}   memberId  Member ID(sequence)
     * @apiSampleRequest /meeting/member/1
     * @apiDescription 회원 ID에 해당되는 미팅의 리스트를 가져온다.
     */
    @RequestMapping(value="/member/{memberId}", method=RequestMethod.GET)
    public List<Meeting> findMeetingsByMemberId(@PathVariable long memberId) {
        return meetingService.findMeetingsByMemberId(memberId);
    }

    /**
     * @api {get} /meeting/comingUp/member/:memberId Get coming up Meetings by Member ID
     * @apiName Get coming up Meetings by Member ID
     * @apiGroup Meeting
     * @apiParam {number}   memberId Member ID(sequence)
     * @apiSampleRequest /meeting/comingUp/member/1
     * @apiDescription 회원 ID에 해당되는 미팅 중, 다가올 미팅들을 최근 순서대로 가져온다.
     */
    @RequestMapping(value="/comingUp/member/{memberId}", method=RequestMethod.GET)
    public List<Meeting> comingUpMeetingsOfMember(@PathVariable long memberId) {
        return meetingService.commingUpMeetingsOfMember(memberId);
    }
    /**
     * @api {get} /meeting/done/member/:memberId Get done Meetings By Member ID
     * @apiName Get done Meetings By Member ID
     * @apiGroup Meeting
     * @apiParam {number}   memberId Member ID(sequence)
     * @apiSampleRequest /meeting/done/member/1
     * @apiDescription 회원 ID에 해당되는 미팅 중, 지나간 미팅들을 최근 순서대로 가져온다.
     */
    @RequestMapping(value="/done/member/{memberId}", method=RequestMethod.GET)
    public List<Meeting> doneMeetingsOfMember(@PathVariable long memberId) {
        return meetingService.doneMeetingsOfMember(memberId);
    }
}
