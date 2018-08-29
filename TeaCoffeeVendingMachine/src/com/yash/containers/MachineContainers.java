package com.yash.containers;

import java.io.Serializable;

public class MachineContainers implements Serializable {

	private Integer tea = 2000; // In Grams
	private Integer coffee = 2000; // In Grams
	private Integer sugar = 8000; // In Grams
	private Integer water = 15000; // In Milliliters
	private Integer milk = 10000; // In Milliliters

	private Integer waste_tea = 0; // In Grams
	private Integer waste_coffee = 0; // In Grams
	private Integer waste_sugar = 0; // In Grams
	private Integer waste_water = 0; // In Milliliters
	private Integer waste_milk = 0; // In Milliliters

	public Integer getTea() {
		return tea;
	}

	public void setTea(Integer tea) {
		this.tea = tea;
	}

	public Integer getCoffee() {
		return coffee;
	}

	public void setCoffee(Integer coffee) {
		this.coffee = coffee;
	}

	public Integer getSugar() {
		return sugar;
	}

	public void setSugar(Integer sugar) {
		this.sugar = sugar;
	}

	public Integer getWater() {
		return water;
	}

	public void setWater(Integer water) {
		this.water = water;
	}

	public Integer getMilk() {
		return milk;
	}

	public void setMilk(Integer milk) {
		this.milk = milk;
	}

	public Integer getWaste_tea() {
		return waste_tea;
	}

	public void setWaste_tea(Integer waste_tea) {
		this.waste_tea = waste_tea;
	}

	public Integer getWaste_coffee() {
		return waste_coffee;
	}

	public void setWaste_coffee(Integer waste_coffee) {
		this.waste_coffee = waste_coffee;
	}

	public Integer getWaste_sugar() {
		return waste_sugar;
	}

	public void setWaste_sugar(Integer waste_sugar) {
		this.waste_sugar = waste_sugar;
	}

	public Integer getWaste_water() {
		return waste_water;
	}

	public void setWaste_water(Integer waste_water) {
		this.waste_water = waste_water;
	}

	public Integer getWaste_milk() {
		return waste_milk;
	}

	public void setWaste_milk(Integer waste_milk) {
		this.waste_milk = waste_milk;
	}

}
