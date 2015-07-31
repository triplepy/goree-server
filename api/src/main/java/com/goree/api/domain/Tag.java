package com.goree.api.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(exclude={"count", "provided"})
public class Tag {
	private long id;
	private String name;
	char provided;
	int count;
}
