package com.goree.api.service;

import com.goree.api.domain.Group;
import com.goree.api.domain.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 원영 on 2015-08-14.
 */
@Service
public class RecommendService {
    @Autowired
    private GroupService groupService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private TagService tagService;

    public List<Group> groupRecommendationOfMember(long memberId) {
        List<Tag> tagsOfMember = memberService.findMemberById(memberId).getTags();
        List<Group> groups = groupService.findGroupsByTags(tagsOfMember);
        return groups;
    }
}
