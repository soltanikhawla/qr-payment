package com.DataMappingApplication.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;


//    @JsonIgnore()
//    @OneToMany(mappedBy = "roles")
//    private List<User> user;




    public Role() {

    }

    public Role(ERole name) {
        this.name = name;
    }




    public Integer getId() {
        return id;
   }

    public void setId(Integer id) {
       this.id = id;
    }
    public ERole getName() {
        return name;
    }

    public void setName(ERole name) {
        this.name = name;
    }
}