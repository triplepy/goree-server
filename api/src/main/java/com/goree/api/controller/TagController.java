package com.goree.api.controller;

import com.goree.api.domain.Tag;
import com.goree.api.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tag")
public class TagController {

	@Autowired
	private TagService tagService;

	/**
	 * @api
	 * @apiGroup
	 * @apiDescription
	 */
	public Tag creatingTag(Tag tag) {
		return tagService.creatingTag(tag);
	}

	/**
	 * @api
	 * @apiGroup
	 * @apiDescription
	 */
	public Tag findTagByName(String tagName) {
		return tagService.findTagByName(tagName);
	}

	/**
	 * @api
	 * @apiGroup
	 * @apiDescription
	 */
	public Tag findTagById(long id) {
		return tagService.findTagById(id);
	}

	/**
	 * @api
	 * @apiGroup
	 * @apiDescription
	 */
	public List<Tag> searchTagsByStartWord(String startWord) {
		return tagService.searchTagsByStartWord(startWord);
	}

}
