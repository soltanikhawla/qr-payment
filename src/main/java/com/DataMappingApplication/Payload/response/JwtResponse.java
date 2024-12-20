package com.DataMappingApplication.Payload.response;


import com.DataMappingApplication.Entity.User;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter

public class JwtResponse {

    private User user;
    private String jwt ;



    public JwtResponse(String jwt, User user) {
          this.user = user;
          this.jwt = jwt;


    }
}
