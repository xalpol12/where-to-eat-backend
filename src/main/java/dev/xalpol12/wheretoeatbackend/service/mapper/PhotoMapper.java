package dev.xalpol12.wheretoeatbackend.service.mapper;

import com.google.maps.ImageResult;
import dev.xalpol12.wheretoeatbackend.service.dto.PhotoResponseDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhotoMapper {
    private final ModelMapper mapper =  new ModelMapper();

    PhotoMapper() {
        mapper.getConfiguration().setFieldMatchingEnabled(true).setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
    }

    public PhotoResponseDTO imageResultToPhotoResponseDTO(ImageResult image) {
        return mapper.map(image, PhotoResponseDTO.class);
    }

    public List<PhotoResponseDTO> imageResultListToPhotoResponseDTOList(List<ImageResult> images) {
        return images.stream()
                .map(this::imageResultToPhotoResponseDTO)
                .collect(Collectors.toList());
    }
}
