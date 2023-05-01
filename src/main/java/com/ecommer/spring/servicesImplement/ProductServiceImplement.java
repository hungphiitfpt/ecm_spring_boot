/*
 * This software is licensed under the MIT License.
 * Copyright (c) 2023 nguyenphamhungphi
*/
package com.ecommer.spring.servicesImplement;

import java.util.ArrayList;
import java.util.Date;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ecommer.spring.model.Category;
import com.ecommer.spring.model.Color;
import com.ecommer.spring.model.Product;
import com.ecommer.spring.model.ProductRequest;
import com.ecommer.spring.repository.categoryRepository;
import com.ecommer.spring.repository.colorRepository;
import com.ecommer.spring.repository.productRepository;
import com.ecommer.spring.services.ProductService;

@Repository
public class ProductServiceImplement implements ProductService{

	@Autowired 
	productRepository productRepository;
	
	@Autowired
	categoryRepository categoryRepository;
	
	@Autowired 
	colorRepository colorRepository;
	
	@Override
	public Product createProduct(ProductRequest productRequest) {
		if (productRepository.findByName(productRequest.getName()) != null) {
		      throw new IllegalArgumentException("Product with this name already exists!");
		    }
		    Category category = categoryRepository.findById(productRequest.getCategoryId())
		        .orElseThrow(() -> new NoSuchElementException("Category not found!"));
		    Color color = colorRepository.findById(productRequest.getColorId())
		        .orElseThrow(() -> new NoSuchElementException("Color not found!"));
		    Product product = new Product();
		    product.setName(productRequest.getName());
		    product.setDescription(productRequest.getDescription());
		    product.setPrice(productRequest.getPrice());
		    product.setCategory(category);
		    product.setColor(color);
		    product.setImages(new ArrayList<>());
		    product.setCreatedAt(new Date());
		    product.setUpdatedAt(new Date());
		    return productRepository.save(product);
	}

}
