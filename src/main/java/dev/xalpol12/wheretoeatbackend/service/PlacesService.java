package dev.xalpol12.wheretoeatbackend.service;

import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.PlaceType;
import com.google.maps.model.PlacesSearchResponse;
import com.google.maps.model.PriceLevel;
import com.google.maps.model.RankBy;
import dev.xalpol12.wheretoeatbackend.service.dto.LocationRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PlacesService {
    GeoApiContext geoApiContext;

    @Autowired
    public PlacesService(GeoApiContext geoApiContext) {
        this.geoApiContext = geoApiContext;
    }

    public PlacesSearchResponse findPlaceByLocation(LocationRequestDTO request) throws IOException, InterruptedException, ApiException {
        PlacesSearchResponse results = PlacesApi.nearbySearchQuery(geoApiContext, request.getLocation())
                .radius(request.getDistance())
                .rankby(RankBy.PROMINENCE)
                .language("pl")
                .minPrice(request.getMinPrice())
                .maxPrice(request.getMaxPrice())
                .type(request.getType())
                .await();
        System.out.println(results.toString());
        return results;
    }
}
