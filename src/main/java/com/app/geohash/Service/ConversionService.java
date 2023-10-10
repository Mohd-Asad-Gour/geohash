package com.app.geohash.Service;

import ch.hsr.geohash.GeoHash;
import com.app.geohash.Entities.LatLong;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ConversionService {

    public String[] convertToGeoHashSegments(List<LatLong> points) {
        log.info("Inside GeoHash");
        String[] geohashSegments= new String[points.size()];
        for(int i=0;i< points.size();i++){
            double latitude=points.get(i).getLatitude();
            double longitude=points.get(i).getLongitude();
            GeoHash geoHash = GeoHash.withCharacterPrecision(latitude, longitude, 6);
            geohashSegments[i] = geoHash.toBase32();
        }
        return geohashSegments;
    }

//    static LatLong getLatLongFromGeohash(String geohash) {
//        int precision = geohash.length();
//        double minLat = -90.0;
//        double maxLat = 90.0;
//        double minLon = -180.0;
//        double maxLon = 180.0;
//
//        String BASE32 = "0123456789bcdefghjkmnpqrstuvwxyz";
//        boolean isEven = true; // Check if it's an even or odd character
//        double mid;
//
//        for (int i = 0; i < precision; i++) {
//            char c = geohash.charAt(i);
//            int cd = BASE32.indexOf(c); // Get the index of the character in the BASE32 encoding
//
//            // Iterate through bits of the character
//            for (int j = 4; j >= 0; j--) {
//                int mask = 1 << j;
//                if (isEven) {
//                    mid = (maxLon + minLon) / 2.0;
//                    if ((cd & mask) != 0) {
//                        minLon = mid;
//                    } else {
//                        maxLon = mid;
//                    }
//                } else {
//                    mid = (maxLat + minLat) / 2.0;
//                    if ((cd & mask) != 0) {
//                        minLat = mid;
//                    } else {
//                        maxLat = mid;
//                    }
//                }
//                isEven = !isEven;
//            }
//        }
//
//        double latitude = (minLat + maxLat) / 2.0;
//        double longitude = (minLon + maxLon) / 2.0;
//        LatLong latLong= new LatLong();
//        latLong.setLatitude(latitude);
//        latLong.setLongitude(longitude);
//        return latLong;
//    }
//
//    boolean isWithinSameGeoHashPrecision6(String coordinate1, String coordinate2) {
//        String geoHash1 = calculateGeoHashWithPrecision(coordinate1, 6);
//        String geoHash2 = calculateGeoHashWithPrecision(coordinate2, 6);
//
//        return geoHash1.equals(geoHash2);
//    }
//    private String calculateGeoHashWithPrecision(String coordinate, int precision) {
//        String[] latLon = coordinate.split(",");
//        double latitude = Double.parseDouble(latLon[0]);
//        double longitude = Double.parseDouble(latLon[1]);
//        GeoHash geoHash = GeoHash.withCharacterPrecision(latitude, longitude, precision);
//        return geoHash.toBase32();
//    }
}
