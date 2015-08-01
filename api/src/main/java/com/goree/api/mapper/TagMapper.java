package com.goree.api.mapper;

import java.util.List;

import com.goree.api.domain.Tag;

public interface TagMapper {

	void insertTag(Tag tag);
	
	void insertTags(List<Tag> tags);

	Tag selectTagByName(String tagName);

	Tag selectTagById(long id);

	List<Tag> selectTagByStartWord(String startWords);
}
