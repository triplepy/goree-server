package com.goree.api.controller;


import com.goree.api.domain.Group;
import com.goree.api.domain.Meeting;
import com.goree.api.domain.Member;
import com.goree.api.util.TestWithDBUnit;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MeetingFindByMemberTest extends TestWithDBUnit{

    @Autowired
    private GroupController groupController;

    @Autowired
    private MemberController memberController;

    @Autowired
    private PlaceController placeController;

    @Autowired
    private MeetingController meetingController;



    @Override
    public String getDatasetFilePath() {
        return "src/test/resources/testdataset/meeting_find_by_member_test_setup.xml";
    }

    @Test
    public void findMeetingsByMemberId() throws Exception{
        //given
        int memberId = 1;

        Member member = new Member();
        member.setId(memberId);

        List<Group> groupsJoined = groupController.findGroupsJoined(member);
        List<Meeting> meetingsOfMember = meetingController.findMeetingsByMemberId(memberId);
        meetingsOfMember.stream().forEach(meeting -> {
            Group group = meeting.getGroup();
            Assert.assertTrue(groupsJoined.stream().anyMatch(g -> g.getId() == group.getId()));
        });
    }
}
