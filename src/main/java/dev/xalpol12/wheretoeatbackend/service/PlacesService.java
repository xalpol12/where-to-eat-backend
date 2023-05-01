package dev.xalpol12.wheretoeatbackend.service;

import com.google.maps.GeoApiContext;
import com.google.maps.ImageResult;
import com.google.maps.PlacesApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.*;
import dev.xalpol12.wheretoeatbackend.service.dto.PhotoResponseDTO;
import dev.xalpol12.wheretoeatbackend.service.dto.PlaceRequestDTO;
import dev.xalpol12.wheretoeatbackend.service.dto.PhotoRequestDTO;
import dev.xalpol12.wheretoeatbackend.service.dto.PlaceResponseDTO;
import dev.xalpol12.wheretoeatbackend.service.mapper.PhotoMapper;
import dev.xalpol12.wheretoeatbackend.service.mapper.PlaceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class PlacesService {
    private final GeoApiContext geoApiContext;
    private final PlaceMapper placeMapper;
    private final PhotoMapper photoMapper;

    @Autowired
    public PlacesService(GeoApiContext geoApiContext, PlaceMapper placeMapper, PhotoMapper photoMapper) {
        this.geoApiContext = geoApiContext;
        this.placeMapper = placeMapper;
        this.photoMapper = photoMapper;
    }

    public List<PlaceResponseDTO> findPlaceByLocation(PlaceRequestDTO request) throws IOException, InterruptedException, ApiException {
        PlacesSearchResult[] response =  PlacesApi
                .nearbySearchQuery(geoApiContext, request.location())
                .radius(request.distance())
                .rankby(RankBy.PROMINENCE)
                .language("pl")
                .minPrice(request.minPrice())
                .maxPrice(request.maxPrice())
                .type(request.type())
                .await().results;
        return placeMapper.placesSearchResultArrayToPlaceResponseDTOList(response);
    }

    public PhotoResponseDTO getPhotoUrl(PhotoRequestDTO request) throws IOException, InterruptedException, ApiException {
        ImageResult imageResult = PlacesApi
                .photo(geoApiContext, request.photoReference())
                .maxHeight(request.height())
                .maxWidth(request.width())
                .await();
        return  photoMapper.imageResultToPhotoResponseDTO(imageResult);
    }
}
