package com.codingshuttle.SeurityApp.SecurityApplication.repositories;

import com.codingshuttle.SeurityApp.SecurityApplication.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
}
