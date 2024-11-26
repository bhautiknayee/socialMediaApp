package com.Bhautik.socialMediaApp.service;

import com.Bhautik.socialMediaApp.apiResponse.weatherResponse;

public interface weatherService {


    weatherResponse getWeather(String city);
}
