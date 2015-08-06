package com.goree.api.controller;

import com.goree.api.domain.Place;
import com.goree.api.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/place")
public class PlaceController {
    @Autowired
    private PlaceService placeService;

    /**
     * @api
     * @apiGroup
     * @apiDescription
     */
    public Place findPlaceById(long id) {
        return placeService.findPlaceById(id);
    }

    /**
     * @api
     * @apiGroup
     * @apiDescription
     */
    public Place createPlace(Place place) {
        return placeService.createPlace(place);
    }

    /**
     * @api
     * @apiGroup
     * @apiDescription
     */
    public Place findPlaceByItself(Place place) {
        return placeService.findPlaceByItself(place);
    }
    
}
