package com.Bhautik.socialMediaApp.controller;

import com.Bhautik.socialMediaApp.entity.user;
import com.Bhautik.socialMediaApp.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class adminController {

    @Autowired
    userService userService;

    @GetMapping("/allUsers")
    public ResponseEntity<List<user>> getAll(){
        List<user> all = userService.getAll();
        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(all, HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping
    public void saveAdmin(@RequestBody user user){
        userService.createAdmin(user);
    }


}
