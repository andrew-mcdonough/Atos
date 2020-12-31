package com.qikserve.supermarket.mapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qikserve.SystemController;
import com.qikserve.supermarket.model.Product;
import com.qikserve.supermarket.pojo.ProductPojo;

public class ProductMapper {
	private static final Logger logger = LoggerFactory.getLogger(SystemController.class);

	public static Product mapProduct(ProductPojo product) {
		Product productDb = new Product();
		productDb.setProductId(product.getId());
		productDb.setName(product.getName());
		productDb.setPrice(product.getPrice());
		return productDb;
	}

}
