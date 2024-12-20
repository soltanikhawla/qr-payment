package com.DataMappingApplication.Repository;

import com.DataMappingApplication.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {


   Optional<User> findByUsername(String username);
   Optional<User> findUserById(Long id);


   Boolean existsByUsername(String username);

   Boolean existsByEmail(String email);

   Optional<User> findUserByUsername(String username);

}
