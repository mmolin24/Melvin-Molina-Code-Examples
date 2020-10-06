/*
 * Class: CMSC201 
 * Instructor: Dr. Grinberg
 * Description: This program will calculate wind chill index after getting input from user and using a formula
 * Due: 9/16/2018
 * I pledge that I have completed the programming assignment independently.
   I have not copied the code from a student or any source.
   I have not given my code to any student.
   Print your Name here: Melvin Molina
*/

import java.util.Scanner;
public class WindChillTemperature {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		// Created scanner to be able to read user input
		
		final double POWER = 0.16;
		double temperature;
		double windSpeed;
		double windChill;
		double windSpeedChange;
		
		//Initialized all variables needed throughout the program
	
		
		System.out.print("Enter the temperature in Fahrenheit between -58ºF and 41ºF: ");
		temperature = input.nextDouble();
		
		// Prompted user to input Fahrenheit
		
		System.out.print("Enter the wind speed (>=2) in miles per hour: ");
		windSpeed = input.nextDouble();
		
		// Prompted user to input Wind Speed
		
		windSpeedChange= Math.pow(windSpeed, POWER);
				
		windChill= 35.74 + (.6215 * temperature) - (35.75*(windSpeedChange)) + ((.4275 * temperature)*(windSpeedChange));
				
		// Calculated Wind Chill Index
		
		System.out.print("\nThe wind chill index is " + windChill);
		
		// Displayed Wind Chill Index
		
		input.close();
	}

}
