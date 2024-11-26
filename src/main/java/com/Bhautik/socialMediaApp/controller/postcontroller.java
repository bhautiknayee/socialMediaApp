package com.Bhautik.socialMediaApp.controller;

import com.Bhautik.socialMediaApp.entity.post;
import com.Bhautik.socialMediaApp.entity.user;
import com.Bhautik.socialMediaApp.service.postService;
import com.Bhautik.socialMediaApp.service.userService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/post")
public class postcontroller {

    @Autowired
    postService postService;

    @Autowired
    userService userService;

    @PostMapping
    public ResponseEntity<post> create(@RequestBody post post){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        try {
            postService.create(userName,post);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping
    public ResponseEntity<List<post>> getAllPostOfUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       String userName = authentication.getName();
       user user =  userService.findByuserName(userName);
        List<post> post = user.getPost();
        if(post != null && !post.isEmpty()){
           return new ResponseEntity<>(post,HttpStatus.OK);
       } else {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }

    }

    @GetMapping("id/{id}")
    public ResponseEntity<post> findById(@PathVariable ObjectId id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        user user  = userService.findByuserName(userName);
        List<post> collect = user.getPost().stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());
       if(!collect.isEmpty()){
           Optional<post> post1 = postService.findById(id);
           if(post1.isPresent()){
               return new ResponseEntity<>(post1.get(),HttpStatus.OK);
           }
       }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("id/{postId}")
    public ResponseEntity<?> deleteById(@PathVariable ObjectId postId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        boolean removed = postService.deleteById(postId,userName);
        if(removed){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }

    @PutMapping("id/{id}")
    public ResponseEntity<post> updateById(@PathVariable ObjectId id,
                                           @RequestBody post newPost)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        user user  = userService.findByuserName(userName);
        List<post> collect = user.getPost().stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());
        if(!collect.isEmpty()){
            Optional<post> post1 = postService.findById(id);
            if(post1.isPresent()){
                post oldPost = post1.get();
                oldPost.setTitle(newPost.getTitle() != null && !newPost.getTitle().equals(" ") ? newPost.getTitle() : oldPost.getTitle());
                oldPost.setContent(newPost.getContent() != null && !newPost.getContent().equals(" ") ? newPost.getContent() : oldPost.getContent());
                postService.create(oldPost);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    }




