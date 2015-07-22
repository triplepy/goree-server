package com.goree.api.mapper;

import com.goree.api.domain.Place;

public interface PlaceMapper {
    public Place findPlaceById(int id);
    
    public void insertPlace(Place place);
    
    public Place selectPlaceByItself(Place place);
}
