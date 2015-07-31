package com.goree.api.service;

import com.goree.api.domain.Member;
import com.goree.api.domain.Tag;
import com.goree.api.mapper.GroupMapper;
import com.goree.api.mapper.MemberMapper;
import com.goree.api.mapper.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MemberService {
    @Autowired
    private MemberMapper memberMapper;
    
    @Autowired
    private TagMapper tagMapper;
    
    @Autowired
    private GroupMapper groupMapper;
    
    public Member registerMember(Member member) {
        memberMapper.insertMember(member);
        Member registered = memberMapper.selectMemberByEmail(member.getEmail());

        if (member.getTags() != null){
            try {
                tagMapper.insertTags(member.getTags());
            } catch (DuplicateKeyException e){ }
            
           for(Tag tag : member.getTags()){
               Tag retrievedTag = tagMapper.selectTagByName(tag.getName());
               memberMapper.insertMemberHasTag(registered.getId(), retrievedTag.getId());
           }
        }
        
        return registered;
    }

    public List<Member> findMemberAll() {
        return memberMapper.selectMemberAll();
    }

    public void deleteMemberById(long id) {
        memberMapper.deleteMemberById(id);
        groupMapper.deleteGroupMemberMappingByMemberId(id);
    }

    public Member findMemberById(long id) {
        return memberMapper.selectMemberById(id);
    }
}
