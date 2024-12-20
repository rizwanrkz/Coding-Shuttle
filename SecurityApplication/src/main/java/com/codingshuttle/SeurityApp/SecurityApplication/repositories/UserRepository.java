package com.codingshuttle.SeurityApp.SecurityApplication.repositories;

import com.codingshuttle.SeurityApp.SecurityApplication.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);
}
