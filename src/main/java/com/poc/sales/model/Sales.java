package com.poc.sales.model;

import java.io.Serializable;

/**
 * Data model for sales
 * 
 * @author Test
 *
 */
@SuppressWarnings("serial")
public class Sales implements Serializable {

	private int quantity;
	private float cost;

	public Sales(int quantity, float cost) {
		super();
		this.setQuantity(quantity);
		this.setCost(cost);
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "Sales{" + "quantity=" + quantity + ", cost=" + cost + '}';
	}
}
