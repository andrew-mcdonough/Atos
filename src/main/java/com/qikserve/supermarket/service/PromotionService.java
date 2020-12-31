package com.qikserve.supermarket.service;

import java.util.List;

import com.qikserve.supermarket.model.Promotion;

public interface PromotionService {

	List<Promotion> listAll();

	Promotion savePromotion(Promotion promotion);

	Promotion getPromotion(Integer promotionId);

	void deletePromotion(Integer promotionId);

}
