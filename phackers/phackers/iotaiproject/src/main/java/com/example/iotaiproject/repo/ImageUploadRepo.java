package com.example.iotaiproject.repo;

import com.example.iotaiproject.models.entity.ImageFilesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageUploadRepo extends JpaRepository<ImageFilesEntity,Long> {
}
