# Where-to-eat API documentation

## Overview
Where to eat API is a wrapper service that uses [Google API](https://github.com/googlemaps/google-maps-services-java) to get the list of nearby places.
All requests are made to endpoints beginning: \
`where-to-eat.up.railway.app`

Other projects that use this backend: 
- [Where-to-eat android app](https://github.com/xalpol12/where-to-eat-android-app) - in development

# Requests

## Places

**Getting a list of places**

Returns details of places found in a defined approximity, with the location specified by latitude and longtitude. 

Constructed exemplary request looks like this:
```
POST /places/find
Content-Type: application/json

{
    "location" : {
        "lat" : 52.40389796246586,
        "lng" : 16.95350439308018
        },
    "distance" : 3000,
    "minPrice" : "1",
    "maxPrice" : "3",
    "type" : "CAFE"
}
```

With parameters:

| Parameter  | Type   | Required? | Description |
|------------|--------|-----------|-------------|
| `location` | (float, float)  | required  | a pair of values, latitude and longtitude, representing a specific location |
| `distance` | int    | required  | the maximum distance in which the place should be searched for |
| `minPrice` | string | required  | `0 - FREE`, `1 - INEXPENSIVE`, `2 - MODERATE`, `3 - EXPENSIVE`, `4 - VERY EXPENSIVE`         |
| `maxPrice` | string | required  | same as stated above        |
| `type`     | string | required  | one of the place types supported by [Google Places API](https://developers.google.com/maps/documentation/places/web-service/supported_types)   |

If successful, you will receive back a list of places found by Google Places API:
```
HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Wed, 10 May 2023 17:27:54 GMT
Keep-Alive: timeout=60
Connection: keep-alive

[
  {
    "name": "Brovaria",
    "placeId": "ChIJ1ZUWpDhbBEcR--j66OkzlXw",
    "vicinity": "Stary Rynek 73-74, Poznań",
    "rating": 4.4,
    "userRatingsTotal": 2241,
    "openNow": true,
    "photoReference": "AZose0nAauWPTBWGmeTJqZS4n3AV6ioNqlXxyfRpisu7FRmSleYRjznz1N6RL1LlIXsHH_12mBCojvaDj_mJPSoPh5ydkmOdCK8yKHAXlGqqR0Z-7G6tYqWWInj4Bj9PdbuXdvmA5OsLsUkYf6YiDB4C-bAVnQWX3upBJdTG_wCEEtcKrMRr"
  }
]
```
With the following parameters:

| Parameter          | Type    | Description                                                |
|--------------------|---------|------------------------------------------------------------|
| `name`             | string  | name of found place                                        |
| `placeId`          | string  | unique Google Places identificator of the place            |
| `vicinity`         | string  | place street address and city                              |
| `rating`           | float   | Google Maps rating of this place                           |
| `userRatingsTotal` | int     | total number of users who rated this place                 |
| `openNow`          | boolean | true if, at the time of sending the request, place is open |
| `photoReference`   | string  | used for getting the actual photo from another endpoint    |


## Images

**Getting an image from url**

Returns data of the image specified by `photoReference`. 

Constructed exemplary request looks like this:
```
POST /places/find
Content-Type: application/json

{
    "photoReference" : {{photoReference}},
    "width" : 1000,
    "height" : 1500
}
```
With parameters:

| Parameter        | Type   | Required? | Description                                     |
|------------------|--------|-----------|-------------------------------------------------|
| `photoReference` | string | required  | photo identifier, received from [find](#places) endpoint |
| `height`         | int    | required  | desired height of the photo                     |
| `width`          | int    | required  | desired width of the photo                      |

If successful, you will receive back a base64 coded array containing requested image data:
```
HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Wed, 10 May 2023 17:22:33 GMT
Keep-Alive: timeout=60
Connection: keep-alive

{
    "imageData" : {{imageData}}
}
```
With parameters:

| Parameter   | Type       | Description             |
|-------------|------------|-------------------------|
| `imageData` | byte array | base64 coded byte array |

## Possible errors:

| Error code                 | Description                                                                   |
| ---------------------------|-------------------------------------------------------------------------------|
| 404 Not found              | Connection to a server has been interrupted, thus couldn't obtain any records |
| 500 Internal server error  | Google API service failure                                                    |
| 504 Gateway timeout        | Server took more than 2 seconds to respond                                    |
