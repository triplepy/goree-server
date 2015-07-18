package com.goree.api.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(exclude={"count", "provided"})
public class Tag {
	private int id;
	private String tagName;
	char provided;
	int count;
}
