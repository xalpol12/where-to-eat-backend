package dev.xalpol12.wheretoeatbackend.controller;

import com.google.maps.errors.ApiException;
import com.google.maps.model.PlacesSearchResult;
import dev.xalpol12.wheretoeatbackend.service.PlacesService;
import dev.xalpol12.wheretoeatbackend.service.dto.LocationRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/places")
public class RequestController {
    PlacesService placesService;

    @Autowired
    public RequestController(PlacesService placesService) {
        this.placesService = placesService;
    }

    @PostMapping("/find")
    public List<PlacesSearchResult> getByLocation(@RequestBody LocationRequestDTO request)
            throws IOException, InterruptedException, ApiException {
        return List.of((placesService.findPlaceByLocation(request).results));
    }
}
