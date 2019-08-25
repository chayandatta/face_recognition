package com.example.iotaiproject.models.dto;

import com.example.iotaiproject.models.entity.ImageFilesEntity;
import lombok.Data;

import java.util.List;

@Data
public class ImageFilesDto {
    Long imageId;

    String byteStringImageFile;
    String binaryImageFile;
    String empId;
    String sourPath;

}
