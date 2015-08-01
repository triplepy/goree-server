package com.goree.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.goree.api.domain.Tag;
import com.goree.api.service.TagService;

@RestController
public class TagController {

	@Autowired
	private TagService tagService;

	public Tag creatingTag(Tag tag) {
		return tagService.creatingTag(tag);
	}

	public Tag findTagByName(String tagName) {
		return tagService.findTagByName(tagName);
	}

	public Tag findTagById(long id) {
		return tagService.findTagById(id);
	}

	public List<Tag> searchTagsByStartWord(String startWord) {
		return tagService.searchTagsByStartWord(startWord);
	}

}
