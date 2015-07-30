package com.goree.api.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Getter
@Setter
@ToString
public class Group {
    private long id;
    private Member leader;
    private String name;
    private String description;
    private List<Member> members;
    private long memberCount;

    public boolean equals(Object other){
        if(!(other instanceof Group))
            return false;
        Group group = (Group)other;

        return
                group.getLeader() == null ? getLeader() == null : group.getLeader().getId() == getLeader().getId()
             && group.getName().equals(getName());

    }
}
