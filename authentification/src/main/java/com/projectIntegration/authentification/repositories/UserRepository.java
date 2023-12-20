package com.projectIntegration.authentification.repositories;

import com.projectIntegration.authentification.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String username);
    List<User> findAllByActive(boolean active);
}
