package com.dhaya.final2.service;


import java.util.List;

import com.dhaya.final2.model.Product;


public interface ProductService {

	public String saveProduct(Product product);
	public String updateProduct(Product product);
	public String deleteProduct(long productId);
	
	public Product getProduct(long productId);
	public List<Product> getProduct();
	public boolean isProductExists(long productId);
	
	public List<Product> getProduct(String productName);
	public List<Product> searchProductByName(String productName);

	public List<Product> getProduct(int min,int max);
	
	public String checkProductInventory(int productId,int quantityRequired);		//In Stock , or Out of Stock
	public List<Product> getProductByPrice(int price);
}
