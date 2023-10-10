package com.app.geohash.Service;

import com.app.geohash.Entities.LatLong;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class ShortestRouteService {


    @Autowired
    private GoogleMapsService googleMapsService;



//        public String solveTSPAgain(List<LatLong> latLongs) throws JsonProcessingException {
//        if (latLongs.isEmpty()) {
//            return formatRoute(Collections.emptyList());
//        }
//
//        List<LatLong> bestRoute = null;
//        double shortestDistance = Double.MAX_VALUE;
//
//        // Generate all permutations of the points
//        List<List<LatLong>> permutations = permute(latLongs);
//
//        // Calculate the total distance for each permutation
//        for (List<LatLong> permutation : permutations) {
//            double totalDistance = calculateTotalDistance(permutation);
//            if (totalDistance < shortestDistance) {
//                shortestDistance = totalDistance;
//                bestRoute = permutation;
//            }
//
//        }
//
//        // Add the starting point to complete the route
//        if (bestRoute != null) {
//            bestRoute.add(bestRoute.get(0));
//        }
//
//        return formatRoute(bestRoute != null ? bestRoute : Collections.emptyList());
//    }
//    private List<List<LatLong>> permute(List<LatLong> latLongs) {
//        List<List<LatLong>> permutations = new ArrayList<>();
//        permute(latLongs, 0, permutations);
//        return permutations;
//    }
//    private double calculateTotalDistance(List<LatLong> route) throws JsonProcessingException {
//        double totalDistance = 0.0;
//        for (int i = 0; i < route.size() - 1; i++) {
//            LatLong current = route.get(i);
//            LatLong next = route.get(i + 1);
//            totalDistance += googleMapsService.calculateDistanceBetweenTwoPoints(current, next);
//        }
//        return totalDistance;
//    }
//
//
//    private void permute(List<LatLong> latLongs, int start, List<List<LatLong>> result) {
//        if (start == latLongs.size()) {
//            result.add(new ArrayList<>(latLongs));
//        } else {
//            for (int i = start; i < latLongs.size(); i++) {
//                Collections.swap(latLongs, start, i);
//                permute(latLongs, start + 1, result);
//                Collections.swap(latLongs, start, i);
//            }
//        }
//    }
//
    public String solveTSP(List<LatLong> latLongs) throws JsonProcessingException {
        List<LatLong> route = new ArrayList<>();
        List<LatLong> unvisited = new ArrayList<>(latLongs);
        if(latLongs.size()>1) {
                // Start from the first point
                LatLong current = latLongs.get(0);
                route.add(current);
                unvisited.remove(current);
                log.info("LOOP1");
                while (!unvisited.isEmpty()) {
                    LatLong nearestNeighbor = null;
                    double minDistance = Double.MAX_VALUE;

                    for (LatLong point : unvisited) {
                        log.info("LOOP2");
                        double distance = googleMapsService.calculateDistanceBetweenTwoPoints(current, point);
                        if (distance < minDistance) {
                            minDistance = distance;
                            nearestNeighbor = point;
                        }
                    }
                    if (nearestNeighbor != null) {
                        route.add(nearestNeighbor);
                        current = nearestNeighbor;
                        unvisited.remove(nearestNeighbor);
                    }
            }
            if (!route.isEmpty()) {
                route.add(route.get(0));
            }
            return formatRoute(route);
        }
        else{
            route.add(latLongs.get(0));
            return formatRoute(route);
        }
    }

    private String formatRoute(List<LatLong> route) {
        StringBuilder formattedRoute = new StringBuilder();

        for (LatLong point : route) {
            formattedRoute.append(point.getLatitude()).append(",");
            formattedRoute.append(point.getLongitude()).append("->");
        }

        if (formattedRoute.length() > 0) {
            formattedRoute.deleteCharAt(formattedRoute.length() - 1);
            formattedRoute.deleteCharAt(formattedRoute.length() - 1);
        }

        return formattedRoute.toString();
    }


//    public String findShortestRouteWithTSP(List<LatLong> latLong) throws JsonProcessingException {
//        // Create a list to store the route
//        List<LatLong> route = new ArrayList<>();
//
//        // Create a set to keep track of visited points
//        Set<LatLong> visited = new HashSet<>();
//        if(latLong.size()>1) {
//            // Start from the first point
//            LatLong current = latLong.get(0);
//            route.add(current);
//            visited.add(current);
//            log.info("ROUTE{}", route);
//            while (visited.size() < latLong.size()) {
//                LatLong nearestNeighbor = null;
//                double minDistance = Double.MAX_VALUE;
//                log.info("LOOP1");
//                for (LatLong point : latLong) {
//                    log.info("LOOP2");
//                    if (!visited.contains(point)) {
//                        log.info("LOOP3");
//                        double distance=googleMapsService.calculateDistanceBetweenTwoPoints(current, point);
//                        log.info("DISTANCE{}",distance);
//                        if (distance < minDistance) {
//                            log.info("IFELSELOOP");
//                            minDistance = distance;
//                            nearestNeighbor = point;
//                        }
//                    }
//                }
//
//                if (nearestNeighbor != null) {
//                    route.add(nearestNeighbor);
//                    visited.add(nearestNeighbor);
//                    current = nearestNeighbor;
//                }
//            }
//
//            // Add the starting point to complete the route
//            route.add(route.get(0));
//
//            return formatRoute(route);
//        } else {
//            route.add(latLong.get(0));
//            return formatRoute(route);
//        }
//    }
}
