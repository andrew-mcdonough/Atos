package com.qikserve.supermarket.service;

import java.util.List;

import com.qikserve.supermarket.pojo.BasketPojo;
import com.qikserve.supermarket.pojo.UserPojo;

public interface PurchaseService {

	String savePurchase(UserPojo user);

	List<String> getBasketProducts(String userId);

	void setBasketPromotions(List<BasketPojo> basketProducts);

	List<BasketPojo> getBasketProductPromo(List<String> basketProducts);

	void setBasketTotals(List<BasketPojo> basketProducts);

}
