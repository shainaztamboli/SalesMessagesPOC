package com.poc.sales.cache;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.poc.sales.model.Operations;
import com.poc.sales.model.Sales;

/**
 * Cache for storing sales messages data
 * 
 * @author Shainazt
 *
 */
public class SalesCache {

	private static Map<String, List<Sales>> salesCache = new LinkedHashMap<String, List<Sales>>();
	private static int salesMessageCount;
	private static Map<String, List<Operations>> adjustmentsMap = new LinkedHashMap<>();

	public static List<Sales> getValue(String key) {
		return salesCache.get(getKey(key));
	}

	/**
	 * Adding key into the Map in case of key is not there
	 * 
	 * @param key
	 */
	public static void setKey(String key) {
		if (getKey(key) == null) {
			salesCache.put(key, new ArrayList<Sales>());
		}
		if (getAdjustmentsKey(key) == null) {
			adjustmentsMap.put(key, new ArrayList<>());
		}
	}

	public static void setValue(String key, Sales sales) {
		salesCache.get(getKey(key)).add(sales);
	}

	/**
	 * Performing the adjustments on the basis of given operations
	 * 
	 * @param key
	 * @param operation
	 * @param changeCost
	 */
	public static void changeValue(final String key, final Operations operation, final float changeCost) {
		adjustmentsMap.get(getAdjustmentsKey(key)).add(operation);
		getValue(key).forEach(sales -> {
			sales.setCost(getCost(operation, sales.getCost(), changeCost));
		});
	}

	/**
	 * Getting the cost on the basis of given operations
	 * 
	 * @param operation
	 * @param existingCost
	 * @param newCost
	 * @return newCost
	 */
	private static float getCost(Operations operation, float existingCost, float newCost) {
		float cost = operation.equals(Operations.ADD) ? (existingCost + newCost)
				: operation.equals(Operations.SUBSTRACT) ? (existingCost - newCost) : (existingCost * newCost);
		return cost > 0 ? cost : 0;
	}

	public static int getSalesMessageCount() {
		return salesMessageCount;
	}

	/**
	 * Storing the sales messages count
	 */
	public static void incrementSalesCount() {
		SalesCache.salesMessageCount++;
	}

	public static void clearCache() {
		salesCache.clear();
		salesMessageCount = 0;
		adjustmentsMap.clear();
	}

	public static Map<String, List<Sales>> getSalesCache() {
		return salesCache;
	}

	/**
	 * To get the correct key from map
	 * 
	 * @param productKey
	 * @return key available in map
	 */
	public static String getKey(final String productKey) {
		String key = null;
		for (String existingKey : salesCache.keySet()) {
			if (existingKey.startsWith(productKey) || productKey.startsWith(existingKey)) {
				key = existingKey;
			}
		}
		return key;
	}

	/**
	 * To get the correct key from map
	 * 
	 * @param productKey
	 * @return key available in map
	 */
	public static String getAdjustmentsKey(final String productKey) {
		String key = null;
		for (String existingKey : adjustmentsMap.keySet()) {
			if (existingKey.startsWith(productKey) || productKey.startsWith(existingKey)) {
				key = existingKey;
			}
		}
		return key;
	}

	public static Map<String, List<Operations>> getAdjustmentsMap() {
		return adjustmentsMap;
	}
}
