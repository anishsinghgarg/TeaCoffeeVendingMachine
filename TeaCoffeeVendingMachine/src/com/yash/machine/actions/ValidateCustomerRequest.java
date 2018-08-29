package com.yash.machine.actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.yash.containers.MachineContainers;
import com.yash.drinks.DrinkReport;
import com.yash.drinks.Drinks;
import com.yash.drinks.Pricing;
import com.yash.interfaces.DrinkValidation;
import com.yash.main.Launcher;

public class ValidateCustomerRequest implements DrinkValidation {

	// Here we validate Customer Request
	public HashMap<String, Object> customerRequests(HashMap<String, Object> main_map) {
		int drink_id = 0;
		int quantity = 0;
		String input_amount;
		int status_code = 0;
		// -99 = Exit
		// 0 = Error
		// -1= Insufficient Materials
		// -2= Invalid Request
		// 1= Delivered Successfully
		List<DrinkReport> reports = (List<DrinkReport>) main_map.get("report_list");
		HashMap<String, Object> master_map = new HashMap<>();

		Scanner input = new Scanner(System.in);
		System.out.print(
				"Enter values for Drink type  ::\n1 for Tea ,2 for Black Tea , 3 for Coffee  , 4 for Black Coffee, 0 for Exit : \n");
		try {
			drink_id = input.nextInt();
			Drinks drink = new Drinks();

			if (drink.getDrinks().containsKey(drink_id)) {
				int action_status = 0;

				System.out.println("Enter No of quantity ::");
				quantity = input.nextInt();
				do {
					Pricing pricing = new Pricing();
					Double drink_price = pricing.getDrinksPricing().get(drink_id);
					drink_price = drink_price * quantity;
					System.out.println("Pay Rs " + drink_price + "::");
					input_amount = input.next();
					// System.out.println("Rs " + input_amount + "::"+Double.compare(drink_price,
					// Double.valueOf(input_amount)));
					if (Double.compare(drink_price, Double.valueOf(input_amount)) != 0) {
						System.out.println("Entered Amount is not Sufficient!!");
					} else {
						action_status = 1;
					}
				} while (action_status == 0);
				System.out.println(
						"Customer Requested " + quantity + " cup of " + drink.getDrinks().get(drink_id) + " Drink.");
				if (quantity > 0) {
					master_map.put("quantity", quantity);
					master_map.put("drink_id", drink_id);
					master_map.put("container", (MachineContainers) main_map.get("container"));
					master_map.put("underflow", main_map.get("underflow").toString());
					DrinkValidation drinkValidate = new ValidateCustomerRequest();
					master_map = drinkValidate.isDrinkDeliverable(master_map);
					if (master_map.containsKey("error")) {
						if (master_map.get("error").toString().equals("Insufficient Materials")) {
							System.out.println("no enough material available !!!");
							master_map.put("underflow", "Y");
							status_code = -1;
						}
						if (master_map.get("error").toString().equals("Error")) {
							System.out.println("Something happened wrong. Please try again !!!");
							master_map.put("underflow", "N");
							status_code = 0;
						}
					} else {
						master_map.put("underflow", "N");
						Double paid_amount = (Double) master_map.get("drink_total_price");
						System.out.println("Drink Amount :: " + paid_amount);
						System.out.println("Drink delivered successfully :-)");
						for (DrinkReport rpt : reports) {
							if (rpt.getDrink_id() == drink_id) {
								DrinkReport _bean = new DrinkReport();
								_bean.setDrink_id(drink_id);
								_bean.setDrink_name(rpt.getDrink_name());
								_bean.setDrink_quantity(rpt.getDrink_quantity() + quantity);
								_bean.setDrink_collected_amount(rpt.getDrink_collected_amount() + paid_amount);
								reports.set(reports.indexOf(rpt), _bean);
							}
						}
						master_map.put("report_list", reports);
						status_code = 1;
					}
				}
			} else {
				if (drink_id == 0) {
					System.out.println("Customer want to Exit :-(");
					master_map.put("underflow", "N");
					status_code = -99;
				} else {
					System.out.println("The value you have entered is invalid. Please try again !!!");
					master_map.put("underflow", "N");
					status_code = -2;
				}

			}
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println("Something happened wrong. Please try again !!!");
			status_code = 0;
			master_map.put("underflow", "N");
		}
		master_map.put("status_code", status_code);
		return master_map;
	}

