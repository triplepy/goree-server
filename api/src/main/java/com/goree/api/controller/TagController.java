package com.goree.api.controller;

import com.goree.api.domain.Tag;
import com.goree.api.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
