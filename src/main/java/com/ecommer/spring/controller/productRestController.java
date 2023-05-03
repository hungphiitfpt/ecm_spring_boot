/*
 * This software is licensed under the MIT License.
 * Copyright (c) 2023 nguyenphamhungphi
*/
package com.ecommer.spring.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommer.spring.model.Product;
import com.ecommer.spring.model.ProductRequest;
import com.ecommer.spring.model.ResponseObject;
import com.ecommer.spring.repository.productRepository;
import com.ecommer.spring.services.ProductService;

@RestController
@RequestMapping("/api/products/")
public class productRestController {
	
	@Autowired
	productRepository productRepository;
	
	@Autowired
	ProductService productService;

	@RequestMapping(value = "product", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE })
	public Page<Product> getProducts(
			@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
									
	    // xử lý logic để truy vấn sản phẩm theo các tham số truy vấn và trả về danh sách sản phẩm tương ứng
		Page<Product> products = (Page<Product>) productRepository.findAll(PageRequest.of(page, size));
		if(products != null) {
			return products;
		}
		else {
			return products;
		}

	}

	@RequestMapping(value = "add", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Product> createProduct(@RequestBody ProductRequest products) {
    Product product = productService.createProduct(products);
    return ResponseEntity.ok().body(product);
  }
	

	@RequestMapping(value = "product2", method = RequestMethod.GET)
    public Page<Product> searchProducts(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) BigDecimal price,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Long colorId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        try {
        	Page<Product> products = productRepository.searchProducts(keyword, price, categoryId, colorId, PageRequest.of(page, size));
    		return products;
		} catch (Exception e) {
			// Xử lý ngoại lệ ở đây, ví dụ như ghi log, thông báo lỗi, trả về mã lỗi tương ứng, vv.
	        e.printStackTrace(); // In thông tin lỗi ra console để debug
	        throw new RuntimeException("Lỗi xảy ra khi tìm kiếm sản phẩm", e);
	    }
}
	@RequestMapping(value="detail/{id}",method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResponseObject> getDetailProduct(@PathVariable long id, Model model) {
		try {
			Product product = productService.findByIdProduct(id);
			return product != null ? ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("ok", "Đã tìm thấy sản phẩm", product)
					)
					: ResponseEntity.status(HttpStatus.NOT_FOUND)
							.body(new ResponseObject("NOT FOUND", "Cannot find product with id = " + id, ""));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
        
	
	@GetMapping("export-excel")
	public void exportExcel(HttpServletResponse response,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) BigDecimal price,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Long colorId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) throws IOException {
	    List<Product> products = productRepository.searchProductss(keyword, price, categoryId, colorId, PageRequest.of(page, size)); // lấy danh sách sản phẩm từ service

	    XSSFWorkbook workbook = new XSSFWorkbook();
	    XSSFSheet sheet = workbook.createSheet("Products");

	    // tạo một cell style với font và màu sắc
	    XSSFCellStyle style = workbook.createCellStyle();
	    Font font = workbook.createFont();
	    font.setFontName("Calibri");
	    font.setColor(IndexedColors.WHITE.getIndex());
	    style.setFont(font);
	    style.setFillForegroundColor(IndexedColors.ROYAL_BLUE.getIndex());
	    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

	    // tạo hàng đầu tiên với tiêu đề cột và style
	    XSSFRow headerRow = sheet.createRow(0);
	    String[] columns = {"ID", "Name", "Description", "Price", "Category", "Color"};
	    for (int i = 0; i < columns.length; i++) {
	        XSSFCell cell = headerRow.createCell(i);
	        cell.setCellValue(columns[i]);
	        cell.setCellStyle(style);
	    }

	    // thêm dữ liệu vào sheet và định dạng phông chữ
	    int rowNum = 1;
	    for (Product product : products) {
	        XSSFRow row = sheet.createRow(rowNum++);
	        row.createCell(0).setCellValue(product.getId());
	        row.createCell(1).setCellValue(product.getName());
	        row.createCell(2).setCellValue(product.getDescription());
	        row.createCell(3).setCellValue(product.getPrice().doubleValue());
	        row.createCell(4).setCellValue(product.getCategory().getName());
	        row.createCell(5).setCellValue(product.getColor().getName());

	        // định dạng phông chữ cho các cell
	        XSSFFont cellFont = workbook.createFont();
	        cellFont.setFontName("Calibri");
	        cellFont.setColor(IndexedColors.BLACK.getIndex());
	        cellFont.setBold(true);

	        XSSFCellStyle cellStyle = workbook.createCellStyle();
	        cellStyle.setFont(cellFont);
	        row.getCell(0).setCellStyle(cellStyle);
	        row.getCell(1).setCellStyle(cellStyle);
	        row.getCell(2).setCellStyle(cellStyle);
	        row.getCell(3).setCellStyle(cellStyle);
	        row.getCell(4).setCellStyle(cellStyle);
	        row.getCell(5).setCellStyle(cellStyle);
	    }

	    // thiết lập response header và ghi file Excel vào response stream
	    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	    response.setHeader("Content-Disposition", "attachment; filename=\"products.xlsx\"");
	    ServletOutputStream outputStream = response.getOutputStream();
	    workbook.write(outputStream);
	    workbook.close();
	    outputStream.flush();
	}
}
