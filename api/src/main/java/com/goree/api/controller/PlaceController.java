package com.goree.api.controller;

import com.goree.api.domain.Place;
import com.goree.api.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PlaceController {
    private static final String URL_PREFIX = "/place";
    public static final String FIND_PLACE_BY_ID_URL = URL_PREFIX + "/{id}";
    public static final String CREATE_PLACE_URL = URL_PREFIX;
    public static final String FIND_PLACE_BY_ITSELF = URL_PREFIX + "/itself";

    @Autowired
    private PlaceService placeService;

    /**
     * @api
     * @apiGroup
     * @apiDescription
     */
    @RequestMapping(value = FIND_PLACE_BY_ID_URL, method = RequestMethod.GET)
    public Place findPlaceById(@PathVariable long id) {
        return placeService.findPlaceById(id);
    }

    /**
     * @api
     * @apiGroup
     * @apiDescription
     */
    @RequestMapping(value = CREATE_PLACE_URL, method = RequestMethod.POST)
    public Place createPlace(@RequestBody Place place) {
        return placeService.createPlace(place);
    }

    /**
     * @api
     * @apiGroup
     * @apiDescription
     */
    @RequestMapping(value = FIND_PLACE_BY_ITSELF, method = RequestMethod.GET)
    public Place findPlaceByItself(@RequestBody Place place) {
        return placeService.findPlaceByItself(place);
    }
    
}
