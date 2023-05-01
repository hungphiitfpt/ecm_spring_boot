/*
 * This software is licensed under the MIT License.
 * Copyright (c) 2023 nguyenphamhungphi
*/
package com.ecommer.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommer.spring.model.Category;
import com.ecommer.spring.repository.categoryRepository;

@RestController
@RequestMapping("/api/category")
public class categoryRestController {
	
	@Autowired
	categoryRepository categoryRepository;

	@RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE })
	public Page<Category> getProducts(
			@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
									
	    // xử lý logic để truy vấn danh mục theo các tham số truy vấn và trả về danh sách sản phẩm tương ứng
		Page<Category> category = (Page<Category>) categoryRepository.findAll(PageRequest.of(page, size));
		if(category != null) {
			return category;
		}
		else {
			return null;
		}

	}
}
