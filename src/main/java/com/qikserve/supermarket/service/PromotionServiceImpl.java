package com.qikserve.supermarket.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.qikserve.supermarket.model.Promotion;
import com.qikserve.supermarket.repository.PromotionRepository;

@Service
public class PromotionServiceImpl implements PromotionService {

	Logger logger = LoggerFactory.getLogger(PromotionServiceImpl.class);

	@Autowired
	JdbcTemplate jdbc;

	@Autowired
	private PromotionRepository promotionRepository;

	@Override
	public List<Promotion> listAll() {
		List<Promotion> product = new ArrayList<>();
		promotionRepository.findAll().forEach(product::add);
		return product;
	}

	@Override
	public Promotion savePromotion(Promotion product) {
		Promotion saveProduct = null;
		try {
			saveProduct = promotionRepository.save(product);
		} catch (DataIntegrityViolationException data) {
			logger.info("Exception handler record not new " + data);
		} catch (Exception e) {
			logger.info("Exception " + e);
		}

		return saveProduct;
	}

	@Override
	public Promotion getPromotion(Integer promotionId) {
		logger.info("get promo " + promotionId);
		return promotionRepository.findById(promotionId).orElse(null);
	}

	@Override
	public void deletePromotion(Integer promotionId) {
		promotionRepository.deleteById(promotionId);
	}

}