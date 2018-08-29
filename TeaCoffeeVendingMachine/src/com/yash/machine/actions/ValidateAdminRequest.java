package com.yash.machine.actions;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;
import com.yash.containers.MachineContainers;
import com.yash.drinks.DrinkReport;
import com.yash.interfaces.OperationsAdmin;
import com.yash.operations.AdminOperation;

public class ValidateAdminRequest implements OperationsAdmin {

	public HashMap<String, Object> adminRequests(HashMap<String, Object> master_map) {
		Scanner input = new Scanner(System.in);
		int operation_id = 0;
		int status_code = 0;
		// -99 = Exit
		// 0 = Error
		// -2= Invalid Request
		System.out.print(
				"Enter values for below Options  ::\n1 for Refill Container ,2 for Check Total Sale, 3 for Container Status  , 4 for Reset Container, 0 for Exit : \n");
		try {
			operation_id = input.nextInt();
			AdminOperation adminOperation = new AdminOperation();
			if (adminOperation.getAdminOperations().containsKey(operation_id)) {
				master_map.put("operation_id", operation_id);

				OperationsAdmin operations = new ValidateAdminRequest();
				master_map = operations.AdminJobs(master_map);

				if (master_map.containsKey("error")) {
					System.out.println("Something happened wrong. Please try again !!!");
					status_code = 0;
				} else {
					System.out.println("Operation Done!!!");
					status_code = 1;
				}

			} else {
				if (operation_id == 0) {
					System.out.println("Admin wants to Exit :-(");
					status_code = -99;
				} else {
					System.out.println("The value you have entered is invalid. Please try again !!!");
					status_code = -2;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		master_map.put("status_code", status_code);
		return master_map;
	}

	@Override
	public HashMap<String, Object> AdminJobs(HashMap<String, Object> master_map) {
		int operation_id = (int) master_map.get("operation_id");
		int refilling_counter = (int) master_map.get("refilling_counter");
		MachineContainers mcContainers = (MachineContainers) master_map.get("container");
		try {
			if (operation_id == 1) {// Refill Container
				MachineContainers _mcContainers = new MachineContainers();
				mcContainers = _mcContainers;
				refilling_counter = refilling_counter + 1;
				master_map.put("refilling_counter", refilling_counter);
				System.out.println(refilling_counter + " times refilling is done!!!");
			} else if (operation_id == 2) {// Check Total Sale
				double total_sale = 0.0;
				List<DrinkReport> reportList = (List<DrinkReport>) master_map.get("report_list");
				for (DrinkReport rpt : reportList) {
					total_sale = total_sale + rpt.getDrink_collected_amount();
					System.out.println("Total " + rpt.getDrink_name() + " Sale " + rpt.getDrink_quantity()
							+ " Cup and Cost is " + rpt.getDrink_collected_amount());

				}
				System.out.println("Total Sale : Rs. " + total_sale + "/-");
			} else if (operation_id == 3) {// Container Status
				System.out.println("***** Remaining quantity of Tea is ::" + mcContainers.getTea());
				System.out.println("***** Remaining quantity of Coffee is ::" + mcContainers.getCoffee());
				System.out.println("***** Remaining quantity of Water is ::" + mcContainers.getWater());
				System.out.println("***** Remaining quantity of Milk is ::" + mcContainers.getMilk());
				System.out.println("");
				System.out.println("***** Waste quantity of Tea is ::" + mcContainers.getWaste_tea());
				System.out.println("***** Waste quantity of Coffee is ::" + mcContainers.getWaste_coffee());
				System.out.println("***** Waste quantity of Water is ::" + mcContainers.getWaste_water());
				System.out.println("***** Waste quantity of Milk is ::" + mcContainers.getWaste_milk());

			} else if (operation_id == 4) {// Reset Container
				MachineContainers _mcContainers = new MachineContainers();
				mcContainers = _mcContainers;
				System.out.println("Reset Container Successfully.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			master_map.put("error", "Error");
		}
		master_map.put("container", mcContainers);
		return master_map;
	}

}
