package com.goree.api.controller;

import com.goree.api.domain.Member;
import com.goree.api.domain.Note;
import com.goree.api.domain.NoteComment;
import com.goree.api.util.TestWithDBUnit;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


public class NoteCommentTest extends TestWithDBUnit{

    @Autowired
    private NoteCommentController noteCommentController;

    @Autowired
    private MemberController memberController;

    @Override
    public String getDatasetFilePath() {
        return "src/test/resources/testdataset/note_comment_test_setup.xml";
    }


    List<NoteComment> noteComments;

    @Override
    public void setUp(){
        super.setUp();
        noteComments = new ArrayList<>();

        Note note = new Note();
        note.setId(1);
        note.setContent("NoteCommentTest");



        NoteComment first = new NoteComment();
        first.setId(1);
        first.setContent("findNoteCommentById");

        first.setNote(note);

        Member firstWriter = new Member();
        firstWriter.setId(2);
        firstWriter.setNickname("asdf");

        first.setWriter(firstWriter);
        first.setContent("NoteComcomcomcomcocm");



        NoteComment second = new NoteComment();
        second.setId(1);
        second.setContent("findNoteCommentById");


        second.setNote(note);

        Member secondWriter = new Member();
        secondWriter.setId(1);
        secondWriter.setNickname("arstarst");

        second.setWriter(secondWriter);
        second.setContent("noteComcom");

        noteComments.add(first);
        noteComments.add(second);



    }


    @Test
    public void findNoteCommentById() {
        NoteComment expected = noteComments.get(0);

        NoteComment actual = noteCommentController.findNoteCommentById(expected.getId());

        Assert.assertEquals(expected, actual);
        Assert.assertNotNull(actual.getCreateDate());
    }


    @Test
    public void findNoteCommentsByNoteId(){
        List<NoteComment> expecteds = noteComments;

        List<NoteComment> actuals = noteCommentController.findNoteCommentsById(1);

        Assert.assertTrue(expecteds.containsAll(actuals));
    }


    @Test
    public void writeNoteComment(){
        NoteComment expected = new NoteComment();
        expected.setContent("writeNoteComment");

        Member writer = memberController.findMemberAll().get(0);
        expected.setWriter(writer);

        Note note = new Note();
        note.setId(1);
        expected.setNote(note);


        NoteComment actual = noteCommentController.writeNoteComment(expected);

        Assert.assertEquals(expected, actual);
        Assert.assertNotNull(actual.getCreateDate());
    }
}
