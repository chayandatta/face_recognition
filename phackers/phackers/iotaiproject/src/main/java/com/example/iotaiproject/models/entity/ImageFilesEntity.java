package com.example.iotaiproject.models.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity(name="imageaitable")
public class ImageFilesEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "imageId", unique = true, nullable = false)
    Long imageId;
    String byteStringImageFile;
    String binaryImageFile;
    String empId;
    String sourPath;

}
