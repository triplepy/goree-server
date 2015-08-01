package com.goree.api.controller;

import com.goree.api.domain.Group;
import com.goree.api.domain.Member;
import com.goree.api.domain.Note;
import com.goree.api.util.TestWithDBUnit;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.IsNot.not;


public class NoteTest extends TestWithDBUnit{
    @Autowired
    private NoteController noteController;

    @Autowired
    private GroupController groupController;

    @Autowired
    private MemberController memberController;

    @Override
    public String getDatasetFilePath() {
        return "src/test/resources/testdataset/note_test_setup.xml";
    }

    @Test
    public void findNoteById(){
        Note expected = new Note();
        expected.setId(1);
        expected.setContent("findNoteById");
        Group group = groupController.findGroupById(1);
        expected.setGroup(group);
        Member member = memberController.findMemberAll().get(1);

        expected.setNoteWriter(member);
        expected.setContent("findNoteById");
        Note actual = noteController.findNoteById(expected.getId());

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void writeNote(){
        Note expected = new Note();
        expected.setContent("arstarstarst");

        Group group = groupController.findGroupById(1);
        expected.setGroup(group);

        Member member = memberController.findMemberAll().get(1);
        expected.setNoteWriter(member);

        Note actual = noteController.writeNote(expected);

        Assert.assertEquals(expected, actual);
        assertThat(actual.getGroup(),is(not(nullValue())));
        assertThat(actual.getNoteWriter(), is(not(nullValue())));
        assertThat(actual.getCreateDate(), is(not(nullValue())));
    }

}
