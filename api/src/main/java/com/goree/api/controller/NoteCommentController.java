package com.goree.api.controller;

import com.goree.api.domain.NoteComment;
import com.goree.api.service.NoteCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NoteCommentController {
    private static final String URL_PREFIX = "/group/note/comment";
    public static final String FIND_NOTECOMMENTS_BY_NOTE_ID_URL = URL_PREFIX + "/s/note/{noteId}";
    public static final String FIND_NOTECOMMENT_BY_ID_URL = URL_PREFIX + "/{id}";

    @Autowired
    private NoteCommentService noteCommentService;


    /**
     * @api {get} /group/note/comment/:id Find a note commend.
     * @apiParam {long} id   note commend id
     * @apiGroup NoteComment
     * @apiDescription note comment id를 통해 한 개의 note comment를 가져온다.
     */
    @RequestMapping(value = FIND_NOTECOMMENT_BY_ID_URL, method = RequestMethod.GET)
    public NoteComment findNoteCommentById(@PathVariable long id) {
        return noteCommentService.findNoteCommentById(id);
    }

    /**
     * @api {get} /group/note/comment/s/note/:noteId
     * @apiParam {long} id  note id
     * @apiGroup NoteComment
     * @apiDescription noteId를 제공하면, 해당 노트에 달려있는 모든 comment를 반환한다.
     */
    @RequestMapping(value = FIND_NOTECOMMENTS_BY_NOTE_ID_URL, method = RequestMethod.GET)
    public List<NoteComment> findNoteCommentsByNoteId(@PathVariable long noteId) {
        return noteCommentService.findNoteCommentsByNoteId(noteId);
    }

    /**
     * //TODO doc 작성
     * @api
     * @apiGroup
     * @apiDescription
     */
    // TODO URL mapping
    public NoteComment writeNoteComment(NoteComment noteComment) {
        return noteCommentService.writeNoteComment(noteComment);
    }
}
