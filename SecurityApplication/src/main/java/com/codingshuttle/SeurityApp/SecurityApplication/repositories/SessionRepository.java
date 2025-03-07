package com.codingshuttle.SeurityApp.SecurityApplication.repositories;

import com.codingshuttle.SeurityApp.SecurityApplication.entities.Session;
import com.codingshuttle.SeurityApp.SecurityApplication.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Long> {

    List<Session> findByUser(UserEntity user);

    Optional<Session> findByRefreshToken(String refreshToken);
}
