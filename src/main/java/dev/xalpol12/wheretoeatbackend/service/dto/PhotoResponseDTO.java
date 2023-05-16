package dev.xalpol12.wheretoeatbackend.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor         //AllArgs and NoArgs added for testing purposes
@NoArgsConstructor
public class PhotoResponseDTO {
    private byte[] imageData;
    private String photoReference;
}
