package dev.xalpol12.wheretoeatbackend.controller;

import com.google.maps.errors.ApiException;
import dev.xalpol12.wheretoeatbackend.service.PlacesService;
import dev.xalpol12.wheretoeatbackend.service.dto.PhotoResponseDTO;
import dev.xalpol12.wheretoeatbackend.service.dto.PlaceRequestDTO;
import dev.xalpol12.wheretoeatbackend.service.dto.PhotoRequestDTO;
import dev.xalpol12.wheretoeatbackend.service.dto.PlaceResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/places")
public class PlacesController {
    PlacesService placesService;

    @Autowired
    public PlacesController(PlacesService placesService) {
        this.placesService = placesService;
    }

    @PostMapping("/find")
    public List<PlaceResponseDTO> getByLocation(@RequestBody PlaceRequestDTO request)
            throws InterruptedException, ApiException, IOException {
        return placesService.findPlaceByLocation(request);
    }

    @PostMapping("/image")
    public PhotoResponseDTO getImage(@RequestBody PhotoRequestDTO request)
            throws IOException, InterruptedException, ApiException {
        return placesService.getPhotoUrl(request);
    }
}
