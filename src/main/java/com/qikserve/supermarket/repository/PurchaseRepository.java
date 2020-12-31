package com.qikserve.supermarket.repository;

import org.springframework.data.repository.CrudRepository;

import com.qikserve.supermarket.model.User;

public interface PurchaseRepository extends CrudRepository<User, Integer> {

}
