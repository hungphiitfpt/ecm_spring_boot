/*
 * This software is licensed under the MIT License.
 * Copyright (c) 2023 nguyenphamhungphi
*/
package com.ecommer.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommer.spring.model.Category;
import com.ecommer.spring.model.Color;

public interface colorRepository  extends JpaRepository<Color, Long>{

	
	
}