package com.goree.api.service;

import com.goree.api.domain.Group;
import com.goree.api.domain.Tag;
import com.goree.api.exception.ImageUploadException;
import com.goree.api.exception.ServiceException;
import com.goree.api.mapper.GroupMapper;
import com.goree.api.util.ImageUploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.google.common.base.Preconditions.*;
import static java.util.Objects.*;
import static org.apache.commons.lang3.StringUtils.*;

@Service
@Transactional
public class GroupService {


    @Autowired
    private GroupMapper groupMapper;

    @Autowired
    private ImageUploader imageUploader;

    public List<Group> findRegistedGroupsByMember(long memberId) {
        return groupMapper.selectGroupsByMemberId(memberId);
    }
    public Group addGroup(Group group) {
        checkGroupRequiredValues(group);

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
        checkArgument(tag != null, "tag is null");
        return groupMapper.selectGroupsByTagOrderByMemberCount(tag);
    }

    public List<Group> findGroupsByTagOrderByMemberCount(String tagName) {
        checkArgument(isNotBlank(tagName), "tagName cannot blank. (tagName : "+tagName+")");
        Tag tag = new Tag();
        tag.setName(tagName);
        return findGroupsByTagOrderByMemberCount(tag);
    }

    public Group updateImage(MultipartFile file, long groupId) {
        checkArgument(file != null);
        checkArgument(!file.isEmpty());

        try {
            String fileName = imageUploader.upload(file);
            groupMapper.updateImagePath(groupId, fileName);
        } catch (ImageUploadException e) {
            throw new ServiceException("Error occured while update image. (groupId : "+groupId+")", e);
        }

        return findGroupById(groupId);
    }

    public List<Group> findGroupsByTags(List<Tag> tags) {
        return groupMapper.selectGroupsByTags(tags);
    }


    private void checkGroupRequiredValues(Group group) {
        checkArgument(group != null);
        requireNonNull(group.getId(), "Group Id must not be null");
        requireNonNull(group.getLeader(), "Leader must not be null");
        requireNonNull(group.getLeader().getId(), "Leader Id must not be null");
        requireNonNull(group.getDescription(), "Description must not be null");
    }
}
