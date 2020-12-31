package com.qikserve.supermarket.repository;

import org.springframework.data.repository.CrudRepository;

import com.qikserve.supermarket.model.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {

}
