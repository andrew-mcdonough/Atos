package com.qikserve.supermarket.pojo;

public class BurgerPromoPojo {
	private String id;
	private String type;
	private Integer required_qty;
	private Integer free_qty;

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

	public Integer getFree_qty() {
		return free_qty;
	}

	public void setFree_qty(Integer free_qty) {
		this.free_qty = free_qty;
	}
}
