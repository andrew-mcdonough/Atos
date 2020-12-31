package com.qikserve.supermarket.pojo;

import java.math.BigDecimal;
import java.util.List;

public class BurgerPojo {
	private String id;
	private String name;
	private BigDecimal price;
	private List<BurgerPromoPojo> promotions;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public List<BurgerPromoPojo> getPromotions() {
		return promotions;
	}

	public void setPromotions(List<BurgerPromoPojo> promotions) {
		this.promotions = promotions;
	}
}
