package com.fdmgroup.view;

import java.util.Calendar;
import java.util.List;

import com.fdmgroup.model.Company;
import com.fdmgroup.model.Person;
import com.fdmgroup.model.Request;
import com.fdmgroup.model.Role;
import com.fdmgroup.model.SecurityRole;

public class ShareHolderView extends View{

	@Override
	public void displayMenu() {
		displayMessageLine("-Welcome Shareholder-");
		displayMessageLine("#####################");
		displayMessageLine("##       MENU      ##");
		displayMessageLine("#####################");
		displayMessageLine("1 --> View Profile");
		displayMessageLine("2 --> View PortFolio");
		displayMessageLine("3 --> View Request History");
		displayMessageLine("4 --> View Trade History");
		displayMessageLine("5 --> Make Order Or Request");
		displayMessageLine("6 --> Exit");
		
	}
	
	public void displayMenu(SecurityRole user) {
		displayMessage("\n-Welcome ");
		displayMessage(user.getUserName());
		displayMessage("-\n");
		displayMessageLine("#####################");
		displayMessageLine("##       MENU      ##");
		displayMessageLine("#####################");
		displayMessageLine("1 --> View Profile");
		displayMessageLine("2 --> View PortFolio");
		displayMessageLine("3 --> View Request History");
		displayMessageLine("4 --> View Trade History");
		displayMessageLine("5 --> Make Order Or Request");
		displayMessageLine("6 --> Exit");
		
	}

	public void displayProfile( Person person, List<Role> roleList){
		displayMessageLine("PROFILE");
		displayMessage("Username: " + person.getUserName());
		displayMessage("\nFirst Name: " + person.getFirstName());
		displayMessage("\nLast Name: " + person.getLastName());
		displayMessageLine("\nROLES: ");
		for (Role role : roleList) {
			displayMessageLine("      " + role.getRoleName());
		}
		displayMessageLine("\n");
		
	}
	
	public void promptRequestType(){
		displayMessageLine("\nSELECT REQUEST TYPE: ");
		displayMessageLine("1 --> BUY");
		displayMessageLine("2 --> SELL");
	}
	
	public void displayCompanyList(List<Company> companyList){
		for (int i = 0; i < companyList.size(); i++) {
			displayMessageLine(i + " --> " + companyList.get(i).getName());
		}
		
	}
	
	public Company getUserCompany(List<Company> companyList, String requestType){
		// fetch company from database
		Company company = null;
		int inputPrompt = 0;
		displayMessageLine("Please Select Company shares you wish to " + requestType);
		displayCompanyList(companyList);
		inputPrompt = getInteger();
		
		while ( !(inputPrompt >= 0 && inputPrompt < companyList.size()) )
		{
			System.err.println("Invalid company selected");
			displayMessageLine("Please Select Company shares you wish to " + requestType);
			displayCompanyList(companyList);
			inputPrompt = getInteger();
		}
		
		company = companyList.get(inputPrompt);
		return company;
	}
	
	public int getTimeInForce() {
		int inputPrompt = 0;
		
		displayMessageLine("\nPlease Select the Time in Force");
		displayMessageLine("1 --> IMMEDIATE OR CANCEL");
		displayMessageLine("2 --> GOOD UNTIL CANCELLED");
		displayMessageLine("3 --> DAY ONLY");
		inputPrompt = getInteger();
		while ( inputPrompt != 1 && inputPrompt != 2 && inputPrompt !=3)
		{
			System.err.println("Invalid company selected");
			
			displayMessageLine("\nPlease Select the Time in Force");
			displayMessageLine("1 --> IMMEDIATE OR CANCEL");
			displayMessageLine("2 --> GOOD UNTIL CANCELLED");
			displayMessageLine("3 --> DAY ONLY");
			inputPrompt = getInteger();
		}
		return inputPrompt;
	}
	
