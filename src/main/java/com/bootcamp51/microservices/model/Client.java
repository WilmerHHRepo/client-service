package com.bootcamp51.microservices.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Client {
    private ObjectId id;
    private String indType;
    private String desType;
    private String indTypeDocument;
    private String desTypeDocument;
    private String name;
    private String lastName;

}
