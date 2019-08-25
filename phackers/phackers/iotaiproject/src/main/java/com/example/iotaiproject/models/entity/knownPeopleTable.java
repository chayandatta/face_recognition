package com.example.iotaiproject.models.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity(name="knownpeopleTable")
@Data
public class knownPeopleTable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "knownPeopleId", unique = true, nullable = false)
    Long knownPeopleId;
    String empId;


    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.REMOVE},orphanRemoval = true)
    @JoinColumn(columnDefinition="imageId")
    List<ImagePathTable> imagesai;


}
