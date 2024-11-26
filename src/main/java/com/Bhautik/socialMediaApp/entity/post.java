package com.Bhautik.socialMediaApp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "posts")
public class post {

    @Id
    private ObjectId id;
    private String title;
    private String content;
    private LocalDate localDate;



}
