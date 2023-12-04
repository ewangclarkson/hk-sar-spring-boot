package com.hksar.sar.repository;

import com.hksar.sar.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findUserByNameIgnoreCase(String name);
    UserDetails findByEmail(String email);
}
