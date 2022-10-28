package com.dhaya.final2.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhaya.final2.model.Product;
import com.dhaya.final2.service.ProductService;

@RestController
@RequestMapping("product")
//@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

	@Autowired
	ProductService productService;
	String message;

	@Autowired
	Product product;

	public ProductController() {
		// TODO Auto-generated constructor stub
	}

	@PostMapping // http://localhost:9090/product/ - POST - BODY
	public ResponseEntity<String> saveProduct(@RequestBody Product product) {
		ResponseEntity<String> responseEntity;

		if (productService.isProductExists(product.getProductId())) {
			responseEntity = new ResponseEntity<>("Product already exists", HttpStatus.CONFLICT);
		} else {
			String message = productService.saveProduct(product);

			if (message.equals("Product saved successfully")) {
				responseEntity = new ResponseEntity<String>(message, HttpStatus.CREATED);
			} else {
				responseEntity = new ResponseEntity<String>(message, HttpStatus.NOT_ACCEPTABLE); // 200
			}
		}
		return responseEntity;
	}

	@GetMapping // http://localhost:9090/product
	public ResponseEntity<List<Product>> getProducts() {
		ResponseEntity<List<Product>> responseEntity;

		List<Product> products = productService.getProduct();

		if (products.size() == 0) {
			responseEntity = new ResponseEntity<List<Product>>(products, HttpStatus.NO_CONTENT); // 201
		} else {
			responseEntity = new ResponseEntity<List<Product>>(products, HttpStatus.OK); // 200
		}
		return responseEntity;
	}

	@GetMapping("{productId}") // http://localhost:9090/product/198
	public ResponseEntity<Product> getProduct(@PathVariable("productId") Integer productId) {

		ResponseEntity<Product> responseEntity;
		if (productService.isProductExists(productId)) {
			product = productService.getProduct(productId);

			responseEntity = new ResponseEntity<Product>(product, HttpStatus.NO_CONTENT); // 201
		} else {
			responseEntity = new ResponseEntity<Product>(product, HttpStatus.NO_CONTENT); // 201
		}
		return responseEntity;
	}

	@DeleteMapping("{productId}") // http://localhost:9090/product/198 - DELETE
	public ResponseEntity<String> deleteProduct(@PathVariable("productId") Integer productId) {
		ResponseEntity<String> responseEntity = null;
		String product = productService.deleteProduct(productId);

		if (product == null) {
			responseEntity = new ResponseEntity<String>("Product does not exists", HttpStatus.NO_CONTENT);
		} else {
			responseEntity = new ResponseEntity<String>(product, HttpStatus.OK);
		}
		return responseEntity;
	}

	@PutMapping // http://localhost:9090/product/ - PUT - BODY
	public ResponseEntity<String> updateProduct(@RequestBody Product product) {
		ResponseEntity<String> responseEntity = null;
		if (productService.isProductExists(product.getProductId())) {
			message = productService.saveProduct(product);
			responseEntity = new ResponseEntity<String>(message, HttpStatus.OK);
		} else {
			responseEntity = new ResponseEntity<String>("Product not available , we cannot update",
					HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}

	// Fetch products by product name
	// http://localhost:9090/product/searchByProductName/Car
	@GetMapping("searchByProductName/{productName}") // http://localhost:9090/product
	public ResponseEntity<List<Product>> getProductsByName(@PathVariable("productName") String productName) {
		ResponseEntity<List<Product>> responseEntity;

		List<Product> products = productService.getProduct(productName);

		if (products.size() == 0) {
			responseEntity = new ResponseEntity<List<Product>>(products, HttpStatus.NO_CONTENT); // 201
		} else {

			responseEntity = new ResponseEntity<List<Product>>(products, HttpStatus.OK); // 200

		}
		return responseEntity;
	}

	@GetMapping("searchByProductPriceBetween/{minPrice}/and/{maxPrice}") // http://localhost:9090/product
	public ResponseEntity<List<Product>> getProductsByName(@PathVariable("minPrice") int minPrice,
			@PathVariable("maxPrice") int maxPrice) {
		ResponseEntity<List<Product>> responseEntity;

		List<Product> products = productService.getProduct(minPrice, maxPrice);

		if (products.size() == 0) {
			responseEntity = new ResponseEntity<List<Product>>(products, HttpStatus.NO_CONTENT); // 201
		} else {

			responseEntity = new ResponseEntity<List<Product>>(products, HttpStatus.OK); // 200

		}
		return responseEntity;
	}

	@GetMapping("searchByProductPrice/{price}") // http://localhost:9090/product
	public ResponseEntity<List<Product>> getProductsByName(@PathVariable("price") int price) {
		ResponseEntity<List<Product>> responseEntity;

		List<Product> products = productService.getProductByPrice(price);

		if (products.size() == 0) {
			responseEntity = new ResponseEntity<List<Product>>(products, HttpStatus.NO_CONTENT); // 201
		} else {

			responseEntity = new ResponseEntity<List<Product>>(products, HttpStatus.OK); // 200

		}
		return responseEntity;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	@PostMapping // http://localhost:9090/product/ - POST - BODY
//	public ResponseEntity<String> saveProduct(@RequestBody Product product) {
//		ResponseEntity<String> responseEntity = null;
//		long pId = product.getProductId();
//		if (productServiceImpl.isProductExists(pId)) {
//			responseEntity = new ResponseEntity<String>(("Product with product id :" + pId + " already exists"),
//					HttpStatus.CONFLICT); // 409
//		} else {
//			message = productServiceImpl.saveProduct(product);
//			responseEntity = new ResponseEntity<String>(message, HttpStatus.OK); // 200
//		}
//
//		return responseEntity;
//	}
//
//	@GetMapping // http://localhost:9090/product
//	public ResponseEntity<List<Product>> getProducts() {
//		ResponseEntity<List<Product>> responseEntity = null;
//		List<Product> products = productServiceImpl.getProduct();
//		if (products.isEmpty()) {
//			responseEntity = new ResponseEntity<List<Product>>(products, HttpStatus.NO_CONTENT); // 204
//		} else {
//			responseEntity = new ResponseEntity<List<Product>>(products, HttpStatus.OK); // 200
//		}
//
//		return responseEntity;
//	}
//
//	@GetMapping("{productId}") // http://localhost:9090/product/198
//	public ResponseEntity<Product> getProduct(@PathVariable("productId") Integer productId) {
//		ResponseEntity<Product> responseEntity = null;
//
//		Product product = productServiceImpl.getProduct(productId);
//		if (product == null) {
//			responseEntity = new ResponseEntity<Product>(product, HttpStatus.NO_CONTENT); // 204
//		} else {
//			responseEntity = new ResponseEntity<Product>(product, HttpStatus.OK); // 200
//		}
//		return responseEntity;
//	}
//
//	@DeleteMapping("{productId}") // http://localhost:9090/product/198 - DELETE
//	public ResponseEntity<String> deleteProduct(@PathVariable("productId") Integer productId) {
//		ResponseEntity<String> responseEntity = null;
//		String product = productServiceImpl.deleteProduct(productId);
//
//		if (product == null) {
//			responseEntity = new ResponseEntity<String>("Product does not exists", HttpStatus.NO_CONTENT);
//		} else {
//			responseEntity = new ResponseEntity<String>(product, HttpStatus.OK);
//		}
//		return responseEntity;
//	}
//
//	@PutMapping // http://localhost:9090/product/ - PUT - BODY
//	public ResponseEntity<String> updateProduct(@RequestBody Product product) {
//		ResponseEntity<String> responseEntity = null;
//		long PId = product.getProductId();
//		if (productServiceImpl.isProductExists(PId)) {
//			responseEntity = new ResponseEntity<String>(product, HttpStatus.NO_CONTENT);
//		}
//		return responseEntity;
//	}
}
