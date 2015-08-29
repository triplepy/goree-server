package com.goree.api.controller;

import com.goree.api.domain.Place;
import com.goree.api.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlaceController {
    private static final String URL_PREFIX = "/place";
    public static final String FIND_PLACE_BY_ID = URL_PREFIX + "/{id}";
    @Autowired
    private PlaceService placeService;

    /**
     * @api
     * @apiGroup
     * @apiDescription
     */
    @RequestMapping(value = FIND_PLACE_BY_ID, method = RequestMethod.GET)
    public Place findPlaceById(@PathVariable long id) {
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
