/*
 * This software is licensed under the MIT License.
 * Copyright (c) 2023 nguyenphamhungphi
*/
package com.ecommer.spring.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ecommer.spring.model.Product;

public interface productRepository  extends JpaRepository<Product, Long>{

	/**
	 * @param keyword
	 * @param price
	 * @param categoryId
	 * @param colorId
	 * @param of
	 * @return
	 */
	@Query("SELECT p FROM Product p WHERE " +
            "(:keyword IS NULL OR p.name LIKE %:keyword% OR p.description LIKE %:keyword%) " +
            "AND (:price IS NULL OR p.price <= :price) " +
            "AND (:categoryId IS NULL OR p.category.id = :categoryId) " +
            "AND (:colorId IS NULL OR p.color.id = :colorId)")
	Page<Product> searchProducts(String keyword, BigDecimal price, Long categoryId, Long colorId, Pageable page);

	/**
	 * @param keyword
	 * @param price
	 * @param categoryId
	 * @param colorId
	 * @param of
	 * @return
	 */
	@Query("SELECT p FROM Product p WHERE " +
            "(:keyword IS NULL OR p.name LIKE %:keyword% OR p.description LIKE %:keyword%) " +
            "AND (:price IS NULL OR p.price <= :price) " +
            "AND (:categoryId IS NULL OR p.category.id = :categoryId) " +
            "AND (:colorId IS NULL OR p.color.id = :colorId)")
	List<Product> searchProductss(String keyword, BigDecimal price, Long categoryId, Long colorId, PageRequest of);

	/**
	 * @param name
	 * @return
	 */
	Product findByName(String name);
	
}
