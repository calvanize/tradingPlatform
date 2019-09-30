package com.fdmgroup.controller;

import java.util.ArrayList;
import java.util.List;

import com.fdmgroup.DAO.CompanyDAO;
import com.fdmgroup.DAO.ICompanyDAO;
import com.fdmgroup.DAO.IPortFolioDAO;
import com.fdmgroup.DAO.IRequestDAO;
import com.fdmgroup.DAO.PortFolioDAO;
import com.fdmgroup.DAO.RequestDAO;
import com.fdmgroup.model.Company;
import com.fdmgroup.model.Person;
import com.fdmgroup.model.PortFolio;
import com.fdmgroup.model.Request;
import com.fdmgroup.model.Role;
import com.fdmgroup.model.SecurityRole;
import com.fdmgroup.view.ShareHolderView;
import com.fdmgroup.view.View;

public class ShareHolderController extends Controller{
	
	ICompanyDAO companyDAO;
	IPortFolioDAO portFolioDAO;
	IRequestDAO requestDAO;

	public ShareHolderController(View view, SecurityRole user) {
		super(view);
		if (!(getView()instanceof ShareHolderView))
			throw new ClassCastException("Error: View provided is not a guestView");
		setUser(user);
		companyDAO = CompanyDAO.getInstance();
		portFolioDAO = PortFolioDAO.getInstance();
		requestDAO = RequestDAO.getInstance();
	}

	@Override
	public void run() {
		ShareHolderView shareHolderView = (ShareHolderView)getView();
		int response = 0;
		do{
			shareHolderView.displayMenu(getUser());
			response = shareHolderView.getInteger();
			if (response == 1)
				viewProfile();
			else if (response == 2)
				viewPortFolio();
			else if (response == 3)
				viewRequestHistory();
			else if (response == 4)
				viewTradeHistory();
			else if (response == 5)
				makeRequest();
		}while (response != 6);
		
	}
	
	private void makeRequest() {
		ShareHolderView shareHolderView = (ShareHolderView)getView();
		int inputPrompt = 0;
		String requestType = null;
		List<PortFolio> portFolioList = null;
		List<Company> companyList = new ArrayList<>();
		Company company = null;
		int shares = 0;
		String timeInForce = null;
		int miniShares = 0;
		double limitPrice = 0;
	    double stopPrice = 0;
	    Request request = null;
	    Person person = getPersonDAO().findByID(getUser().getPersonID());
		
		
		// get request type
		shareHolderView.promptRequestType();
		inputPrompt = shareHolderView.getInteger();
		while (inputPrompt != 1 && inputPrompt != 2)
		{
			System.err.println("Invalid request type selected");
			shareHolderView.promptRequestType();
			inputPrompt = shareHolderView.getInteger();
		}
		
		if( inputPrompt == 1)
			requestType = "BUY";
		else
			requestType = "SELL";
		// for "BUY"
		
		// gets company user wants to issue an order
		if (requestType.equals("BUY"))
		{
			company = shareHolderView.getUserCompany(companyDAO.findAll(), requestType);
		} // end buy if
		else
		{
			portFolioList = portFolioDAO.findByUser(getUser().getPersonID());
			for (PortFolio portFolio : portFolioList) {
				companyList.add(portFolio.getCompany());
			}
			
			company = shareHolderView.getUserCompany(companyList, requestType);
		} // end sell if
		
		// get the number of shares
		shareHolderView.displayMessage("\nHow much shares do you want to buy: ");
		shares = shareHolderView.getInteger();
		while (shares == 0){
			System.err.println("\nShares cannot be zero");
			shareHolderView.displayMessage("\nHow much shares do you want to buy: ");
			shares = shareHolderView.getInteger();
		}
		
		// get time in force
		inputPrompt = shareHolderView.getTimeInForce();
		if ( inputPrompt == 1)
			timeInForce = "IMMEDIATE OR CANCEL";
		else if ( inputPrompt == 2)
			timeInForce = "GOOD UNTIL CANCELLED";
		else
			timeInForce = "DAY ONLY";
		
		// get mininum shares
		if (timeInForce.equals("IMMEDIATE OR CANCEL"))
			miniShares = shares;
		else
		{
			shareHolderView.displayMessage("\nPlease supply minimum shares: ");
			miniShares = shareHolderView.getInteger();
			while (miniShares < 0 || miniShares > shares){
				System.err.println("\nMinimum shares is invalid: either negative or greater than shares");
				shareHolderView.displayMessage("\nPlease supply minimum shares: ");
				miniShares = shareHolderView.getInteger();
			}
		}
		
		// get the order type
		inputPrompt = shareHolderView.getOrderType();
		if ( inputPrompt == 1)
		{
			limitPrice = 0;
			stopPrice = 0;
		}
		else if ( inputPrompt == 2)
		{
			shareHolderView.displayMessage("\nPlease supply Limit Price: ");
			limitPrice = shareHolderView.getDouble();
		}
		else
		{

			shareHolderView.displayMessage("\nPlease supply Stop Price: ");
			stopPrice = shareHolderView.getDouble();
		}
		
		request = new Request(null, requestType, person, company, shares, miniShares, timeInForce, limitPrice, stopPrice);
		// create request
		request = requestDAO.create(request);
		 // insert request
		if (request != null)
			requestDAO.insert(request);
		
		// to do call the display trade.
		
	}

	private void viewTradeHistory() {
		// TODO Auto-generated method stub
		
	}

	private void viewRequestHistory() {
		ShareHolderView shareHolderView = (ShareHolderView)getView();
		// get request from tradeDAO
		List<Request> requestList = requestDAO.findByUser(getUser());
		
		//displayRecord
		shareHolderView.displayRequestHistory(requestList);
		
	}

	private void viewPortFolio() {
		// TODO Auto-generated method stub
		
	}

	public void viewProfile(){
		ShareHolderView shareHolderView = (ShareHolderView)getView();
		// get person from personDAO
		Person person = getPersonDAO().findByID(getUser().getPersonID());
		// get roles from UserRoleDAO
		List<Role> roleList = getUserRoleDAO().findByUserID(getUser().getPersonID());
		// display records
		shareHolderView.displayProfile(person, roleList);
	}

}
