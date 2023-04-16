package dev.xalpol12.wheretoeatbackend;

import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PlacesApiTest {
    String variable = System.getenv("PLACES_API_KEY");
    GeoApiContext context = new GeoApiContext.Builder().apiKey(variable).queryRateLimit(2).disableRetries().build();

    @Test
    public void testPlaceDetailsRequest() throws Exception {
        LatLng location = new LatLng(54.189968855417014, 16.182448076516245);
//        PlacesSearchResponse results = PlacesApi.textSearchQuery(context, "Pizza in new york").await();
        PlacesSearchResponse results = PlacesApi.nearbySearchQuery(context, location)
                .radius(2000)
                .rankby(RankBy.PROMINENCE)
                .language("pl")
                .minPrice(PriceLevel.INEXPENSIVE)
                .maxPrice(PriceLevel.EXPENSIVE)
                .type(PlaceType.RESTAURANT)
                .await();
        System.out.println(results.toString());
        context.shutdown();
        assertNotNull(results.toString());
    }
}
