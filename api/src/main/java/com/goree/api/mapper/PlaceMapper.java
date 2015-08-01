package com.goree.api.mapper;

import com.goree.api.domain.Place;

public interface PlaceMapper {
    Place findPlaceById(long id);
    
    void insertPlace(Place place);
    
    Place selectPlaceByItself(Place place);
}
