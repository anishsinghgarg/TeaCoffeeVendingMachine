package com.yash.machine.actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.sun.javafx.collections.MappingChange.Map;
import com.yash.containers.MachineContainers;
import com.yash.drinks.DrinkReport;
import com.yash.drinks.Drinks;

public class RequestProcessor {
	private static MachineContainers mcContainers;
	private List<DrinkReport> reportList;

	public void requestProcessor() {
		mcContainers = new MachineContainers();
		reportList = new ArrayList<DrinkReport>();
		String user_type = "";
		int status_code = 0;
		// -99 = Exit
		// 0 = Error
		// -1= Insufficient Materials
		// -2= Invalid Request
		// 1= Delivered Successfully
		System.out.println("**** Welcome to Coffee Center ****");
		ValidateUserAction userAction = new ValidateUserAction();

		HashMap<String, Object> master_map = new HashMap<>();
		HashMap<String, Object> slave_map = new HashMap<>();

		Drinks drink = new Drinks();
		for (Integer key : drink.getDrinks().keySet()) {
			DrinkReport baen = new DrinkReport();
			baen.setDrink_id(key);
			baen.setDrink_name(drink.getDrinks().get(key));
			baen.setDrink_collected_amount(0.0);
			baen.setDrink_quantity(0);
			reportList.add(baen);
		}
		master_map.put("underflow", "N");
		master_map.put("overflow", "N");
		master_map.put("refilling_counter", 0);
		do {
			user_type = userAction.validateUser();
			// For Customer
			if (user_type.equals("Customer")) {
				master_map.put("report_list", reportList);
				master_map.put("container", mcContainers);
				do {
					ValidateCustomerRequest customerRequest = new ValidateCustomerRequest();
					slave_map = customerRequest.customerRequests(master_map);
					status_code = (int) slave_map.get("status_code");
					mcContainers = (MachineContainers) slave_map.get("container");
					reportList = (List<DrinkReport>) slave_map.get("report_list");
					master_map.put("underflow", slave_map.get("underflow").toString());
				} while (status_code == -2 || status_code == -1 || status_code == 0);
			}
			if (user_type.equals("Admin")) {
				master_map.put("report_list", reportList);
				master_map.put("container", mcContainers);
				do {
					ValidateAdminRequest adminRequest = new ValidateAdminRequest();
					slave_map = adminRequest.adminRequests(master_map);
					status_code = (int) slave_map.get("status_code");
					mcContainers = (MachineContainers) slave_map.get("container");
				} while (status_code == -2 || status_code == -1 || status_code == 0 || status_code == 1);
			}

			if (user_type.equals("Vendor")) {
				System.out.println("Under Dvelopment !!!");
				status_code = 1;
			}
		} while (user_type.equals("unknown") || status_code == -99 || status_code == 1);
	}
}
