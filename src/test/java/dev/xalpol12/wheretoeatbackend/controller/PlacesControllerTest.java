package dev.xalpol12.wheretoeatbackend.controller;

import com.google.maps.errors.ApiException;
import com.google.maps.model.LatLng;
import com.google.maps.model.PlaceType;
import com.google.maps.model.PriceLevel;
import dev.xalpol12.wheretoeatbackend.service.PlacesService;
import dev.xalpol12.wheretoeatbackend.service.dto.PhotoRequestDTO;
import dev.xalpol12.wheretoeatbackend.service.dto.PhotoResponseDTO;
import dev.xalpol12.wheretoeatbackend.service.dto.PlaceRequestDTO;
import dev.xalpol12.wheretoeatbackend.service.dto.PlaceResponseDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = PlacesController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class PlacesControllerTest {
    @MockBean
    private PlacesService placesService;

    private PlaceRequestDTO createPlaceRequestDTO() {
        LatLng location = new LatLng(52, 16);
        PriceLevel minPrice = PriceLevel.INEXPENSIVE;
        PriceLevel maxPrice = PriceLevel.EXPENSIVE;
        PlaceType type = PlaceType.RESTAURANT;
        return new PlaceRequestDTO(location, 5000, minPrice, maxPrice, type);
    }

    private PhotoRequestDTO createPhotoRequestDTO() {
        return new PhotoRequestDTO("", 1, 1);
    }

    @Test
    void givenPlaceRequestDTO_controllerReturnsPlaceResponseDTO() throws IOException, InterruptedException, ApiException {
        //given
        PlaceRequestDTO requestDTO = createPlaceRequestDTO();
        PlaceResponseDTO responseDTO = new PlaceResponseDTO();
        List<PlaceResponseDTO> responseDTOList = List.of(responseDTO, responseDTO);

        //when
        when(placesService.findPlaceByLocation(requestDTO)).thenReturn(responseDTOList);
        List<PlaceResponseDTO> receivedList = placesService.findPlaceByLocation(requestDTO);

        //then
        Assertions.assertThat(receivedList).isNotNull();
        assertEquals(receivedList, responseDTOList);
    }

    @Test
    void givenPhotoRequestDTO_controllerReturnsPhotoResponseDTO() throws IOException, InterruptedException, ApiException {
        //given
        PhotoRequestDTO requestDTO = createPhotoRequestDTO();
        PhotoResponseDTO responseDTO = new PhotoResponseDTO();

        //when
        when(placesService.getPhotoUrl(requestDTO)).thenReturn(responseDTO);
        PhotoResponseDTO received = placesService.getPhotoUrl(requestDTO);

        //then
        Assertions.assertThat(received).isNotNull();
        assertEquals(received, responseDTO);
    }
}