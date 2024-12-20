package com.DataMappingApplication.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID_PROJECT;
    @Column(unique = true)
    private String Project_Name;
    private Date Date_Creation;
    @Column(name = "Status")
    private String status;

    private int version =1;



}