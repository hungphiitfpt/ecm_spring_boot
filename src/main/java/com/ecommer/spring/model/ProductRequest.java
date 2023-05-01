/*
 * This software is licensed under the MIT License.
 * Copyright (c) 2023 nguyenphamhungphi
*/
package com.ecommer.spring.model;

import java.math.BigDecimal;

public class ProductRequest {

	private String name;
	private String description;
	private BigDecimal price;
	private Long categoryId;
	private Long colorId;

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

	public ProductRequest(String name, String description, BigDecimal price, Long categoryId, Long colorId) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.categoryId = categoryId;
		this.colorId = colorId;
	}

	public ProductRequest() {
		super();
	}

}
