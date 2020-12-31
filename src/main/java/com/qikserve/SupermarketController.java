package com.qikserve;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qikserve.business.PurchaseStore;
import com.qikserve.supermarket.pojo.CheckoutTotal;
import com.qikserve.supermarket.pojo.PurchaseSummaryInfo;
import com.qikserve.supermarket.pojo.UserPojo;
import com.qikserve.supermarket.service.ProductService;

@Controller
@RequestMapping(path = "/qikserve")
public class SupermarketController {
	Logger logger = LoggerFactory.getLogger(SupermarketController.class);
	@Autowired
	ProductService productService;

	@Autowired
	PurchaseStore purchaseStore;

	@GetMapping("/checkout/{userId}")
	public @ResponseBody CheckoutTotal getCheckout(@PathVariable String userId) {

		return purchaseStore.checkout(userId);
	}

	@GetMapping(path = "/test")
	@CrossOrigin
	public @ResponseBody ResponseEntity getTest() {
		return new ResponseEntity<>("healthcheck", HttpStatus.OK);
	}

	@CrossOrigin
	@PostMapping(path = "/purchase", produces = "application/json;charset=UTF-8", consumes = "application/json;charset=UTF-8")
	public @ResponseBody ResponseEntity<PurchaseSummaryInfo> buyProduct(@RequestBody UserPojo user) {
		logger.info("SupermarketController :: buyProduct :: userId " + user.getProductId());
		PurchaseSummaryInfo purchaseSummaryInfo = new PurchaseSummaryInfo();
		purchaseStore.savePurchase(user);
		purchaseSummaryInfo.setMessage("Purchase Summary");
		return new ResponseEntity<>(purchaseSummaryInfo, HttpStatus.OK);
	}

}
