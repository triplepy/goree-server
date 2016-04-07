package com.goree.api.service;

import com.goree.api.domain.Group;
import com.goree.api.domain.Member;
import com.goree.api.domain.Note;
import com.goree.api.util.TestWithDBUnit;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.IsNot.not;


public class NoteServiceTest extends TestWithDBUnit{
    @Autowired
    private NoteService noteService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private MemberService memberService;

    @Override
    public String getDatasetFilePath() {
        return "src/test/resources/testdataset/note_test_setup.xml";
    }

    @Test
    public void findNoteById() {
        Note expected = new Note();
        expected.setId(1);
        expected.setContent("findNoteById");
        Group group = groupService.findGroupById(1);
        expected.setGroup(group);
        Member member = memberService.findMemberAll().get(1);

        expected.setNoteWriter(member);
        expected.setContent("findNoteById");
        Note actual = noteService.findNoteById(expected.getId());

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void writeNote() {
        // given
        Note newNote = new Note();
        newNote.setContent("content");
        Member noteWriter = memberService.findMemberById(2L);
        newNote.setNoteWriter(noteWriter);
        Group group = groupService.findGroupById(1L);
        newNote.setGroup(group);

        // when
        Note written = noteService.writeNote(newNote);

        // then
        assertThat(written.getId(), greaterThan(0L));
        Date now = new Date();
        assertThat(written.getCreateDate(), is(not(nullValue())));
        assertThat(written.getCreateDate().before(now), is(true));
        assertThat(written.getContent(), is(newNote.getContent()));
        assertThat(written.getNoteWriter().getId(), is(newNote.getNoteWriter().getId()));
        assertThat(written.getGroup().getId(), is(newNote.getGroup().getId()));
    }

}
