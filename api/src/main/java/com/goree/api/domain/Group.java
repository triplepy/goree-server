package com.goree.api.domain;

import java.util.List;

public class Group {
    private int id;
    private Member leader;
    private String name;
    private String description;
    private List<Member> members;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Member getLeader() {
        return leader;
    }
    public void setLeader(Member leader) {
        this.leader = leader;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public List<Member> getMembers() {
        return members;
    }
    public void setMembers(List<Member> members) {
        this.members = members;
    }
    
    @Override
    public boolean equals(Object obj){
        if (!(obj instanceof Group)) 
            return false;
        
        Group group = (Group) obj;
        return this.getId() == group.getId();
    }
    
}
