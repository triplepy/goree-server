package com.goree.api.controller;

import com.goree.api.domain.Group;
import com.goree.api.domain.Member;
import com.goree.api.domain.Note;
import com.goree.api.util.TestWithDBUnit;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


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

}
