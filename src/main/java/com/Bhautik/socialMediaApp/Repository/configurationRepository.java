package com.Bhautik.socialMediaApp.Repository;

import com.Bhautik.socialMediaApp.entity.configuration;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface configurationRepository extends MongoRepository<configuration, ObjectId> {
}
