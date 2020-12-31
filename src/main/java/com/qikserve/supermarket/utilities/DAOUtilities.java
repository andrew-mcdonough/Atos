package com.qikserve.supermarket.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.qikserve.supermarket.mapping.ProductMapper;
import com.qikserve.supermarket.mapping.PromotionMapper;
import com.qikserve.supermarket.model.Product;
import com.qikserve.supermarket.model.Promotion;
import com.qikserve.supermarket.pojo.BurgerPojo;
import com.qikserve.supermarket.pojo.FriesPojo;
import com.qikserve.supermarket.pojo.PizzaPojo;
import com.qikserve.supermarket.pojo.ProductPojo;
import com.qikserve.supermarket.pojo.SaladPojo;
import com.qikserve.supermarket.service.ProductService;
import com.qikserve.supermarket.service.PromotionService;

@Component
public class DAOUtilities {

	@Autowired
	private JdbcTemplate jdbc;

	@Autowired
	private ProductService productService;

	@Autowired
	private PromotionService promotionService;

	Logger logger = LoggerFactory.getLogger(DAOUtilities.class);

	public void getProducts() {
		RestTemplate restTemplate = new RestTemplate();
		try {
			ProductPojo[] response = restTemplate.getForObject("http://localhost:8081/products", ProductPojo[].class);
			for (ProductPojo product : response) {
				Product prodTmp = ProductMapper.mapProduct(product);
				productService.saveProduct(prodTmp);
				// getPromotions(prodTmp.getProductId());
			}
		} catch (Exception e) {
			logger.info("Exception " + e);
		}
		getPromotions("");
		// return Arrays.asList(response.getBody());
	}

	public void getPromotions(String productId) {
		RestTemplate restTemplate = new RestTemplate();
		try {
			Promotion prodTmp = null;
			PizzaPojo pizzaPojo = restTemplate.getForObject("http://localhost:8081/products/Dwt5F7KAhi",
					PizzaPojo.class);
			if (pizzaPojo != null) {
				prodTmp = PromotionMapper.mapPizzaPromotion(pizzaPojo);
				promotionService.savePromotion(prodTmp);
			}

			BurgerPojo burgerPojo = restTemplate.getForObject("http://localhost:8081/products/PWWe3w1SDU",
					BurgerPojo.class);
			if (burgerPojo != null) {
				prodTmp = PromotionMapper.mapBurgerPromotion(burgerPojo);
				promotionService.savePromotion(prodTmp);
			}

			SaladPojo saladPojo = restTemplate.getForObject("http://localhost:8081/products/C8GDyLrHJb",
					SaladPojo.class);
			if (saladPojo != null) {
				prodTmp = PromotionMapper.mapSaladPromotion(saladPojo);
				promotionService.savePromotion(prodTmp);
			}

			FriesPojo friesPojo = restTemplate.getForObject("http://localhost:8081/products/4MB7UfpTQs",
					FriesPojo.class);
			if (friesPojo != null) {
				prodTmp = PromotionMapper.mapFriesPromotion(friesPojo);
				promotionService.savePromotion(prodTmp);
			}

		} catch (Exception e) {
			logger.info("Exception " + e);
		}

		// return Arrays.asList(response.getBody());
	}
}
