package com.qikserve.supermarket.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.qikserve.supermarket.model.Product;
import com.qikserve.supermarket.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	JdbcTemplate jdbc;

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> listAll() {
		List<Product> product = new ArrayList<>();
		productRepository.findAll().forEach(product::add);
		return product;
	}

	@Override
	public Product saveProduct(Product product) {
		Product saveProduct = null;
		try {
			saveProduct = productRepository.save(product);
		} catch (DataIntegrityViolationException data) {
			logger.info("Exception handler record not new " + data);
		} catch (Exception e) {
			logger.info("Exception " + e);
		}

		return saveProduct;
	}

	@Override
	public Product getProduct(Integer productId) {
		logger.info("get product " + productId);
		return productRepository.findById(productId).orElse(null);
	}

	@Override
	public void deleteProduct(Integer productId) {
		productRepository.deleteById(productId);
	}

}