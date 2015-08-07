package com.goree.api.controller;

import com.goree.api.domain.Group;
import com.goree.api.domain.Tag;
import com.goree.api.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {
    
    @Autowired
    private GroupService groupService;

    /**
     * @api {get} /member Find all of Group
     * @apiGroup Group
     * @apiDescription 등록된 모든 그룹의 리스트를 가져온다.
     */
    @RequestMapping(value="", method=RequestMethod.GET)
    public List<Group> findGroupAll() {
        return groupService.findGroupAll();
    }


    @RequestMapping(value="", method=RequestMethod.POST)
    public Group makingGroup(@RequestBody Group group) {
        return groupService.makingGroup(group);
    }

    @RequestMapping(value="/id/{id}/updateImage", method=RequestMethod.POST,consumes="multipart/form-data")
    public Group updateImage(@RequestPart MultipartFile file, @PathVariable long id){
        return groupService.updateImage(file,id);
    }


    @RequestMapping(value= "/joined/member/id/{memberId}", method=RequestMethod.GET)
    public List<Group> findGroupsJoined(@PathVariable long memberId) {
        return groupService.findRegistedGroupsByMember(memberId);
    }

    @RequestMapping(value="/id/{id}", method=RequestMethod.GET)
    public Group findGroupById(@PathVariable long id) {
        return groupService.findGroupById(id);
    }

    @RequestMapping(value="/name/{name}", method=RequestMethod.GET)
    public Group findGroupByName(@PathVariable String name) {
        return groupService.findGroupByName(name);
    }

    @RequestMapping(value = "/join/{groupId}/member/{memberId}", method=RequestMethod.GET)
    public void joinMember(@PathVariable long groupId, @PathVariable long memberId) {
        groupService.joinMember(groupId, memberId);
    }

    @RequestMapping(value="/orderByMemberCount/tagName/{tagName}")
    public List<Group> findGroupsByTagOrderByMemberCount(@PathVariable String tagName) {
        return groupService.findGroupsByTagOrderByMemberCount(tagName);
    }
}
