package dev.xalpol12.wheretoeatbackend.service.dto;

import com.google.maps.model.LatLng;
import com.google.maps.model.PlaceType;
import com.google.maps.model.PriceLevel;
import lombok.Data;

@Data
public class LocationRequestDTO {
    private LatLng location;
    private int distance;
    private PriceLevel minPrice;
    private PriceLevel maxPrice;
    private PlaceType type;
}
