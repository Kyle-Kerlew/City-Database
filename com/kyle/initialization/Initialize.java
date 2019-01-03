package com.kyle.initialize;

/**
 * @Author Kyle Kerlew
 * @Description Exemplifies a running database of destinations composed of cities, their populations and crime rates.
 * Based off a program originally written in ANSI C
 */
import java.util.Scanner;
import com.kyle.destinations.Destination;

public class Initialize {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int totalDestinations = 5;
		int option = 0;
		Destination[] dest = new Destination[100];
		populateDestinations(dest, totalDestinations);
		while (option != 5) {
			String input = null;
			option = menu();
			switch (option) {
			case 1:
				addCity(dest, totalDestinations);
				totalDestinations++;
				break;
			case 2:
				System.out.println("What city do you want to search for?");
				input = sc.nextLine();
				searchCity(dest, input, totalDestinations);
				break;
			case 3:
				findMaxCrimeRate(dest, totalDestinations);
				break;
			case 4:
				System.out.println("What city are you updating data for?");
				input = sc.nextLine();
				updateData(dest, input, totalDestinations);
				break;
			default:
				break;
			}
		}
		sc.close();
	}

	/**
	 * This method displays the menu of options and takes the input of the user to
	 * select an option
	 * 
	 * @return menu choice selected by user
	 */
	private static int menu() {
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		while (choice < 1 || choice > 5) {
			System.out.print(
					"\n-1-Add new city \n-2-SearchCity\n-3-Find City with Max Robbery Rate\n-4-Add data to existing city\n-5-Quit Program\n ===>Enter choice: \n ");
			choice = sc.nextInt();
			if (choice < 1 || choice > 5) {
				System.out.println("Wrong option. Please try again.");
			}
		}
		return choice;
	}

	/**
	 * Pre-loads our 'database' with 5 destinations
	 * 
	 * @param dest              array of destinations
	 * @param totalDestinations number of destinations
	 */
	private static void populateDestinations(Destination[] dest, int totalDestinations) {
		for (int i = 0; i < totalDestinations; i++) {
			dest[i] = new Destination();
		}
		dest[0].setCity("Orlando");
		dest[0].setPopulation(259675);
		dest[0].setRobberyRate(238.8);

		dest[1].setCity("New York");
		dest[1].setPopulation(8473938);
		dest[1].setRobberyRate(915.7);

		dest[2].setCity("San Jose");
		dest[2].setPopulation(1009679);
		dest[2].setRobberyRate(106.2);

		dest[3].setCity("Buffalo");
		dest[3].setPopulation(258419);
		dest[3].setRobberyRate(67.3);

		dest[4].setCity("Columbus");
		dest[4].setPopulation(830811);
		dest[4].setRobberyRate(252.5);
	}

	/**
	 * Initializes new destination and takes necessary input to create a new
	 * destination
	 * 
	 * @param dest     array of destinations
	 * @param position position of the new destination
	 */
	private static void addCity(Destination[] dest, int position) {
		dest[position] = new Destination();
		boolean valid = false;
		String input = null;
		Scanner sc = new Scanner(System.in);
		while (!valid) {
			System.out.println("What is the name of the city?");
			input = sc.nextLine();
			valid = isValidInput(input, true, false, false);

		}
		valid = false;
		dest[position].setCity(input);
		while (!valid) {
			System.out.printf("What is the population of %s\n", dest[position].getCity());
			input = sc.nextLine();
			valid = isValidInput(input, false, true, false);
		}
		valid = false;
		dest[position].setPopulation(Integer.parseInt(input));
		while (!valid) {
			System.out.printf("What is the crime rate of %s", dest[position].getCity());
			input = sc.nextLine();
			valid = isValidInput(input, false, false, true);
		}
		dest[position].setRobberyRate(Double.parseDouble(input));
	}

	/**
	 * Searches for a city in the database and outputs the data associated with that
	 * city
	 * 
	 * @param dest              array of destinations
	 * @param cityName          city name that we're searching for
	 * @param totalDestinations total number of destinations in our database
	 */
	private static void searchCity(Destination[] dest, String cityName, int totalDestinations) {
		boolean found = false;
		for (int i = 0; i < totalDestinations; i++) {
			if (dest[i].getCity().equalsIgnoreCase(cityName)) {
				System.out.printf("City: %s\nPopulation:%d\nCrime rate: %.2f\n", dest[i].getCity(),
						dest[i].getPopulation(), dest[i].getRobberyRate());
				found = true;
				break;
			}
		}
		if (!found) {
			System.out.printf("We don't have any information for %s. Please try again later.\n", cityName);
		}
	}

	/**
	 * Determines which of the cities in the running database has the highest crime
	 * rate, outputs city, population, and crime rate.
	 * 
	 * @param dest              array of destinations
	 * @param totalDestinations number of destinations in our database
	 */
	private static void findMaxCrimeRate(Destination[] dest, int totalDestinations) {
		double maxCrimeRate = 0.00;
		String city = null;
		int population = 0;
		for (int i = 0; i < totalDestinations; i++) {
			if (dest[i].getRobberyRate() > maxCrimeRate) {
				maxCrimeRate = dest[i].getRobberyRate();
				city = dest[i].getCity();
				population = dest[i].getPopulation();
			}
		}
		System.out.printf("The highest crime rate is %.2f, in %s, which has a population of %d\n", maxCrimeRate, city,
				population);
	}

	/**
	 * Takes input to update a city's information
	 * 
	 * @param dest              array of destinations
	 * @param cityName          name of city we're updating data for
	 * @param totalDestinations total number of destinations in our database
	 */
	private static void updateData(Destination[] dest, String cityName, int totalDestinations) {
		String input = null;
		Scanner sc = new Scanner(System.in);
		for (Destination d : dest) {
			if (d.getCity().equalsIgnoreCase(cityName)) {
				boolean valid = false;
				while (!valid) {
					System.out.printf("What is the new population of %s?\n", cityName);
					input = sc.nextLine();
					valid = isValidInput(input, false, true, false);
				}
				d.setPopulation(Integer.parseInt(input));
				valid = false;
				while (!valid) {
					System.out.printf("What is the new crime rate of %s\n", cityName);
					input = sc.nextLine();
					valid = isValidInput(input, false, false, true);
				}
				d.setRobberyRate(Double.parseDouble(input));
				break;
			}
		}
	}

	/**
	 * Validates input for city names, populations and crime rates. Ensures that the
	 * characters are the right type.
	 * 
	 * @param input        input to be validated
	 * @param isCity       are we validating a city?
	 * @param isPopulation are we validating a population?
	 * @param isCrimeRate  are we validating a crime rate?
	 * @returns if the input is valid
	 */
	private static boolean isValidInput(String input, boolean isCity, boolean isPopulation, boolean isCrimeRate) {
		boolean isValid = false;
		if (isCity) {
			char[] characters = input.toCharArray();
			for (char c : characters) {
				if (Character.isAlphabetic(c) || Character.isWhitespace(c)) {
					isValid = true;
				} else {
					isValid = false;
					break;
				}
			}
		} else if (isPopulation) {
			char[] characters = input.toCharArray();
			for (char c : characters) {
				if (Character.isDigit(c)) {
					isValid = true;
				} else {
					isValid = false;
					break;
				}
			}
		} else if (isCrimeRate) {
			char[] characters = input.toCharArray();
			for (char c : characters) {
				if (Character.isDigit(c) || c == '.') {
					isValid = true;
				} else {
					isValid = false;
					break;
				}
			}
		}
		if (isValid == false) {
			System.out.println("Input contains an invalid character. Please try again.");
		}
		return isValid;
	}
}
