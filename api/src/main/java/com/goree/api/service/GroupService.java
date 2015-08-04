package com.goree.api.service;

import com.goree.api.domain.Group;
import com.goree.api.domain.Member;
import com.goree.api.domain.Tag;
import com.goree.api.mapper.GroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class GroupService {
    @Value("${file.upload.path}")
    private String staticPath;

    @Autowired
    private GroupMapper groupMapper; 
    public List<Group> findRegistedGroupsByMember(Member member) {
        return groupMapper.selectGroupsByMemberId(member);
    }
    public Group makingGroup(Group group) {
        groupMapper.insertGroup(group);
        Group makedGroup = groupMapper.selectGroupByName(group.getName());
        return makedGroup;
    }
    public Group findGroupById(long id) {
        return groupMapper.selectGroupById(id);
    }
    public List<Group> findGroupAll() {
        return groupMapper.selectGroupAll();
    }
    public Group findGroupByName(String name) {
        return groupMapper.selectGroupByName(name);
    }
    public void joinMember(long groupId, long memberId) {
        groupMapper.insertToGroupHasMember(groupId, memberId);
    }

    public List<Group> findGroupsByTagOrderByMemberCount(Tag tag) {
        return groupMapper.selectGroupsByTagOrderByMemberCount(tag);
    }

    public Group updateImage(MultipartFile file, long id) {
        if (!file.isEmpty()){
            String fileName = new Date().getTime() + file.getOriginalFilename();
            try {
                if(staticPath != null) {
                    File imagePath = new File(staticPath + fileName);
                    file.transferTo(imagePath);
                    groupMapper.updateImagePath(id, fileName);
                } else {
                    return null;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return findGroupById(id);
    }
}
