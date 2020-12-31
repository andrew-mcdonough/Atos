package com.qikserve.supermarket.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Promotion")
public class Promotion {

	@Id
	@GeneratedValue
	private Long id;

	private String promotionid;
	private String type;
	private String productid;
	private Integer required_qty;
	private Integer price;
	private Integer amount;
	private Integer free_qty;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPromotionid() {
		return promotionid;
	}

	public void setPromotionid(String promotionid) {
		this.promotionid = promotionid;
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

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getFree_qty() {
		return free_qty;
	}

	public void setFree_qty(Integer free_qty) {
		this.free_qty = free_qty;
	}

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

}
