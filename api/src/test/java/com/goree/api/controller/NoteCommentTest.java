package com.goree.api.controller;

import com.goree.api.domain.Member;
import com.goree.api.domain.Note;
import com.goree.api.domain.NoteComment;
import com.goree.api.util.TestWithDBUnit;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class NoteCommentTest extends TestWithDBUnit{

    @Autowired
    private NoteCommentController noteCommentController;


    @Override
    public String getDatasetFilePath() {
        return "src/test/resources/testdataset/note_comment_test_setup.xml";
    }


    @Test
    public void findNoteCommentById() {
        NoteComment expected = new NoteComment();
        expected.setId(1);
        expected.setContent("findNoteCommentById");


        Note note = new Note();
        note.setId(1);
        note.setContent("NoteCommentTest");
        expected.setNote(note);

        Member writer = new Member();
        writer.setId(2);
        writer.setNickname("asdf");

        expected.setWriter(writer);
        expected.setContent("NoteComcomcomcomcocm");

        NoteComment actual = noteCommentController.findNoteCommentById(expected.getId());

        Assert.assertEquals(expected, actual);
        Assert.assertNotNull(actual.getCreateDate());
    }


    @Test
    public void findNoteCommentByNoteId(){
        NoteComment expected = new NoteComment();
        NoteComment actual = new NoteComment();



        Assert.assertEquals(expected, actual);
    }

}
