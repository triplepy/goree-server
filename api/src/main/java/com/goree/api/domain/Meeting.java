package com.goree.api.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class Meeting {
    private long id;
    private String title;
    private Group group;
    private Place place;
    private Member promoter;
    private Date date;
    private String description;
    
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Meeting))
            return false;
        Meeting meeting = (Meeting) other;
        return meeting.getTitle().equals(title) 
        && meeting.getGroup().getId() == group.getId()
        && meeting.getGroup().getName().equals(group.getName())
        && meeting.getDate().equals(date)
        && meeting.getPromoter().getId() == promoter.getId()
        && meeting.getPromoter().getNickname().equals(promoter.getNickname())
        && meeting.getPlace().getId() == place.getId();        
    }
}
