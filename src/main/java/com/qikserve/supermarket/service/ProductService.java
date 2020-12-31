package com.qikserve.supermarket.service;

import java.util.List;

import com.qikserve.supermarket.model.Product;

public interface ProductService {

	List<Product> listAll();

	Product saveProduct(Product product);

	Product getProduct(Integer productId);

	void deleteProduct(Integer productId);

}
