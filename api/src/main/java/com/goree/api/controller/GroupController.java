package com.goree.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.goree.api.domain.Group;
import com.goree.api.domain.Member;
import com.goree.api.service.GroupService;

@RestController
@RequestMapping("/group")
public class GroupController {
    
    @Autowired
    private GroupService groupService;
    
    @RequestMapping(value="/", method=RequestMethod.GET)
    public List<Group> findGroupAll() {
        return groupService.findGroupAll();
    }

    @RequestMapping(value="/", method=RequestMethod.POST)
    public Group makingGroup(Group group) {
        return groupService.makingGroup(group);
    }

    @RequestMapping(value="/joined", method=RequestMethod.GET)
    public List<Group> findGroupsJoined(Member member) {
        return groupService.findRegistedGroupsByMember(member);
    }

    @RequestMapping(value="/id/{id}", method=RequestMethod.GET)
    public Group findGroupById(@PathVariable int id) {
        return groupService.findGroupById(id);
    }

    @RequestMapping(value="/name/{name}", method=RequestMethod.GET)
    public Group findGroupByName(@PathVariable String name) {
        return groupService.findGroupByName(name);
    }

    public void joinMember(int groupId, int memberId) {
        groupService.joinMember(groupId, memberId);
    }

}
