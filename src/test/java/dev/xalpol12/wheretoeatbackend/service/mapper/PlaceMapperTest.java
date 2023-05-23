package dev.xalpol12.wheretoeatbackend.service.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.maps.model.LatLng;
import com.google.maps.model.PlacesSearchResult;
import dev.xalpol12.wheretoeatbackend.service.dto.PlaceResponseDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlaceMapperTest {
    private final String JSON_PLACE_RESULT_PATH = Paths
            .get("src/test/resources/json/places/places_search_single_result.json").toAbsolutePath().toString();

    private static PlaceMapper mapper;
    private static ObjectMapper objectMapper;

    @BeforeAll
    static void setup() {
        mapper = new PlaceMapper();
        objectMapper = new ObjectMapper();
    }

    private PlacesSearchResult createPlaceSearchResult() throws IOException {
        return objectMapper.readValue(new FileReader(JSON_PLACE_RESULT_PATH), PlacesSearchResult.class);
    }

    private PlacesSearchResult[] createPlaceSearchResultArray() throws IOException {
        return new PlacesSearchResult[]{createPlaceSearchResult(), createPlaceSearchResult()};
    }

    private PlaceResponseDTO createPlaceResponseDTO() {
        return PlaceResponseDTO
                .builder()
                .name("Brovaria")
                .placeId("ChIJ1ZUWpDhbBEcR--j66OkzlXw")
                .location(new LatLng(52f, 16f))
                .vicinity("Stary Rynek 73-74, Poznań")
                .rating(4.4f)
                .userRatingsTotal(2227)
                .openNow(false)
                .photoReference("AUjq9jnFGpt4i-U044P62Zhm85Zc0QEPTpdviyxqQAAyvOIeBEBiiNotairw0j5Sp0IN_" +
                        "Zm1Yp2TlpAG28s1mLjTmYwamwaNlS8UHtM6ZoJbASejC1nBfB9NrkaImVSzvOUcppdzMdxaVG1W3KFSkcpu6g0WPVKXXH0cS1Yv2Kn7rGELUjik")
                .build();
    }

    @Test
    void givenPlacesSearchResult_mapsCorrectlyToPlaceResponseDTO() throws IOException {
        //given
        PlacesSearchResult toMap = createPlaceSearchResult();
        PlaceResponseDTO expected = createPlaceResponseDTO();

        //when
        PlaceResponseDTO received = mapper.placesSearchResultToPlaceResponseDTO(toMap);

        //then
        assertEquals(expected, received);
    }

    @Test
    void givenPlacesSearchResultArray_mapsCorrectlyToPlaceResponseDTOList() throws IOException {
        //given
        PlacesSearchResult[] toMap = createPlaceSearchResultArray();
        List<PlaceResponseDTO> expected = List.of(createPlaceResponseDTO(), createPlaceResponseDTO());

        //when
        List<PlaceResponseDTO> received = mapper.placesSearchResultArrayToPlaceResponseDTOList(toMap);

        //then
        assertEquals(expected, received);
    }

}