package com.yash.drinks;

import java.util.HashMap;

public class Drinks {
	private final static HashMap<Integer, String> drink_map = new HashMap<Integer, String>() {
		{
			put(1, "Tea");
			put(2, "Balck Tea");
			put(3, "Coffee");
			put(4, "Black Coffee");

		}
	};

	public HashMap<Integer, String> getDrinks() {
		return drink_map;
	}
}
