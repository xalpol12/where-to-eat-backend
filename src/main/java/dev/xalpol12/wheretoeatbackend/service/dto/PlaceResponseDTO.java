package dev.xalpol12.wheretoeatbackend.service.dto;

import lombok.Data;

@Data
public class PlaceResponseDTO {
    private String name;
    private String placeId;
    private String vicinity;
    private float rating;
    private int userRatingsTotal;
    private boolean openNow;
    private String photoReference;
}
