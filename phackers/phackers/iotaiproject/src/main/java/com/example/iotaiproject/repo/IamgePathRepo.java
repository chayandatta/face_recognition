package com.example.iotaiproject.repo;

import com.example.iotaiproject.models.entity.knownPeopleTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IamgePathRepo extends JpaRepository<knownPeopleTable,Long> {
}
