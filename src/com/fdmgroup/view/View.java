package com.fdmgroup.view;

import java.util.List;
import java.util.Scanner;

import com.fdmgroup.model.Person;
import com.fdmgroup.model.Role;

public abstract class View {
	
	private Scanner input;
	
	public View(){
		input = new Scanner(System.in);
	}
	
	public abstract void displayMenu();
	public void displayMessageLine(String message){
		System.out.println(message);
	}
	public void displayMessage( String message){
		System.out.print(message);
	}
	public void displayDollarAmount( double dollar){
		System.out.printf( "$%,.2f", dollar);
	}
	public int getInteger(){
		return input.nextInt();
	}
	
	public double getDouble(){
		return input.nextDouble();
	}
	
	public String getString(){
		return input.next();
	}
	
	public void displayLoginPage(){
		displayMessageLine("\n#####################");
		displayMessageLine("##       Login     ##");
		displayMessageLine("#####################");
		displayMessageLine("1 --> Login");
		displayMessageLine("2 --> Cancel");
	}
	
	public void displayProfile(Person person, List<Role> userRoles){
		displayMessageLine("\n-------------PROFILE------------");
		displayMessage("First Name: ");
		displayMessage(person.getFirstName());
		displayMessage("\nLast Name: ");
		displayMessage(person.getLastName());
		displayMessage("\nUsername: ");
		displayMessage(person.getUserName());
		displayMessageLine("\n Roles ");
		for (Role userRole : userRoles) {
			displayMessage(userRole.getRoleName() + " ");
		}
	}
	
	

}
