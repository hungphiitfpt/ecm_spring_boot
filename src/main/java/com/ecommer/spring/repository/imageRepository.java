/*
 * This software is licensed under the MIT License.
 * Copyright (c) 2023 nguyenphamhungphi
*/
package com.ecommer.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommer.spring.model.Image;

public interface imageRepository  extends JpaRepository<Image, Long>{
	
}
