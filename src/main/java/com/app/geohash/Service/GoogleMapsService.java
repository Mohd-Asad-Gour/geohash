package com.app.geohash.Service;

import com.app.geohash.Entities.DistanceMatrixResponse;
import com.app.geohash.Entities.LatLong;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class GoogleMapsService {

    @Value("${google.maps.api.key}")
    private String googleMapsApiKey;



    public double calculateDistanceBetweenTwoPoints(LatLong latLon1, LatLong latLon2) throws JsonProcessingException {
        log.info("Loop3");
        double latitude1= latLon1.getLatitude();
        double longitude1= latLon1.getLongitude();
        double latitude2= latLon2.getLatitude();
        double longitude2= latLon2.getLongitude();
        String url = "https://maps.googleapis.com/maps/api/distancematrix/json" +
                "?origins=" + latitude1 + "," + longitude1 +
                "&destinations=" + latitude2 + "," + longitude2 +
                "&key=" + googleMapsApiKey;


        RestTemplate restTemplate = new RestTemplate();
        String jsonResponse = restTemplate.getForObject(url, String.class);

            // Deserialize the JSON response into DistanceMatrixResponse
            ObjectMapper objectMapper = new ObjectMapper();
        DistanceMatrixResponse response = null;
            response = objectMapper.readValue(jsonResponse, DistanceMatrixResponse.class);


        // Check if the response contains rows and elements
            if (response != null && response.getRows() != null && response.getRows().length > 0 &&
                    response.getRows()[0].getElements() != null && response.getRows()[0].getElements().length > 0) {
                // Extract the distance from the response
                double distance = response.getRows()[0].getElements()[0].getDistance().getValue();
                log.info("Distance{}", distance);
                return distance;
            } else {
                return -1.0;

        }
    }
}


