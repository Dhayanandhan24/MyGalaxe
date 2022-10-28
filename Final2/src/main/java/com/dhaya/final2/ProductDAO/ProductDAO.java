package com.dhaya.final2.ProductDAO;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dhaya.final2.model.Product;

@Repository
public interface ProductDAO extends CrudRepository<Product, Long>{
	public List<Product> findByProductName(String productName);
	public List<Product> findByPrice(int price);	
	public List<Product> findByPriceBetween(int min,int max);

}
