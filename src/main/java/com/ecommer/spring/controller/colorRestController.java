/*
 * This software is licensed under the MIT License.
 * Copyright (c) 2023 nguyenphamhungphi
*/
package com.ecommer.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommer.spring.model.Color;
import com.ecommer.spring.model.Product;
import com.ecommer.spring.model.ProductRequest;
import com.ecommer.spring.repository.colorRepository;
import com.ecommer.spring.services.ProductService;

@RestController
@RequestMapping("/api/color")
public class colorRestController {
	
	@Autowired
	colorRepository colorRepository;
	
	@Autowired
	ProductService productService;

	@RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE })
	public Page<Color> getProducts(
			@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
									
	    // xử lý logic để truy vấn sản phẩm theo các tham số truy vấn và trả về danh sách sản phẩm tương ứng
		Page<Color> category = (Page<Color>) colorRepository.findAll(PageRequest.of(page, size));
		if(category != null) {
			return category;
		}
		else {
			return null;
		}

	}
}
