package com.app.geohash.Service;

import com.app.geohash.Entities.LatLong;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Deprecated
@Slf4j
@Service
public class DistanceCalculationService {


    @Autowired
    private static ConversionService conversionService;


    public double calculateDistanceBetweenTwoLatLong(LatLong latLon1, LatLong latLon2) {
        double latitude1=latLon1.getLatitude();
        double longitude1=latLon1.getLongitude();
        double latitude2=latLon2.getLatitude();
        double longitude2=latLon2.getLongitude();
        return calculateDistance(latitude1, longitude1,latitude2, longitude2);

    }

    public static double calculateDistance(double latitude1, double longitude1, double latitude2, double longitude2) {

        double lat1 = Math.toRadians(latitude1);
        double lon1 = Math.toRadians(longitude1);
        double lat2 = Math.toRadians(latitude2);
        double lon2 = Math.toRadians(longitude2);

        double dlat = lat2 - lat1;
        double dlon = lon2 - lon1;

        double a = Math.pow(Math.sin(dlat / 2), 2) +
                Math.cos(lat1) * Math.cos(lat2) *
                        Math.pow(Math.sin(dlon / 2), 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double earthradius = 6371;
        return earthradius * c;
    }


//    double calculateTotalDistance( List<LatLong> points) {
//        log.info("POINTS{}",points);
//        double totalDistance = 0.0;
//
//        log.info("Inside CalculateTotalDistanceOUTSIDELOOP");
//        if(points.size()-1>0) {
//            log.info("Inside CalculateTotalDistanceCondition");
//            for (int i = 0; i < points.size() - 1; i++) {
//                log.info("Inside CalculateTotalDistanceINSIDELOOP");
//                LatLong latLong1 = points.get(i);
//                LatLong latLong2 = points.get(i + 1);
//                double distance = calculateDistanceBetweenTwoLatLong(latLong1, latLong2);
//                totalDistance += distance;
//            }
//            return totalDistance;
//        }
//       return 0.0;
//    }
//    public static double calculateDistanceBetweenTwoPoints(String segment1,String segment2) {
//        LatLong latLon1 = conversionService.getLatLongFromGeohash(segment1);
//        LatLong latLon2 = conversionService.getLatLongFromGeohash(segment2);
//        double latitude1=latLon1.getLatitude();
//        double longitude1=latLon1.getLongitude();
//        double latitude2=latLon2.getLatitude();
//        double longitude2=latLon2.getLongitude();
//
//        return calculateDistance(latitude1, longitude1,latitude2, longitude2);
//    }
//
//    List<String[]> groupSegmentsByValue(String[] geohashSegments) {
//        Map<String, List<String>> segmentsMap = new HashMap<>();
//
//        for (String segment : geohashSegments) {
//            segmentsMap.computeIfAbsent(segment, k -> new ArrayList<>()).add(segment);
//        }
//
//        List<String[]> groupedSegments = new ArrayList<>();
//        for (List<String> group : segmentsMap.values()) {
//            groupedSegments.add(group.toArray(new String[0]));
//        }
//
//        return groupedSegments;
//    }
//


}
