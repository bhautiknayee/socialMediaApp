package com.Bhautik.socialMediaApp.service.serviceImpl;

import com.Bhautik.socialMediaApp.apiResponse.weatherResponse;
import com.Bhautik.socialMediaApp.cache.appCache;
import com.Bhautik.socialMediaApp.constants.placeHolder;
import com.Bhautik.socialMediaApp.service.weatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class weatherServiceImpl implements weatherService {

    @Value("${weather_api_key}")
    private String apiKey;

    @Autowired
    private appCache appCache;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public weatherResponse getWeather(String city){
        String finalApi = appCache.APP_CACHE.get(com.Bhautik.socialMediaApp.cache.appCache.keys.WEATHER_API.toString()).
                replace(placeHolder.CITY,city).replace(placeHolder.API_KEY,apiKey);
        ResponseEntity<weatherResponse> response = restTemplate.exchange(finalApi, HttpMethod.GET, null, weatherResponse.class);
        weatherResponse body = response.getBody();
        return body;
    }


}
