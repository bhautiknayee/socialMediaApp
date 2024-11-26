package com.Bhautik.socialMediaApp.Repository;

import com.Bhautik.socialMediaApp.entity.post;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


public interface postRepository extends MongoRepository<post, ObjectId> {
}
