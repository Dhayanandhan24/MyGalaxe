package com.dhaya.final2.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhaya.final2.AOP.LoggingAspect;
import com.dhaya.final2.AOP.SecurityAspect;
import com.dhaya.final2.ProductDAO.ProductDAO;
import com.dhaya.final2.model.Product;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDAO productDAO;
	
	@Autowired
	LoggingAspect loggingAspect;
	
	@Autowired
	SecurityAspect securityAspect;

	@Override
	public String saveProduct(Product product) {
		String message = null;
		if (product.getPrice() < 0 | product.getQuantityOnHand() < 0) {
			message = "Product price cannot be negative";
		} else {
			message = "Product saved successfully";
			productDAO.save(product);
		}
		return message;
	}

	@Override
	public String updateProduct(Product product) {

		return null;
	}

	@Override
	public String deleteProduct(long productId) {
		productDAO.deleteById(productId);
		return "Deleted Successful";
	}

	@Override
	public List<Product> searchProductByName(String productName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product getProduct(long productId) {
		Optional<Product> product = productDAO.findById(productId);
		return product.get();
	}

	@Override
	public List<Product> getProduct() {
		return (List<Product>) productDAO.findAll();
	}

	@Override
	public boolean isProductExists(long pId) {
		Optional<Product> product = productDAO.findById(pId);
		return product.isPresent();
	}

	@Override
	public List<Product> getProduct(String productName) {
		return productDAO.findByProductName(productName);
	}

	@Override
	public List<Product> getProduct(int min, int max) {
		// TODO Auto-generated method stub
		return productDAO.findByPriceBetween(min, max);
	}

	@Override
	public String checkProductInventory(int productId, int quantityRequired) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getProductByPrice(int price) {
		// TODO Auto-generated method stub
		return productDAO.findByPrice(price);
	}
}
