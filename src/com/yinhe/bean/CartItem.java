package com.yinhe.bean;

public class CartItem {

	  private Product product;//商品
	  
	  private int buyNum;//个数
	  
	  private double subTotal;//小计

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getBuyNum() {
		return buyNum;
	}

	public void setBuyNum(int buyNum) {
		this.buyNum = buyNum;
	}

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}
	  
	  
	  
}
