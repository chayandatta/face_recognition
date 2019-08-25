package com.example.iotaiproject.models.entity;


import lombok.Data;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity(name="registerTable")
public class RegisterEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "registerId", unique = true, nullable = false)
    Long registerId;

    String empId;
    String name;


    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.REMOVE},orphanRemoval = true)
    @JoinColumn(columnDefinition="imageId")
    List<ImageFilesEntity> imagesai;



}
