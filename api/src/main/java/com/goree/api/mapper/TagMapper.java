package com.goree.api.mapper;

import com.goree.api.domain.Tag;

import java.util.List;

public interface TagMapper {

	void insertTag(Tag tag);
	
	void insertTags(List<Tag> tags);

	Tag selectTagByName(String tagName);

	Tag selectTagById(long id);

	List<Tag> selectTagByStartWord(String startWords);
}
