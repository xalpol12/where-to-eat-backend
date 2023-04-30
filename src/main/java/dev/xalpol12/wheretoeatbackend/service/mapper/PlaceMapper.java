package dev.xalpol12.wheretoeatbackend.service.mapper;

import com.google.maps.model.Photo;
import com.google.maps.model.PlacesSearchResult;
import dev.xalpol12.wheretoeatbackend.service.dto.PlaceResponseDTO;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.config.Configuration;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlaceMapper {
    private final ModelMapper mapper = new ModelMapper();

    PlaceMapper() {
        mapper.getConfiguration().setFieldMatchingEnabled(true).setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
        mapper.addMappings(getPlaceMap());
    }

    public List<PlaceResponseDTO> PlacesSearchResultArrayToPlaceResponseDTOList(PlacesSearchResult[] places) {
        return Arrays.stream(places)
                .map(this::PlacesSearchResultToPlaceResponseDTO)
                .collect(Collectors.toList());
    }

    PlaceResponseDTO PlacesSearchResultToPlaceResponseDTO(PlacesSearchResult place) {
        return mapper.map(place, PlaceResponseDTO.class);
    }

    private PropertyMap<PlacesSearchResult, PlaceResponseDTO> getPlaceMap() {
        return new PropertyMap<>() {
            final Converter<Photo[], String> photosToPhotoRef = ctx ->
                    ctx.getSource() == null ? null : ctx.getSource()[0].photoReference;

            @Override
            protected void configure() {
                map().setOpenNow(source.openingHours.openNow);
                using(photosToPhotoRef).map(source.photos).setPhotoReference(null);
            }
        };
    }
}
