package com.app.geohash.Service;

import com.app.geohash.Entities.LatLong;
import com.app.geohash.Entities.RouteCalculateIn;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;

@Slf4j
@Service
public class RouteAndFareService {

    @Autowired
    private ConversionService conversionService;



    @Autowired
    private ShortestRouteService shortestRouteService;


    public  List<String> calculate(RouteCalculateIn body) throws JsonProcessingException {
        log.info("INSIDE CALCULATE");
        Map<String, List<LatLong>> geoHashMap = new HashMap<>();
        String[] geohashSegments = conversionService.convertToGeoHashSegments(body.getPoints());
        for (int i = 0; i < geohashSegments.length; i++) {
            String geoHash = geohashSegments[i];
            LatLong latLong = body.getPoints().get(i);
            geoHashMap.computeIfAbsent(geoHash, k -> new ArrayList<>()).add(latLong);
        }
        log.info("GEOHASHMAP{}", geoHashMap);

        List<String> shortestRoute = new ArrayList<>();
        for (List<LatLong> latLongList : geoHashMap.values()) {
            String shortestRouteTsp = shortestRouteService.solveTSP(latLongList);
            shortestRoute.add(shortestRouteTsp );
        }
        return shortestRoute;
    }
}
