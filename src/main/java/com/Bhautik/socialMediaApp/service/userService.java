package com.Bhautik.socialMediaApp.service;

import com.Bhautik.socialMediaApp.entity.post;
import com.Bhautik.socialMediaApp.entity.user;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;


public interface userService {

    void saveNewUser(user user);

    void create(user user);

    List<user> getAll();

   Optional<user> findById(ObjectId id);

    void deleteById(ObjectId id);

    user findByuserName(String userName);

    void createAdmin(user user);
}
