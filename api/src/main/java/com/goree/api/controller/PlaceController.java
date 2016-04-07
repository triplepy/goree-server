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
     * @api {get} /place/:id Find a place by id
     * @apiParam {long} id   place id
     * @apiGroup Place
     * @apiDescription id를 이용해 한 개의 place를 받아온다.
     */
    @RequestMapping(value = FIND_PLACE_BY_ID_URL, method = RequestMethod.GET)
    public Place findPlaceById(@PathVariable long id) {
        return placeService.findPlaceById(id);
    }

    /**
     * @api {post} /place
     * @apiGroup Place
     * @apiDescription 등록을 원하는 place를 json 형태로 body에 실어서 보내면 된다. 등록된 place가 응답으로 돌아온다.
     */
    @RequestMapping(value = CREATE_PLACE_URL, method = RequestMethod.POST)
    public Place createPlace(@RequestBody Place place) {
        return placeService.createPlace(place);
    }

    /**
     * @api {get} /place/itself
     * @apiGroup Place
     * @apiDescription place의 json object 형태로 body에 넣어주도록 하되,
     *                 검색을 원하는 항목만 적도록 한다. 모든 값은 equal 매칭이다. like 매칭이 아님.
     */
    @RequestMapping(value = FIND_PLACE_BY_ITSELF, method = RequestMethod.GET)
    public Place findPlaceByItself(@RequestBody Place place) {
        return placeService.findPlaceByItself(place);
    }
    
}
