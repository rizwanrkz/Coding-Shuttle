package com.codingshuttle.jpaTutorial.jpaTut.repositories;

import com.codingshuttle.jpaTutorial.jpaTut.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findByTitle(String title);

    List<ProductEntity>  findByCreatedAtAfter(LocalDateTime after);

    List<ProductEntity> findByQuantityAndPrice(int quantity, BigDecimal price);

    List<ProductEntity> findByQuantityGreaterThanAndPriceLessThan(int quantity, BigDecimal price);

    List<ProductEntity> findByTitleLike(String title);

    @Query("select e from ProductEntity e where e.title=?1 and price=?2")
    Optional<ProductEntity> findByTitleAndPrice(String title, BigDecimal price);
}
