package com.qikserve.supermarket.mapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qikserve.SystemController;
import com.qikserve.supermarket.model.Promotion;
import com.qikserve.supermarket.pojo.BurgerPojo;
import com.qikserve.supermarket.pojo.FriesPojo;
import com.qikserve.supermarket.pojo.PizzaPojo;
import com.qikserve.supermarket.pojo.SaladPojo;

public class PromotionMapper {
	private static final Logger logger = LoggerFactory.getLogger(SystemController.class);

	public static Promotion mapPizzaPromotion(PizzaPojo promotion) {
		Promotion promotionDb = new Promotion();
		promotionDb.setPromotionid(promotion.getPromotions().get(0).getId());
		promotionDb.setProductid(promotion.getId());
		promotionDb.setType(promotion.getPromotions().get(0).getType());
		promotionDb.setRequired_qty(promotion.getPromotions().get(0).getRequired_qty());
		promotionDb.setType(promotion.getPromotions().get(0).getType());
		promotionDb.setPrice(promotion.getPromotions().get(0).getPrice());
		return promotionDb;
	}

	public static Promotion mapSaladPromotion(SaladPojo promotion) {
		Promotion promotionDb = new Promotion();
		promotionDb.setPromotionid(promotion.getPromotions().get(0).getId());
		promotionDb.setProductid(promotion.getId());
		promotionDb.setType(promotion.getPromotions().get(0).getType());
		promotionDb.setAmount(promotion.getPromotions().get(0).getAmount());
		return promotionDb;
	}

	public static Promotion mapBurgerPromotion(BurgerPojo promotion) {
		Promotion promotionDb = new Promotion();
		promotionDb.setPromotionid(promotion.getPromotions().get(0).getId());
		promotionDb.setProductid(promotion.getId());
		promotionDb.setType(promotion.getPromotions().get(0).getType());
		promotionDb.setRequired_qty(promotion.getPromotions().get(0).getRequired_qty());
		promotionDb.setFree_qty(promotion.getPromotions().get(0).getFree_qty());
		return promotionDb;
	}

	public static Promotion mapFriesPromotion(FriesPojo promotion) {
		Promotion promotionDb = new Promotion();
		return promotionDb;
	}

}
