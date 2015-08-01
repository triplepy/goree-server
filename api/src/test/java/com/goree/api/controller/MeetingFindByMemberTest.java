package com.goree.api.controller;


import com.goree.api.domain.Group;
import com.goree.api.domain.Meeting;
import com.goree.api.domain.Member;
import com.goree.api.util.IDataSetFactory;
import com.goree.api.util.TestWithDBUnit;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

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


    @Test
    public void commingUpMeetings() throws Exception{
        // given
        int memberId = 1;
        Member member = new Member();
        member.setId(memberId);
        List<Meeting> expecteds = new ArrayList<>();

        IDataSet dataSet = IDataSetFactory.fromXml(getDatasetFilePath());

        try {
            ITable itable = dataSet.getTable("meeting");
            // 테스트 값의 범위를 하드하게 정해줘야되서 안그럼 너무 힘들잖아
            // meeting_find_by_member_test_setup.xml 참고하면 암
            for(int i = 1; i < 4 ; i++){
                Meeting meeting = new Meeting();
                meeting.setId(Integer.parseInt((String)itable.getValue(i, "meeting_id")));

                SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date to = transFormat.parse(((String)itable.getValue(i, "date")));
                meeting.setDate(to);

                meeting.setDescription((String) itable.getValue(i, "meeting_desc"));
                meeting.setGroup(groupController.findGroupById(Integer.parseInt((String) itable.getValue(i, "group_id"))));
                meeting.setTitle((String) itable.getValue(i, "meeting_title"));
                meeting.setPlace(placeController.findPlaceById(Integer.parseInt((String) itable.getValue(i, "place_id"))));
                meeting.setPromoter(memberController.findMemberById(Integer.parseInt((String)itable.getValue(i,"promoter_id"))));
                expecteds.add(meeting);
            }

            expecteds.sort(new Comparator<Meeting>() {
                @Override
                public int compare(Meeting o1, Meeting o2) {
                    // ASC
                    return o1.getDate().compareTo(o2.getDate());
                }
            });
        } catch (DataSetException e) {
            throw new RuntimeException(e);
        }
        // when
        List<Meeting> commingUpMeetings = meetingController.commingUpMeetingsOfMember(member);

        // then
        for (int i=0; i<commingUpMeetings.size(); i++) {
            Meeting actual = commingUpMeetings.get(i);
            Meeting expected = expecteds.get(i);
            assertThat(actual.getId(), is(expected.getId()));
            assertThat(actual.getDate(), is(expected.getDate()));
            assertThat(actual.getGroup(), is(not(nullValue())));
            assertThat(actual.getPlace(), is(not(nullValue())));
            assertThat(actual.getPromoter(), is(not(nullValue())));
            assertThat(actual.getTitle(), is(expected.getTitle()));
        }
    }

    @Test
    public void doneMeetings() throws Exception {
        // given
        int memberId = 1;
        Member member = new Member();
        member.setId(memberId);
        List<Meeting> expecteds = new ArrayList<>();

        IDataSet dataSet = IDataSetFactory.fromXml(getDatasetFilePath());

        int row = 0;
        try {
            ITable itable = dataSet.getTable("meeting");

            Meeting meeting = new Meeting();
            meeting.setId(Integer.parseInt((String)itable.getValue(row, "meeting_id")));

            SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date to = transFormat.parse(((String)itable.getValue(row, "date")));
            meeting.setDate(to);

            meeting.setDescription((String) itable.getValue(row, "meeting_desc"));
            meeting.setGroup(groupController.findGroupById(Integer.parseInt((String) itable.getValue(row, "group_id"))));
            meeting.setTitle((String) itable.getValue(row, "meeting_title"));
            meeting.setPlace(placeController.findPlaceById(Integer.parseInt((String) itable.getValue(row, "place_id"))));
            meeting.setPromoter(memberController.findMemberById(Integer.parseInt((String)itable.getValue(row,"promoter_id"))));
            expecteds.add(meeting);




        } catch (DataSetException e) {
            throw new RuntimeException(e);
        }
        // when
        List<Meeting> doneMeetings = meetingController.doneMeetingsOfMember(member);

        // then
        for (int i=0; i<doneMeetings.size(); i++) {
            Meeting actual = doneMeetings.get(i);
            Meeting expected = expecteds.get(i);
            assertThat(actual.getId(), is(expected.getId()));
            assertThat(actual.getDate(), is(expected.getDate()));
            assertThat(actual.getGroup(), is(not(nullValue())));
            assertThat(actual.getPlace(), is(not(nullValue())));
            assertThat(actual.getPromoter(), is(not(nullValue())));
            assertThat(actual.getTitle(), is(expected.getTitle()));
        }
    }
}
