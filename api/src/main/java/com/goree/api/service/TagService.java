package com.goree.api.service;

import com.goree.api.domain.Tag;
import com.goree.api.mapper.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

	@Autowired
	private TagMapper tagMapper;

	public Tag creatingTag(Tag tag) {
		tagMapper.insertTag(tag);
		return tagMapper.selectTagByName(tag.getName());
	}

	public Tag findTagByName(String tagName) {
		return tagMapper.selectTagByName(tagName);
	}

	public Tag findTagById(long id) {
		return tagMapper.selectTagById(id);
	}

	public List<Tag> searchTagsByStartWord(String startWord) {
		return tagMapper.selectTagByStartWord(startWord);
	}

}
