package com.kyle.destinations;

/**
 * 
 * @author Kyle Kerlew
 * @Description JavaBean Class representing a Destination
 */
public class Destination {

	private String city;
	private int population;
	private double robberyRate;

	public Destination() { }

	public double getRobberyRate() {
		return robberyRate;
	}

	public void setRobberyRate(double robberyRate) {
		this.robberyRate = robberyRate;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
