package com.goree.api.controller;

import com.goree.api.domain.Group;
import com.goree.api.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class GroupController {
    private static final String URL_PREFIX = "/group";
    public static final String FIND_GROUP_ALL_URL = URL_PREFIX;
    public static final String ADD_GROUP_URL = URL_PREFIX;
    public static final String UPDATE_IMAGE_URL = URL_PREFIX+"/id/{id}/updateImage";
    public static final String FIND_GROUPS_JOINED_URL = URL_PREFIX+"/joined/member/id/{memberId}";
    public static final String FIND_GROUP_BY_ID_URL = URL_PREFIX+"/id/{id}";
    public static final String FIND_GROUP_BY_NAME_URL = URL_PREFIX+"/name/{name}";
    public static final String JOIN_MEMBER_URL = URL_PREFIX+"/join/{groupId}/member/{memberId}";
    public static final String FIND_GROUPS_BY_TAG_ORDERBY_MEMBERCOUNT_URL = URL_PREFIX+"/orderByMemberCount/tagName/{tagName}";

    @Autowired
    private GroupService groupService;

    /**
     * @api {get} /group Get all of Group
     * @apiName Find All of Group
     * @apiGroup Group
     * @apiDescription 등록된 모든 그룹의 리스트를 가져온다.
     */
    @RequestMapping(value=FIND_GROUP_ALL_URL, method=RequestMethod.GET)
    public List<Group> findGroupAll() {
        return groupService.findGroupAll();
    }


    /**
     * @api {post} /group Make a Group
     * @apiName Make a Group
     * @apiGroup Group
     * @apiDescription Group의 정보를 받아서 등록한다.
     */
    @RequestMapping(value= ADD_GROUP_URL, method=RequestMethod.POST)
    public Group addGroup(@RequestBody Group group) {
        return groupService.addGroup(group);
    }


    /**
     * @api {post} /group/id/:id/updateImage Update cover image
     * @apiName Update group cover image
     * @apiGroup Group
     * @apiParam {file} imageFile for member profile
     * @apiParam {Number} groupId group ID (sequence)
     * @apiDescription Group ID에 해당되는 그룹의 이미지를 등록혹은 변경한다.
     */
    @RequestMapping(value= UPDATE_IMAGE_URL, method=RequestMethod.POST,consumes="multipart/form-data")
    public Group updateImage(@RequestPart MultipartFile file, @PathVariable long id){
        return groupService.updateImage(file, id);
    }

    /**
     * @api {get} /group/joined/member/id/:memberId Get Groups by Member ID
     * @apiName Get Groups by Member ID
     * @apiGroup Group
     * @apiParam {Number} memberId  Member ID(sequence)
     * @apiSampleRequest /group/joined/member/id/1
     * @apiDescription Member ID에 해당되는 회원이 가입한 그룹리스트를 가져온다.
     */
    @RequestMapping(value= FIND_GROUPS_JOINED_URL, method=RequestMethod.GET)
    public List<Group> findGroupsJoined(@PathVariable long memberId) {
        return groupService.findRegistedGroupsByMember(memberId);
    }


    /**
     * @api {get} /group/id/:id Get a Group by GroupID
     * @apiName Get a Group by GroupID
     * @apiGroup Group
     * @apiParam {Number} id    Group ID(sequence)
     * @apiSampleRequest /group/id/1
     * @apiDescription Group ID에 해당되는 그룹의 정보를 가져온다.
     */
    @RequestMapping(value= FIND_GROUP_BY_ID_URL, method=RequestMethod.GET)
    public Group findGroupById(@PathVariable long id) {
        return groupService.findGroupById(id);
    }

    /**
     * @api {get} /group/name/:name Get a Group By Group Name
     * @apiName Get a Group By Group Name
     * @apiGroup Group
     * @apiParam {String} name  Group Name (title)
     * @apiSampleRequest /group/name/ThisIsGroup
     * @apiDescription Group 이름에 해당되는 그룹의 정보를 가져온다.
     */
    @RequestMapping(value= FIND_GROUP_BY_NAME_URL, method=RequestMethod.GET)
    public Group findGroupByName(@PathVariable String name) {
        return groupService.findGroupByName(name);
    }


    /**
     * @api {put} /group/join/:groupId/member/:memberId Join a Group
     * @apiName Join a Group
     * @apiGroup Group
     * @apiParam {Number} groupId   Group ID(sequence)
     * @apiParam {Number} memberId  Member ID(sequence)
     * @apiSampleRequest /group/join/1/member/2
     * @apiDescription Group ID에 해당되는 그룹에, Member ID에 해당되는 멤버를 가입시킨다.
     */
    @RequestMapping(value = JOIN_MEMBER_URL, method=RequestMethod.PUT)
    public void joinMember(@PathVariable long groupId, @PathVariable long memberId) {
        groupService.joinMember(groupId, memberId);
    }


    /**
     * @api {get} /group/orderByMemberCount/tagName/:tagName Get Groups with Tag Name
     * @apiName Search Group with Tag Name Order By Member count
     * @apiGroup Group
     * @apiParam {String} tagName     Tag Name
     * @apiSampleRequest /group/orderByMemberCount/tagName/ThisIsTag
     * @apiDescription 태그 이름을 받아서 해당태그를 가진 그룹들을 멤버수를 기준으로 내림차순으로 가져온다.
     */
    @RequestMapping(value= FIND_GROUPS_BY_TAG_ORDERBY_MEMBERCOUNT_URL, method=RequestMethod.GET)
    public List<Group> findGroupsByTagOrderByMemberCount(@PathVariable String tagName) {
        return groupService.findGroupsByTagOrderByMemberCount(tagName);
    }
}
