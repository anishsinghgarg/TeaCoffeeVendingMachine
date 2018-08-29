package com.yash.machine.actions;

import java.util.Scanner;

import com.yash.user.Users;

public class ValidateUserAction {
	// Here we validate Customer
	public String validateUser() {
		String user_type = null;
		Scanner input = new Scanner(System.in);
		System.out.print("Enter values and press Enter  ::\n1 for Customer ,2 for Admin , 3 for Vendor : \n");
		try {
			int user_id = input.nextInt();
			Users users = new Users();
			if (users.getUsers().containsKey(user_id)) {
				user_type = users.getUsers().get(user_id);
				System.out.println("You are ::" + user_type);
			} else {
				System.out.println("Warning :: Invalid User !!!");
				user_type = "unknown";
			}
		} catch (Exception e) {
			user_type = "unknown";
		}
		return user_type;
	}
}
