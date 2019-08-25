package com.example.iotaiproject.models.dto;

import com.example.iotaiproject.models.entity.ImageFilesEntity;
import lombok.Data;

import java.util.List;

@Data
public class RegisterDto {

    Long registerId;
    String empId;
    String firstName;
    String lastName;
    List<ImageFilesEntity> imagesai;

}
