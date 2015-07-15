package com.goree.api.mapper;

import java.util.List;

import com.goree.api.domain.Tag;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public interface TagMapper {

	void insertTag(Tag tag);
	
	void insertTags(List<Tag> tags);

	Tag selectTagByName(String tagName);

	Tag selectTagById(int id);

	List<Tag> selectTagByStartWord(String startWords);
}
