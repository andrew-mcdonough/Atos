package com.qikserve.supermarket.pojo;

public class PizzaPromoPojo {
	private String id;
	private String type;
	private Integer required_qty;
	private Integer price;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getRequired_qty() {
		return required_qty;
	}

	public void setRequired_qty(Integer required_qty) {
		this.required_qty = required_qty;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}
}
