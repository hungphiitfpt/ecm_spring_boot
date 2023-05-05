/*
 * This software is licensed under the MIT License.
 * Copyright (c) 2023 nguyenphamhungphi
*/
package com.ecommer.spring.model;

import java.math.BigDecimal;
import java.util.List;

public class ProductRequest {

	private Long id;
	private String name;
	private String description;
	private BigDecimal price;
	private Long categoryId;
	private Long colorId;
	private List<Image> images ;

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}



	public Long getId() {
		return id;
	}

	public ProductRequest(Long id, String name, String description, BigDecimal price, Long categoryId, Long colorId,
			List<Image> images) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.categoryId = categoryId;
		this.colorId = colorId;
		this.images = images;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getColorId() {
		return colorId;
	}

	public void setColorId(Long colorId) {
		this.colorId = colorId;
	}
	public ProductRequest() {
		super();
	}

}
