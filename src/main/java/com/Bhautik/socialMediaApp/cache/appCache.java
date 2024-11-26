package com.Bhautik.socialMediaApp.cache;

import com.Bhautik.socialMediaApp.Repository.configurationRepository;
import com.Bhautik.socialMediaApp.entity.configuration;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class appCache {

    public enum keys {
        WEATHER_API;
    }

    public Map<String,String> APP_CACHE;

    @Autowired
    configurationRepository configurationRepository;

    @PostConstruct
    public void init(){
        APP_CACHE = new HashMap<>();
        List<configuration> all = configurationRepository.findAll();
       for (configuration configuration : all){
           APP_CACHE.put(configuration.getKey(),configuration.getValue());
       }
    }

}
