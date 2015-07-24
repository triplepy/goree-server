package com.goree.api.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.goree.api.domain.Group;
import com.goree.api.domain.Meeting;
import com.goree.api.domain.Member;
import com.goree.api.domain.Place;
import com.goree.api.util.TestWithDBUnit;

public class MeetingTest extends TestWithDBUnit {

    @Autowired
    private MeetingController meetingController;

    @Autowired
    private GroupController groupController;
    
    @Autowired
    private MemberController memberController;

    @Override
    public String getDatasetFilePath() {
        return "src/test/resources/testdataset/meeting_test_setup.xml";
    }

    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void createMeeting() {
        Meeting expected = new Meeting();
        expected.setTitle("createMeetingBBBBB");
        expected.setDescription("etsnsaeitetaenharsithaneitnatre");
        Group groupOfExpected = groupController.findGroupByName("meeting_test");
        expected.setGroup(groupOfExpected);
        Date meetingDate = Date.from(
                LocalDate.of(
                        2015, Month.OCTOBER, 23).atTime(0, 0)
                         .atZone(ZoneId.systemDefault()).toInstant());
        expected.setDate(meetingDate);
        Member promoter = memberController.findMemberAll().get(0);
        expected.setPromoter(promoter);
        Place place = new Place();
        place.setName("Place the createMeeting");
        place.setAddress("Adddddddddddreeeesss");
        place.setXCoordinate(new BigDecimal("36.017194"));
        place.setYCoordinate(new BigDecimal("128.6978236"));
        expected.setPlace(place);

        Meeting actual = meetingController.createMeeting(expected);

        Assert.assertNotNull(actual);
    }
    
    @Test
    public void findMeetingById() {
        int meetingId = 1;
        Meeting expected = new Meeting();
        expected.setId(1);
        expected.setTitle("title");
        Group group = new Group();
        group.setId(1);
        group.setName("meeting_test");
        expected.setGroup(group);
        Place place = new Place();
        place.setId(1);
        place.setName("place name");
        place.setAddress("place address");
        place.setXCoordinate(new BigDecimal("36.017194"));
        place.setYCoordinate(new BigDecimal("128.6978236"));
        expected.setPlace(place);
        Member promoter = new Member();
        promoter.setId(1);
        promoter.setNickname("arstarst");
        expected.setPromoter(promoter);
        Date meetingDate = Date.from(
                LocalDate.of(
                        2015, Month.OCTOBER, 23).atTime(0, 0)
                         .atZone(ZoneId.systemDefault()).toInstant());
        expected.setDate(meetingDate);
        
        Meeting actual = meetingController.findMeetingById(meetingId);
        Assert.assertEquals(expected, actual);
    }

}
