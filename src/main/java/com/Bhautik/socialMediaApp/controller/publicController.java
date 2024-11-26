package com.Bhautik.socialMediaApp.controller;

import com.Bhautik.socialMediaApp.entity.post;
import com.Bhautik.socialMediaApp.entity.user;
import com.Bhautik.socialMediaApp.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class publicController {

    @Autowired
    userService service;

    @PostMapping
    public ResponseEntity<post> create(@RequestBody user user){
        try {
            service.saveNewUser(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

}
