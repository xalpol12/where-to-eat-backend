package dev.xalpol12.wheretoeatbackend.service.mapper;

import com.google.maps.model.PlacesSearchResult;
import dev.xalpol12.wheretoeatbackend.service.dto.PlaceResponseDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class PlaceMapper {
    private final ModelMapper mapper = new ModelMapper();  //TODO: dependency injection

    PlaceMapper() {
        TypeMap<PlacesSearchResult, PlaceResponseDTO> typeMapToDTO = this.mapper.createTypeMap(PlacesSearchResult.class, PlaceResponseDTO.class);
        typeMapToDTO.addMapping(src -> src.openingHours.openNow, PlaceResponseDTO::setOpenNow);
        typeMapToDTO.addMapping(src -> Arrays.stream(src.photos).findAny().get().photoReference, PlaceResponseDTO::setPhotoReference);
    }

    public PlaceResponseDTO PlacesSearchResultToPlaceResponseDTO(PlacesSearchResult place) {
        return mapper.map(place, PlaceResponseDTO.class);
    }
}
