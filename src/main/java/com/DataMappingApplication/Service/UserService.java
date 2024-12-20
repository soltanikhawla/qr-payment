package com.DataMappingApplication.Service;

import com.DataMappingApplication.Entity.ERole;
import com.DataMappingApplication.Entity.Project;
import com.DataMappingApplication.Entity.Role;
import com.DataMappingApplication.Entity.User;
import com.DataMappingApplication.Repository.RoleRepository;
import com.DataMappingApplication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JdbcTemplate jdbcTemplate;



    @Transactional
    public
    User getCurrentUser() {
        SecurityContext principal = SecurityContextHolder.getContext();
        Authentication authentication = principal.getAuthentication();
        User username = null ;    if (authentication != null)
            if (authentication.getPrincipal() instanceof User)
                username = ((User) authentication.getPrincipal());
            else if (authentication.getPrincipal() instanceof String)
                username = (User) authentication.getPrincipal();
        return username;
    }
@Transactional
public Optional<User> findUserByLogin(String username){

        return userRepository.findUserByUsername(username);
}
    @Transactional
    public void initRoleandUser() {
        // Vérifier si les rôles existent déjà
        String countSql = "SELECT COUNT(*) FROM roles";
        int count = jdbcTemplate.queryForObject(countSql, Integer.class);

        if (count > 0) {
            // Les rôles existent déjà, sortir de la méthode
            return;
        }

        // Les rôles n'existent pas encore, procéder à l'insertion
        String insertSql = "INSERT INTO roles (name) VALUES ('ROLE_USER'), ('ROLE_ADMIN')";
        jdbcTemplate.update(insertSql);


    // User Registration
        User user = new User();
        user.setUsername("user123");
        user.setEmail("user@user.com");
        user.setPassword(getEncodedPassword("user@pass"));
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName(ERole.valueOf("ROLE_USER"))
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);

        // Admin Registration
        User userAdmin = new User();
        userAdmin.setUsername("Admin");
        userAdmin.setEmail("admin@admin.com");
        userAdmin.setPassword(getEncodedPassword("admin@admin"));
        Set<Role> adminRoles = new HashSet<>();
        Role adminRole = roleRepository.findByName(ERole.valueOf("ROLE_ADMIN"))
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        adminRoles.add(adminRole);
        userAdmin.setRoles(adminRoles);
        userRepository.save(userAdmin);
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}