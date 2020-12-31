package com.qikserve.supermarket.pojo;

public class CheckoutTotal {

	private Integer rawPrice;
	private Integer totalPromos;
	private Integer totalPayable;
	private String productId;

	public Integer getRawPrice() {
		return rawPrice;
	}

	public void setRawPrice(Integer rawPrice) {
		this.rawPrice = rawPrice;
	}

	public Integer getTotalPromos() {
		return totalPromos;
	}

	public void setTotalPromos(Integer totalPromos) {
		this.totalPromos = totalPromos;
	}

	public Integer getTotalPayable() {
		return totalPayable;
	}

	public void setTotalPayable(Integer totalPayable) {
		this.totalPayable = totalPayable;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

}
