package com.qikserve.supermarket.mapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qikserve.SystemController;
import com.qikserve.supermarket.model.User;
import com.qikserve.supermarket.pojo.UserPojo;

public class PurchaseMapper {
	private static final Logger logger = LoggerFactory.getLogger(SystemController.class);

	public static User mapUser(UserPojo user) {
		User userDb = new User();
		userDb.setUserid(user.getUserId());
		userDb.setProductid(user.getProductId());
		return userDb;
	}

}
