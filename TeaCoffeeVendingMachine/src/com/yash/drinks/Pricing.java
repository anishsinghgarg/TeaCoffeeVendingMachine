package com.yash.drinks;

import java.util.HashMap;

public class Pricing {
	private final static HashMap<Integer, Double> drink_price_map = new HashMap<Integer, Double>() {
		{
			put(1, 10.0);
			put(2, 5.0);
			put(3, 15.0);
			put(4, 10.0);

		}
	};

	public HashMap<Integer, Double> getDrinksPricing() {
		return drink_price_map;
	}
}
