package com.goree.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goree.api.domain.Place;
import com.goree.api.mapper.PlaceMapper;

@Service
public class PlaceService {
    @Autowired
    private PlaceMapper placeMapper;
    
    
    public Place findPlaceById(int id) {
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
