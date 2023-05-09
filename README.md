# Where-to-eat API documentation

## Overview
Where to eat API is a service that uses [Google API](https://github.com/googlemaps/google-maps-services-java) to get the list of nearby places.
All requests are made to endpoints beginning with: \
`where-to-eat.up.railway.app`

## Requests
| Parameter  | Type   | Required? | Description |
|------------|--------|-----------|-------------|
| `location` | float  | required  | todo        |
| `distance` | int    | required  | todo        |
| `minPrice` | string | required  | todo        |
| `maxPrice` | string | required  | todo        |
| `type`     | string | required  | todo        |


| Parameter          | Type    | Description                                                |
|--------------------|---------|------------------------------------------------------------|
| `name`             | string  | name of found place                                        |
| `placeId`          | string  | unique Google Places identificator                         |
| `vicinity`         | string  | place street address and city                              |
| `rating`           | float   | Google Maps rating of this place                           |
| `userRatingsTotal` | int     | total number of users who rated this place                 |
| `openNow`          | boolean | true if, at the time of sending the request, place is open |
| `photoReference`   | string  | used for getting the actual photo from another endpoint    |


| Parameter        | Type   | Required? | Description                                     |
|------------------|--------|-----------|-------------------------------------------------|
| `photoReference` | string | required  | photo identifier, received from "find" endpoint |
| `height`         | int    | required  | desired height of the photo                     |
| `width`          | int    | required  | desired width of the photo                      |

| Parameter   | Type       | Description       |
|-------------|------------|-------------------|
| `imageData` | byte array | actual image data |

