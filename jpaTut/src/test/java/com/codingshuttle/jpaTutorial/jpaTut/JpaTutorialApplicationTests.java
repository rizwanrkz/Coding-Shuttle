package com.codingshuttle.jpaTutorial.jpaTut;

import com.codingshuttle.jpaTutorial.jpaTut.entities.ProductEntity;
import com.codingshuttle.jpaTutorial.jpaTut.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class JpaTutorialApplicationTests {

	@Autowired
	ProductRepository productRepository;
	@Test
	void contextLoads() {
	}

	@Test
	void testRepository() {
		ProductEntity productEntity = ProductEntity.builder()
				.sku("Samsung")
				.title("Samsung Mobile")
				.price(BigDecimal.valueOf(23000.50))
				.quantity(4)
				.build();
		ProductEntity savedProductEntity = productRepository.save(productEntity);
		System.out.println(savedProductEntity);
	}

	@Test
	void getRepository() {
		List<ProductEntity> entities = productRepository.findAll();
		System.out.println(entities);
	}

	@Test
	void getRepository2() {
		List<ProductEntity> entities = productRepository.findByTitle("samsung");
		System.out.println(entities);
	}

	@Test
	void getRepository3() {
		List<ProductEntity> entities = productRepository.findByCreatedAtAfterOrderByTitle(LocalDateTime.of(
				2024,1,1,0,0,0));
		System.out.println(entities);
	}

	@Test
	void getRepository4() {
		List<ProductEntity> entities = productRepository.findByQuantityAndPrice(4, BigDecimal.valueOf(23000.50));
		System.out.println(entities);
	}

	@Test
	void getRepository5() {
		List<ProductEntity> entities = productRepository.findByQuantityGreaterThanAndPriceLessThan(4, BigDecimal.valueOf(23000.50));
		System.out.println(entities);
	}

	@Test
	void getRepository6() {
		List<ProductEntity> entities = productRepository.findByTitleLike("%R%");
		System.out.println(entities);
	}

	@Test
	void getSingleRecodFromRepository6() {
		Optional<ProductEntity> productEntity = productRepository.findByTitleAndPrice("TIGER",BigDecimal.valueOf(40));
		//System.out.println(productEntity);
		productEntity.ifPresent(System.out::println);
	}

}
