package com.ecommer.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/index")
	public String index() {
		return "interface_shop/shop_index/index";
	}
	@RequestMapping("/admin/index")
	public String adminIndex() {
		return "interface_admin/tinydash-master/light/index";
	}
	@RequestMapping("/admin/product")
	public String adminProduct() {
		return "interface_admin/product_manager/product_admin";
	}
	@RequestMapping("/admin/manager/product")
	public String adminProductManager() {
		return "interface_admin/product_manager/product_manager";
	}
	@RequestMapping("/admin/product/manager")
	public String adminProductVadition() {
		return "interface_admin/product_manager/form_validation";
	}
	
}
