package com.poc.sales.model;

/**
 * Enum for Operations
 * 
 * @author Shainazt
 *
 */
public enum Operations {
	ADD, SUBSTRACT, MULTIPLY;

	public static boolean checkValue(String value) {
		for (Operations operations : values()) {
			if (operations.toString().equals(value)) {
				return true;
			}
		}
		return false;
	}

	public static Operations getValue(String value) {
		for (Operations operations : values()) {
			if (operations.toString().equals(value)) {
				return operations;
			}
		}
		return null;
	}

}
