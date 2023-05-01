package dev.xalpol12.wheretoeatbackend.service.mapper;

import com.google.maps.ImageResult;
import dev.xalpol12.wheretoeatbackend.service.dto.PhotoResponseDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PhotoMapperTest {
    private static final String JSON_CONTENT_TYPE_PATH = Paths
            .get("src/test/resources/json/photos/content_type.txt").toAbsolutePath().toString();
    private static final String JSON_IMAGE_DATA_PATH = Paths
            .get("src/test/resources/json/photos/image_data.txt").toAbsolutePath().toString();

    private static PhotoMapper mapper;
    private static String contentType;
    private static byte[] imageData;

    @BeforeAll
    static void setup() throws IOException {
        mapper = new PhotoMapper();
        contentType = Files.readString(Path.of(JSON_CONTENT_TYPE_PATH));
        imageData = Files.readAllBytes(Path.of(JSON_IMAGE_DATA_PATH));
    }

    private ImageResult createImageResult() throws IOException {
        return new ImageResult(contentType, imageData);
    }

    private PhotoResponseDTO createPhotoResponseDTO() throws IOException {
        return new PhotoResponseDTO(imageData);
    }

    @Test
    void givenImageResult_mapsCorrectlyToPhotoResponseDTO () throws IOException {
        //given
        ImageResult toMap = createImageResult();
        PhotoResponseDTO expected = createPhotoResponseDTO();

        //when
        PhotoResponseDTO received = mapper.imageResultToPhotoResponseDTO(toMap);

        //then
        assertEquals(expected, received);
    }

    @Test
    void imageResultListToPhotoResponseDTOList() throws IOException {
        //given
        ImageResult imgResult = createImageResult();
        List<ImageResult> toMap = List.of(imgResult, imgResult);
        PhotoResponseDTO photoResponse = createPhotoResponseDTO();
        List<PhotoResponseDTO> expected = List.of(photoResponse, photoResponse);

        //when
        List<PhotoResponseDTO> received = mapper.imageResultListToPhotoResponseDTOList(toMap);

        //then
        assertEquals(expected, received);
    }
}