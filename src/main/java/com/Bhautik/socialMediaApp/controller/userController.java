package com.Bhautik.socialMediaApp.controller;

import com.Bhautik.socialMediaApp.apiResponse.weatherResponse;
import com.Bhautik.socialMediaApp.entity.post;
import com.Bhautik.socialMediaApp.entity.user;
import com.Bhautik.socialMediaApp.service.userService;
import com.Bhautik.socialMediaApp.service.weatherService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class userController {

    @Autowired
    userService userService;

    @Autowired
    weatherService weatherService;




//
//    @GetMapping("id/{id}")
//    public ResponseEntity<user> findById(@PathVariable ObjectId id){
//        Optional<user> user = userService.findById(id);
//        if(user.isPresent()){
//            return new ResponseEntity<>(user.get(),HttpStatus.OK);
//        } else{
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//    }
//
    @DeleteMapping
    public ResponseEntity<?> deleteById(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        user oldUser = userService.findByuserName(name);
        if(oldUser != null){
            userService.deleteById(oldUser.getId());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<post> updateUserByUserName(@RequestBody user user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
       user oldUser = userService.findByuserName(name);
        if (oldUser != null) {
            oldUser.setUserName(user.getUserName());
            oldUser.setPassword(user.getPassword());
            userService.create(oldUser);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }
    }


    @GetMapping
    public ResponseEntity<?> greeting(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        weatherResponse weatherResponse = weatherService.getWeather("Cleveland");
        String greeting = "";
        if(weatherResponse.getCurrent() != null){
            greeting = ", Weather feels like "+ weatherResponse.getCurrent().getFeelslike();

        }     return  new ResponseEntity<>("Hello "+ authentication.getName() + greeting ,HttpStatus.OK);
    }

    }




