package com.goree.api.service;

import com.goree.api.domain.Place;
import com.goree.api.mapper.PlaceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlaceService {
    @Autowired
    private PlaceMapper placeMapper;
    
    
    public Place findPlaceById(long id) {
        return placeMapper.findPlaceById(id);
    }


    public Place createPlace(Place place) {
        placeMapper.insertPlace(place);
        return findPlaceByItself(place);
    }


    public Place findPlaceByItself(Place place) {
        return placeMapper.selectPlaceByItself(place);
    }

}
