package dev.xalpol12.wheretoeatbackend.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhotoResponseDTO {
    byte[] imageData;
}
