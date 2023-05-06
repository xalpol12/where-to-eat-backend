package dev.xalpol12.wheretoeatbackend.service.dto;

import com.google.maps.model.LatLng;
import com.google.maps.model.PlaceType;
import com.google.maps.model.PriceLevel;

public record PlaceRequestDTO(
    LatLng location,
    int distance,
    PriceLevel minPrice,
    PriceLevel maxPrice,
    PlaceType placeType
) {}
