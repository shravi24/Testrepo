package com.FoodOrderException;

public class InvalidNameException extends Exception {

	public void nameInvalid() {
	
		System.out.println("Invalid Name Format");
	}
}