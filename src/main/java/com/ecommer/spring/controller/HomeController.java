package com.ecommer.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommer.spring.model.Product;
import com.ecommer.spring.services.ProductService;

@Controller
public class HomeController {

	@Autowired
	ProductService productService;
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
	@RequestMapping("/shop/product/{id}")
	public String getDetailProduct(@PathVariable long id, Model model) {
		Product product = productService.findByIdProduct(id);
		 model.addAttribute("product", product);
		 System.out.println(product);
		return "interface_shop/shop_index/single_product_detail";
	}
	
}
