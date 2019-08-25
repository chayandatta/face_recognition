package com.example.iotaiproject.models.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity(name="imagePathTable")
public class ImagePathTable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "imagePathId", unique = true, nullable = false)
    Long imagePathId;
    String imagePath;
    String binaryImageFile;
}
