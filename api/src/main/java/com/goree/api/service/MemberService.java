package com.goree.api.service;

import com.goree.api.domain.Member;
import com.goree.api.domain.Tag;
import com.goree.api.mapper.GroupMapper;
import com.goree.api.mapper.MemberMapper;
import com.goree.api.mapper.TagMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class MemberService {

    @Value("${file.upload.path}")
    private String staticPath;

    @Autowired
    private MemberMapper memberMapper;
    
    @Autowired
    private TagMapper tagMapper;
    
    @Autowired
    private GroupMapper groupMapper;
    
    public Member registerMember(Member member) {
        if (StringUtils.isBlank(member.getFacebookUserId())) {
            throw new IllegalArgumentException("facebook user id is blank.");
        }

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

    public Member findMemberByFacebookUserId(String facebookUserId) {
        return memberMapper.selectMemberByFacebookUserId(facebookUserId);
    }

    public Member updateImage(long id, MultipartFile multipartFile) {
        if (!multipartFile.isEmpty()){
            String fileName = new Date().getTime() + multipartFile.getOriginalFilename();
            try {
                if(staticPath != null) {
                    File imagePath = new File(staticPath + fileName);
                    multipartFile.transferTo(imagePath);
                    memberMapper.updateImagePath(id, fileName);
                } else {
                    return null;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return findMemberById(id);

    }
}
