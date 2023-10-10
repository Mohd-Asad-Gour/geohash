package com.app.geohash.Entities;

import com.app.geohash.Entities.LatLong;
import lombok.Data;
import java.util.List;

@Data
public class RouteCalculateIn {
    private List<LatLong> points;
}
