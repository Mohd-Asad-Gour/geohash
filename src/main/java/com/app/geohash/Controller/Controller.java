package com.app.geohash.Controller;

import com.app.geohash.Entities.RouteCalculateIn;
import com.app.geohash.Service.RouteAndFareService;
import com.app.geohash.Service.ShortestRouteCallback;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class Controller {

    @Autowired
    private RouteAndFareService service;

    @PostMapping("/calculate")
    List<String> calculate(@RequestBody RouteCalculateIn body) throws JsonProcessingException {
       log.info("Inside CONTROLLER");
        List<String> response= service.calculate(body);
        log.info("RESPONSE{}",response);
      return response;
    }

//    @PostMapping("/calculate")
//    List<String> calculate(@RequestBody RouteCalculateIn body) throws JsonProcessingException {
//        List<String> response = new ArrayList<>();
//
//        // Define a callback to handle the shortest route result
//        ShortestRouteCallback callback = new ShortestRouteCallback() {
//            @Override
//            public void onShortestRouteFound(String route) {
//                response.add(route);
//            }
//        };
//
//        shortestRouteService.calculate(body, callback);
//
//        return response;
//    }
}