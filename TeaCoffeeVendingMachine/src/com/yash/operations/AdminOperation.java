package com.yash.operations;

import java.util.HashMap;

public class AdminOperation {
	private final static HashMap<Integer, String> admin_operation_map = new HashMap<Integer, String>() {
		{
			put(1, "Refill Container");
			put(2, "Check Total Sale");
			put(3, "Container Status");
			put(4, "Reset Container");

		}
	};

	public HashMap<Integer, String> getAdminOperations() {
		return admin_operation_map;
	}
}
