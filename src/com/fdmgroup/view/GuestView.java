package com.fdmgroup.view;

public class GuestView extends View{

	@Override
	public void displayMenu() {
		displayMessageLine("----Welcome Guest----");
		displayMessageLine("#####################");
		displayMessageLine("##       MENU      ##");
		displayMessageLine("#####################");
		displayMessageLine("1 --> Login");
		displayMessageLine("2 --> Register");
		displayMessageLine("3 --> Exit");
	}
	
	public void displayRegisterPage(){
		displayMessageLine("\n#####################");
		displayMessageLine("##     Register    ##");
		displayMessageLine("#####################");
		displayMessageLine("1 --> Register");
		displayMessageLine("2 --> Cancel");
	}

	public void displayAvailableRoles(){
		displayMessageLine("\n1 --> Administrator ");
		displayMessageLine("2 --> ShareHolder");
		displayMessageLine("3 --> Broker");
		displayMessageLine("4 --> Hybrid(ShareHolder and Broker)");
		
	}

}
