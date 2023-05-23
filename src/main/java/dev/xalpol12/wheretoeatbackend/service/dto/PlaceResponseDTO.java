package dev.xalpol12.wheretoeatbackend.service.dto;

import com.google.maps.model.LatLng;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlaceResponseDTO {
    private String name;
    private String placeId;
    private LatLng location;
    private String vicinity;
    private float rating;
    private int userRatingsTotal;
    private boolean openNow;
    private String photoReference;
}