	@Override
	public HashMap<String, Object> isDrinkDeliverable(HashMap<String, Object> master_map) {

		HashMap<String, Object> _master_map = new HashMap<>();

		try {
			Pricing pricing = new Pricing();
			MachineContainers mcContainers = (MachineContainers) master_map.get("container");
			int drink_id = (int) master_map.get("drink_id"), quantity = (int) master_map.get("quantity");
			Double drink_price = pricing.getDrinksPricing().get(drink_id);
			// Tea
			if (drink_id == 1) {

				int tea = 6 * quantity;
				int water = 65 * quantity;
				int milk = 44 * quantity;
				int sugar = 17 * quantity;

				int waste_tea = 1 * quantity;
				int waste_water = 5 * quantity;
				int waste_milk = 4 * quantity;
				int waste_sugar = 2 * quantity;

				if (tea > mcContainers.getTea()) {
					_master_map.put("error", "Insufficient Materials");
					return _master_map;
				} else {
					mcContainers.setTea(mcContainers.getTea() - tea);
					mcContainers.setWaste_tea(mcContainers.getWaste_tea() + waste_tea);
				}
				if (water > mcContainers.getWater()) {
					_master_map.put("error", "Insufficient Materials");
					return _master_map;
				} else {
					mcContainers.setWater(mcContainers.getWater() - water);
					mcContainers.setWaste_water(mcContainers.getWaste_water() + waste_water);
				}
				if (milk > mcContainers.getMilk()) {
					_master_map.put("error", "Insufficient Materials");
					return _master_map;
				} else {
					mcContainers.setMilk(mcContainers.getMilk() - milk);
					mcContainers.setWaste_milk(mcContainers.getWaste_milk() + waste_milk);
				}
				if (sugar > mcContainers.getSugar()) {
					_master_map.put("error", "Insufficient Materials");
					return _master_map;
				} else {
					mcContainers.setSugar(mcContainers.getSugar() - sugar);
					mcContainers.setWaste_sugar(mcContainers.getWaste_sugar() + waste_sugar);
				}

			} else if (drink_id == 2) {// Black Tea

				int tea = 3 * quantity;
				int water = 112 * quantity;
				int sugar = 17 * quantity;

				int waste_tea = 0;
				int waste_water = 12 * quantity;
				int waste_sugar = 2 * quantity;

				if (tea > mcContainers.getTea()) {
					_master_map.put("error", "Insufficient Materials");
					return _master_map;
				} else {
					mcContainers.setTea(mcContainers.getTea() - tea);
				}
				if (water > mcContainers.getWater()) {
					_master_map.put("error", "Insufficient Materials");
					return _master_map;
				} else {
					mcContainers.setWater(mcContainers.getWater() - water);
					mcContainers.setWaste_water(mcContainers.getWaste_water() + waste_water);
				}

				if (sugar > mcContainers.getSugar()) {
					_master_map.put("error", "Insufficient Materials");
					return _master_map;
				} else {
					mcContainers.setSugar(mcContainers.getSugar() - sugar);
					mcContainers.setWaste_sugar(mcContainers.getWaste_sugar() + waste_sugar);
				}

			} else if (drink_id == 3) { // Coffee

				int coffee = 5 * quantity;
				int water = 23 * quantity;
				int milk = 88 * quantity;
				int sugar = 17 * quantity;

				int waste_coffee = 1 * quantity;
				int waste_water = 3 * quantity;
				int waste_milk = 8 * quantity;
				int waste_sugar = 2 * quantity;

				if (coffee > mcContainers.getCoffee()) {
					_master_map.put("error", "Insufficient Materials");
					return _master_map;
				} else {
					mcContainers.setCoffee(mcContainers.getCoffee() - coffee);
					mcContainers.setWaste_coffee(mcContainers.getWaste_coffee() + waste_coffee);
				}
				if (water > mcContainers.getWater()) {
					_master_map.put("error", "Insufficient Materials");
					return _master_map;
				} else {
					mcContainers.setWater(mcContainers.getWater() - water);
					mcContainers.setWaste_water(mcContainers.getWaste_water() + waste_water);
				}
				if (milk > mcContainers.getMilk()) {
					_master_map.put("error", "Insufficient Materials");
					return _master_map;
				} else {
					mcContainers.setMilk(mcContainers.getMilk() - milk);
					mcContainers.setWaste_milk(mcContainers.getWaste_milk() + waste_milk);
				}
				if (sugar > mcContainers.getSugar()) {
					_master_map.put("error", "Insufficient Materials");
					return _master_map;
				} else {
					mcContainers.setSugar(mcContainers.getSugar() - sugar);
					mcContainers.setWaste_sugar(mcContainers.getWaste_sugar() + waste_sugar);
				}

			} else if (drink_id == 4) { // Black Coffee

				int coffee = 2 * quantity;
				int water = 112 * quantity;
				int sugar = 17 * quantity;

				int waste_coffee = 0 * quantity;
				int waste_water = 12 * quantity;
				int waste_sugar = 2 * quantity;

				if (coffee > mcContainers.getCoffee()) {
					_master_map.put("error", "Insufficient Materials");
					return _master_map;
				} else {
					mcContainers.setCoffee(mcContainers.getCoffee() - coffee);
				}
				if (water > mcContainers.getWater()) {
					_master_map.put("error", "Insufficient Materials");
					return _master_map;
				} else {
					mcContainers.setWater(mcContainers.getWater() - water);
					mcContainers.setWaste_water(mcContainers.getWaste_water() + waste_water);
				}

				if (sugar > mcContainers.getSugar()) {
					_master_map.put("error", "Insufficient Materials");
					return _master_map;
				} else {
					mcContainers.setSugar(mcContainers.getSugar() - sugar);
					mcContainers.setWaste_sugar(mcContainers.getWaste_sugar() + waste_sugar);
				}

			}
			_master_map.put("drink_total_price", Double.valueOf(drink_price.doubleValue() * quantity));
			_master_map.put("container", mcContainers);
		} catch (Exception e) {
			e.printStackTrace();
			_master_map.put("error", "Error");
		}

		return _master_map;
	}
}
