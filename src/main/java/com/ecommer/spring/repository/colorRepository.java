/*
 * This software is licensed under the MIT License.
 * Copyright (c) 2023 nguyenphamhungphi
*/
package com.ecommer.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommer.spring.model.Color;

@Repository
public interface colorRepository  extends JpaRepository<Color, Long>{

	
	
}
