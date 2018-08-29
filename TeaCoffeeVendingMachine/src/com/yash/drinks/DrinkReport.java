package com.yash.drinks;

public class DrinkReport {
	private Integer drink_id;
	private String drink_name;
	private Integer drink_quantity = 0;
	private Double drink_collected_amount = 0.0;

	public Integer getDrink_id() {
		return drink_id;
	}

	public void setDrink_id(Integer drink_id) {
		this.drink_id = drink_id;
	}

	public String getDrink_name() {
		return drink_name;
	}

	public void setDrink_name(String drink_name) {
		this.drink_name = drink_name;
	}

	public Integer getDrink_quantity() {
		return drink_quantity;
	}

	public void setDrink_quantity(Integer drink_quantity) {
		this.drink_quantity = drink_quantity;
	}

	public Double getDrink_collected_amount() {
		return drink_collected_amount;
	}

	public void setDrink_collected_amount(Double drink_collected_amount) {
		this.drink_collected_amount = drink_collected_amount;
	}


}
