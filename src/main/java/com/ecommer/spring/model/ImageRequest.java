/*
 * This software is licensed under the MIT License.
 * Copyright (c) 2023 nguyenphamhungphi
*/
package com.ecommer.spring.model;

public class ImageRequest {

	private String url;
	
	private long product_id;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public long getProduct_id() {
		return product_id;
	}
	public void setProduct_id(long product_id) {
		this.product_id = product_id;
	}
	public ImageRequest(String url, long product_id) {
		super();
		this.url = url;
		this.product_id = product_id;
	}
	public ImageRequest() {
		super();
	}
	
	

}