package com.qikserve.business;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.qikserve.supermarket.model.Promotion;
import com.qikserve.supermarket.pojo.BasketPojo;
import com.qikserve.supermarket.pojo.CheckoutTotal;
import com.qikserve.supermarket.pojo.UserPojo;
import com.qikserve.supermarket.service.PurchaseService;

@Service
public class PurchaseStore {
	Logger logger = LoggerFactory.getLogger(PurchaseStore.class);

	@Autowired
	PurchaseService purchaseService;

	public Promotion savePurchase(UserPojo user) {
		Promotion saveProduct = null;
		try {
			purchaseService.savePurchase(user);
		} catch (DataIntegrityViolationException data) {
			logger.info("Exception handler record not new " + data);
		} catch (Exception e) {
			logger.info("Exception " + e);
		}

		return saveProduct;
	}

	public CheckoutTotal checkout(String userId) {
		List<String> basketProducts = null;
		List<BasketPojo> basketProdTotals = new ArrayList<BasketPojo>();
		int totalPromos = 0;
		int rawPrice = 0;
		int totalPayable = 0;
		String products = null;
		CheckoutTotal checkoutTotal = new CheckoutTotal();
		// get list of products for userId
		basketProducts = purchaseService.getBasketProducts(userId);
		// for each product calculate the promotion value count
		basketProdTotals = purchaseService.getBasketProductPromo(basketProducts);

		// calculate the total price of each product and
		purchaseService.setBasketTotals(basketProdTotals);

		purchaseService.setBasketPromotions(basketProdTotals);

		for (BasketPojo prod : basketProdTotals) {
			logger.info("getRawPrice <" + prod.getRawPrice() + ">");
			logger.info("getTotalPayable  <" + prod.getTotalPayable() + ">");
			logger.info("totalPromos  <" + prod.getTotalPromos() + ">");
			totalPromos = totalPromos + prod.getTotalPromos();
			rawPrice = rawPrice + prod.getRawPrice();
			totalPayable = totalPayable + prod.getTotalPayable();
			products = products + prod.getProductId();
		}

		// add value to the total purchased amount.
		checkoutTotal.setTotalPayable(totalPayable);
		checkoutTotal.setRawPrice(rawPrice);
		checkoutTotal.setTotalPromos(totalPromos);
		checkoutTotal.setProductId(products);
		return checkoutTotal;
	}

}
