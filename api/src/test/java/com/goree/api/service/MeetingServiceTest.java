package com.goree.api.service;

import com.goree.api.domain.Group;
import com.goree.api.domain.Meeting;
import com.goree.api.domain.Member;
import com.goree.api.domain.Place;
import com.goree.api.util.IDataSetFactory;
import com.goree.api.util.TestWithDBUnit;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

public class MeetingServiceTest extends TestWithDBUnit {

    @Autowired
    private MeetingService meetingService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private PlaceService placeService;


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
        Group groupOfExpected = groupService.findGroupByName("meeting_test");
        expected.setGroup(groupOfExpected);
        Date meetingDate = Date.from(
                LocalDate.of(
                        2030, Month.JULY, 28).atTime(0, 0)
                         .atZone(ZoneId.systemDefault()).toInstant());
        expected.setDate(meetingDate);
        Member promoter = memberService.findMemberAll().get(0);
        expected.setPromoter(promoter);
        Place place = new Place();
        place.setName("Place the createMeeting");
        place.setAddress("Adddddddddddreeeesss");
        place.setXCoordinate(new BigDecimal("36.017194"));
        place.setYCoordinate(new BigDecimal("128.6978236"));
        expected.setPlace(place);

        Meeting actual = meetingService.createMeeting(expected);

        Assert.assertNotNull(actual);
    }
    
    @Test
    public void findMeetingById() {
        int meetingId = 1;
        Meeting expected = new Meeting();
        expected.setId(1);
        expected.setTitle("title1");
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
                        2015, Month.JULY, 28).atTime(0, 0)
                        .atZone(ZoneId.systemDefault()).toInstant());
        expected.setDate(meetingDate);
        
        Meeting actual = meetingService.findMeetingById(meetingId);

        assertThat(actual.getId(), is(expected.getId()));
        assertThat(actual.getDate(), is(expected.getDate()));
        assertThat(actual.getGroup(), is(not(nullValue())));
        assertThat(actual.getPlace(), is(not(nullValue())));
        assertThat(actual.getPromoter(), is(not(nullValue())));
        assertThat(actual.getTitle(), is(expected.getTitle()));
    }

    @Test
    public void findMeetingsByGroupId() throws Exception{
        List<Meeting> expecteds = new ArrayList<>();

        IDataSet dataSet = IDataSetFactory.fromXml(getDatasetFilePath());
        try {
            ITable itable = dataSet.getTable("meeting");
            for(int i = 0; i < itable.getRowCount() ; i++){
                Meeting meeting = new Meeting();
                meeting.setId(Integer.parseInt((String)itable.getValue(i, "meeting_id")));

                SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date to = transFormat.parse(((String)itable.getValue(i, "date")));
                meeting.setDate(to);

                meeting.setDescription((String) itable.getValue(i, "meeting_desc"));
                meeting.setGroup(groupService.findGroupById(Integer.parseInt((String) itable.getValue(i, "group_id"))));
                meeting.setTitle((String) itable.getValue(i, "meeting_title"));
                meeting.setPlace(placeService.findPlaceById(Integer.parseInt((String) itable.getValue(i, "place_id"))));
                meeting.setPromoter(memberService.findMemberById(Long.parseLong((String) itable.getValue(i, "promoter_id"))));
                expecteds.add(meeting);
            }
        } catch (DataSetException e) {
            throw new RuntimeException(e);
        }

        long groupId = 1;
        List<Meeting> actuals = meetingService.findMeetingsByGroupId(groupId);

        Assert.assertTrue(actuals.containsAll(expecteds));
        Assert.assertTrue(expecteds.containsAll(actuals));
    }

    @Test
    public void findMeetingsByGroups() throws Exception {
        // given
        List<Group> groups = groupService.findGroupAll();

        // when
        List<Meeting> meetings = meetingService.findMeetingsByGroups(groups);

        // then
        meetings.forEach(meeting -> {
            Group groupOfMeeting = meeting.getGroup();
            long groupId = groupOfMeeting.getId();
            boolean isContains = groups.stream().anyMatch(group -> groupId == group.getId());
            Assert.assertTrue(isContains);
        });


    }

}