	public int getOrderType(){
		int inputPrompt = 0;
		
		displayMessageLine("\nPlease Select the Order Type");
		displayMessageLine("1 --> Market Order");
		displayMessageLine("2 --> Limit Order");
		displayMessageLine("3 --> Stop Order");
		inputPrompt = getInteger();
		while ( inputPrompt != 1 && inputPrompt != 2 && inputPrompt !=3)
		{
			System.err.println("Invalid company selected");
			
			displayMessageLine("\nPlease Select the Order Type");
			displayMessageLine("1 --> Market Order");
			displayMessageLine("2 --> Limit Order");
			displayMessageLine("3 --> Stop Order");
			inputPrompt = getInteger();
		}
		return inputPrompt;
	}

	public void displayRequestHistory(List<Request> requestList) {
		displayMessageLine("REQUEST HISTORY");
		System.out.printf("\n%-10s %-20s %-15s %-10s %-15s %-16s %-20s %-18s %-6s",
				"COMPANY", "REQUEST DATE", "SHARES FILLED" ,
				"SHARES", "REQUEST TYPE", "REQUEST STATUS",
				"TIME IN FORCE", "ORDER TYPE", "PRICE");
		
		/*displayMessage("\nCOMPANY\t");
		displayMessage("REQUEST DATE\t");
		displayMessage("SHARES FILLED\t");
		displayMessage("SHARES\t");
		displayMessage("REQUEST TYPE\t");
		displayMessage("REQUEST STATUS\t");
		displayMessage("TIME IN FORCE\t");
		displayMessage("ORDER TYPE\t");
		displayMessage("PRICE\t");*/
		
		for (Request request : requestList) {
			System.out.printf("\n%-10s %-20s %-15d %-10d %-15s %-16s %-20s ",
					request.getCompany().getName(),
					request.getRequestDate().get(Calendar.DATE) + " " +
					request.getRequestDate().get(Calendar.MONTH) + " " +
					request.getRequestDate().get(Calendar.YEAR) + " " +
					request.getRequestDate().get(Calendar.HOUR) + " " +
					request.getRequestDate().get(Calendar.MINUTE) + " " +
					request.getRequestDate().get(Calendar.SECOND) + " ",
					request.getSharesFilled(), request.getShares(),
					request.getRequestType(), request.getRequestStatus(),
					request.getTimeInForce());		
					
			/*displayMessage("\n" + request.getCompany().getName() +"\t");
			displayMessage(request.getRequestDate().get(Calendar.DATE) + " " +
					request.getRequestDate().get(Calendar.MONTH) + " " +
					request.getRequestDate().get(Calendar.YEAR) + " " +
					request.getRequestDate().get(Calendar.HOUR) + " " +
					request.getRequestDate().get(Calendar.MINUTE) + " " +
					request.getRequestDate().get(Calendar.SECOND) + " " +
					"\t");
			displayMessage(request.getSharesFilled() + "\t");
			displayMessage(request.getShares() + "\t");
			displayMessage(request.getRequestType() + "\t");
			displayMessage(request.getRequestStatus() + "\t");
			displayMessage(request.getTimeInForce() + "\t");*/
			if (request.getLimitPrice() != 0)
			{
				System.out.printf("%-18s $%-,6.2f", "Limit Order",
						request.getLimitPrice());
			}
			else if (request.getStopPrice() != 0)
			{
				System.out.printf("%-18s $%-,6.2f", "Stop Order",
						request.getStopPrice());
				/*displayMessage(" Stop Order\t");
				displayMessage(request.getStopPrice() + "\t");*/
			}
			else
			{
				System.out.printf("%-18s $%-,6.2f", "Market Order",
						request.getCompany().getStartingPrice());
				
				/*displayMessage(" Market Order\t");
				displayMessage(request.getCompany().getStartingPrice() + "\t");*/
			}
		}
		displayMessageLine("\n");
		
		
	}
}
