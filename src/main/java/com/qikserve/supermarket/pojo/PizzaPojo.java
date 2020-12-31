package com.qikserve.supermarket.pojo;

import java.math.BigDecimal;
import java.util.List;

public class PizzaPojo {
	private String id;
	private String name;
	private BigDecimal price;
	private List<PizzaPromoPojo> promotions;

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

	public List<PizzaPromoPojo> getPromotions() {
		return promotions;
	}

	public void setPromotions(List<PizzaPromoPojo> promotions) {
		this.promotions = promotions;
	}

}
