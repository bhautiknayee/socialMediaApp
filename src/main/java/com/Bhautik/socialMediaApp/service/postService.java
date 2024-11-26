package com.Bhautik.socialMediaApp.service;

import com.Bhautik.socialMediaApp.entity.post;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;


public interface postService {
    void create(String userName, post post);

    List<post> getAll();

   Optional<post> findById(ObjectId id);

    boolean deleteById(ObjectId postId, String userName);

    public void create(post post);
}
