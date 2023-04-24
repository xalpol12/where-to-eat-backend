package dev.xalpol12.wheretoeatbackend.service;

import com.google.maps.GeoApiContext;
import com.google.maps.ImageResult;
import com.google.maps.PhotoRequest;
import com.google.maps.PlacesApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.*;
import dev.xalpol12.wheretoeatbackend.configuration.GoogleApiConfig;
import dev.xalpol12.wheretoeatbackend.service.dto.LocationRequestDTO;
import dev.xalpol12.wheretoeatbackend.service.dto.PhotoRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.IOException;

@Service
public class PlacesService {
    private final GeoApiContext geoApiContext;
    private final GoogleApiConfig googleConfig;

    @Autowired
    public PlacesService(GeoApiContext geoApiContext, GoogleApiConfig googleConfig) {
        this.geoApiContext = geoApiContext;
        this.googleConfig = googleConfig;
    }

    public PlacesSearchResponse findPlaceByLocation(LocationRequestDTO request) throws IOException, InterruptedException, ApiException {
        return PlacesApi.nearbySearchQuery(geoApiContext, request.location())
                .radius(request.distance())
                .rankby(RankBy.PROMINENCE)
                .language("pl")
                .minPrice(request.minPrice())
                .maxPrice(request.maxPrice())
                .type(request.type())
                .await();
    }

    public ImageResult getPhotoUrl(PhotoRequestDTO request) throws IOException, InterruptedException, ApiException {
        return PlacesApi.photo(geoApiContext, request.photoReference())
                .maxHeight(request.height())
                .maxWidth(request.width())
                .await();
    }
}
