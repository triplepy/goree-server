package com.goree.api.controller;

import com.goree.api.domain.Place;
import com.goree.api.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlaceController {
    @Autowired
    private PlaceService placeService;
    
    public Place findPlaceById(long id) {
        return placeService.findPlaceById(id);
    }

    public Place createPlace(Place place) {
        return placeService.createPlace(place);
    }

    public Place findPlaceByItself(Place place) {
        return placeService.findPlaceByItself(place);
    }
    
}
