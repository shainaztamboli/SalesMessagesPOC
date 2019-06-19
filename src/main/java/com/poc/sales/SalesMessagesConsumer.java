package com.poc.sales;

import com.poc.sales.cache.SalesCache;
import com.poc.sales.model.Operations;
import com.poc.sales.model.Sales;

/**
 * Consumer to process sales data
 * 
 * @author Shainazt
 *
 */
public class SalesMessagesConsumer {

	public void onMessage(String message) {
		String[] messages = message.split(" ");
		String productType;
		int messageType = 1;
		if (messages.length == 7) {
			productType = messages[3];
			messageType = 2;
		} else if (Operations.checkValue(messages[0].toUpperCase())) {
			productType = messages[2];
			messageType = 3;
		} else {
			productType = messages[0];
		}

		SalesCache.incrementSalesCount();
		if (SalesCache.getSalesMessageCount() <= 50) {
			SalesCache.setKey(productType);
			switch (messageType) {
			case 1:
				SalesCache.setValue(productType,
						createSales(1, Float.parseFloat(messages[2].substring(0, messages[2].length() - 1))));
				break;
			case 2:
				int quantity = Integer.parseInt(messages[0]);
				float cost = Float.parseFloat(messages[5].substring(0, messages[5].length() - 1));
				SalesCache.setValue(productType, createSales(quantity, cost));
				break;
			case 3:
				SalesCache.changeValue(productType, Operations.getValue(messages[0].toUpperCase()),
						Float.parseFloat(messages[1].substring(0, messages[1].length() - 1)));
				break;
			}

			if (SalesCache.getSalesMessageCount() % 10 == 0) {
				showSalesDetailsReport();
			}
		} else {
			System.out.println("Application is not accepting new Sales Messages");
			showAdjustmentsReport();
		}
	}

	private Sales createSales(int quantity, float cost) {
		return new Sales(quantity, cost);
	}

	private void showSalesDetailsReport() {
		System.out.println("Sales Details Report:");
		System.out.println("Product Type \t No. of Sales \t Total Cost");

		SalesCache.getSalesCache().forEach((key, sales) -> {
			System.out.println(key + "\t\t\t\t" + sales.stream().mapToInt(o -> o.getQuantity()).sum() + "\t\t\t\t"
					+ sales.stream().mapToDouble(o -> (o.getCost() * o.getQuantity())).sum() + "p");
		});

		System.out.println();
		System.out.println();
	}

	private void showAdjustmentsReport() {
		System.out.println();
		System.out.println();
		System.out.println("Adjustments Report: ");
		System.out.println("Product Type \t\t Operation");
		SalesCache.getAdjustmentsMap().forEach((key, value) -> System.out.println(key + "\t\t\t\t" + value));
		System.out.println();
		System.out.println();
	}
}
