package com.DataMappingApplication.Repository;

import com.DataMappingApplication.Entity.ERole;
import com.DataMappingApplication.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository

public interface RoleRepository extends JpaRepository<Role,Long> {


       @Transactional
        Optional<Role> findByName(ERole name);



}
