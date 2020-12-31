package com.qikserve.supermarket.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.qikserve.supermarket.mapping.PurchaseMapper;
import com.qikserve.supermarket.model.Product;
import com.qikserve.supermarket.model.Promotion;
import com.qikserve.supermarket.model.User;
import com.qikserve.supermarket.pojo.BasketPojo;
import com.qikserve.supermarket.pojo.CheckoutTotal;
import com.qikserve.supermarket.pojo.UserPojo;
import com.qikserve.supermarket.repository.PurchaseRepository;

@Service
public class PurchaseServiceImpl implements PurchaseService {
	Logger logger = LoggerFactory.getLogger(PurchaseServiceImpl.class);

	@Autowired
	JdbcTemplate jdbc;

	@Autowired
	private PurchaseRepository purchaseRepository;

	@Override
	public String savePurchase(UserPojo user) {
		User saved = null;
		try {
			User userDb = new User();
			userDb = PurchaseMapper.mapUser(user);
			saved = purchaseRepository.save(userDb);
		} catch (DataIntegrityViolationException data) {
			logger.info("Exception handler DataIntegrityViolationException " + data);
		} catch (Exception e) {
			logger.info("Exception " + e);
		}

		return saved.getUserid();
	}

	@Override
	public List<String> getBasketProducts(String userId) {
		List<String> listProduct = new ArrayList<String>();
		try {
			String queryString = "select productid from user where userId = ? ";
			listProduct = jdbc.queryForList(queryString, String.class, userId);
		} finally {
			logger.info("getBasketProducts finish ");
		}
		return listProduct;
	}

	@Override
	public void setBasketTotals(List<BasketPojo> basketProducts) {
		Integer price = null;
		for (BasketPojo basketTmp : basketProducts) {
			String queryString = "select price from product where product_id = ? ";
			price = jdbc.queryForObject(queryString, Integer.class, basketTmp.getProductId());
			logger.info("setBasketTotals  price " + price + "><" + basketTmp.getCount());
			long totalRawPrice = price * basketTmp.getCount();
			basketTmp.setRawPrice((int) totalRawPrice);
		}
	}

	@Override
	public void setBasketPromotions(List<BasketPojo> basketProducts) {
		List<Promotion> promo = null;
		CheckoutTotal checkoutTotal = new CheckoutTotal();
		int discountedPrice = 0;
		try {

			for (BasketPojo basketTmp : basketProducts) {
				int freeAmount, payAmount, rawPrice, discount;

				String queryString = "select * from promotion where productid = ? ";
				Object[] param = new Object[] { basketTmp.getProductId() };
				promo = jdbc.query(queryString, param, BeanPropertyRowMapper.newInstance(Promotion.class));

				String queryStrg = "select * from product where product_id = ? ";
				Object[] paramProd = new Object[] { basketTmp.getProductId() };
				Product product = jdbc.queryForObject(queryStrg, paramProd,
						BeanPropertyRowMapper.newInstance(Product.class));

				Integer productsBought = basketTmp.getCount().intValue();
				String productName = product.getName();
				// for now assume only 1 promotion per product
				switch (promo.get(0).getType()) {
				// case statements
				// values must be of same type of expression
				case "QTY_BASED_PRICE_OVERRIDE":
					// Statements
					logger.info("QTY_BASED_PRICE_OVERRIDE  type ");
					freeAmount = (int) ((productsBought > promo.get(0).getRequired_qty())
							? productsBought - promo.get(0).getRequired_qty()
							: 0);
					rawPrice = productsBought * product.getPrice().intValue();
					if (freeAmount > 0) { // some were discounted
						discountedPrice = (freeAmount * promo.get(0).getPrice());
						// nondiscountedPrice = promo.get(0).getRequired_qty() *
						// product.getPrice().intValue();
						basketTmp.setTotalPromos(discountedPrice);
					} else {
						basketTmp.setTotalPromos(0);
					}
					basketTmp.setRawPrice(rawPrice);
					basketTmp.setTotalPayable(rawPrice - discountedPrice);
					basketTmp.setProductId(productName + ",");
					logger.info("TOTAL PAYABEL QTY ");
					logger.info("TOTAL PAYABEL QTY " + basketTmp.getTotalPayable());
					break; // break is optional

				case "BUY_X_GET_Y_FREE":
					// Statements
					logger.info("BUY_X_GET_Y_FREE  type ");
					freeAmount = productsBought / (promo.get(0).getRequired_qty() + promo.get(0).getFree_qty());
					payAmount = productsBought - freeAmount;
					rawPrice = productsBought * product.getPrice().intValue();
					discount = freeAmount * product.getPrice().intValue();
					basketTmp.setRawPrice(rawPrice);
					basketTmp.setTotalPayable(rawPrice - discount);
					basketTmp.setTotalPromos(discount);
					basketTmp.setProductId(productName + ",");
					logger.info("TOTAL BUY_X_GET_Y_FREE QTY ");
					logger.info("TOTAL BUY_X_GET_Y_FREE QTY " + basketTmp.getTotalPayable());
					break; // break is optional
				case "FLAT_PERCENT":
					// Statements
					discount = (productsBought * product.getPrice().intValue() * promo.get(0).getAmount()) / 100;
					logger.info("FLAT DICSCOUNT " + discount);
					payAmount = (productsBought * product.getPrice().intValue()) - discount;
					rawPrice = productsBought * product.getPrice().intValue();

					basketTmp.setRawPrice(rawPrice);
					basketTmp.setTotalPayable(payAmount);
					basketTmp.setTotalPromos(discount);
					basketTmp.setProductId(productName + ",");
					logger.info("TOTAL FLAT_PERCENT QTY ");
					logger.info("TOTAL FLAT_PERCENT QTY " + basketTmp.getTotalPayable());
					break; // break is optional

				// We can have any number of case statements
				// below is default statement, used when none of the cases is true.
				// No break is needed in the default case.
				default:
					basketTmp.setTotalPromos(0);
					basketTmp.setRawPrice(product.getPrice().intValue());
					basketTmp.setProductId(productName + ",");
					basketTmp.setTotalPayable(product.getPrice().intValue());
				}

			}
		} finally {
			logger.info("setBasketPromotions finish price ");
		}

		checkoutTotal.setRawPrice(basketProducts.get(0).getRawPrice());
	}

	@Override
	public List<BasketPojo> getBasketProductPromo(List<String> basketProducts) {
		List<BasketPojo> basketProd = new ArrayList<BasketPojo>();
		Map<String, Long> productCounts = basketProducts.stream()
				.collect(Collectors.groupingBy(e -> e, Collectors.counting()));
		for (Map.Entry<String, Long> entry : productCounts.entrySet()) {
			System.out.println("Key : " + entry.getKey() + ", Value : " + entry.getValue());
			BasketPojo basketTmp = new BasketPojo();
			basketTmp.setCount(entry.getValue());
			basketTmp.setProductId(entry.getKey());
			basketProd.add(basketTmp);
		}

		return basketProd;
	}

}
