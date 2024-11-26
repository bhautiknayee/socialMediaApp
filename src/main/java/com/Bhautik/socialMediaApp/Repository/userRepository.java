package com.Bhautik.socialMediaApp.Repository;

import com.Bhautik.socialMediaApp.entity.user;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface userRepository extends MongoRepository<user, ObjectId> {
    user findByUserName(String userName);
}
