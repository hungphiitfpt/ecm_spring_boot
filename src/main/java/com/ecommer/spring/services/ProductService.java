/*
 * This software is licensed under the MIT License.
 * Copyright (c) 2023 nguyenphamhungphi
*/
package com.ecommer.spring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ecommer.spring.model.Product;
import com.ecommer.spring.model.ProductRequest;

@Service
public interface ProductService {

	Product createProduct(ProductRequest products);

	Product findByIdProduct(long id);

	List<Product> findAll();



}
 