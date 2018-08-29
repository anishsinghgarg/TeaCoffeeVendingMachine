package com.yash.user;

import java.util.HashMap;

public class Users {
	private final static HashMap<Integer, String> user_map = new HashMap<Integer, String>() {
		{
			put(1, "Customer");
			put(2, "Admin");
			put(3, "Vendor");

		}
	};

	public HashMap<Integer, String> getUsers() {
		return user_map;
	}

}
