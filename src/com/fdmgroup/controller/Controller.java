package com.fdmgroup.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.fdmgroup.DAO.IPersonDAO;
import com.fdmgroup.DAO.IUserRoleDAO;
import com.fdmgroup.DAO.PersonDAO;
import com.fdmgroup.DAO.UserRoleDAO;
import com.fdmgroup.model.Person;
import com.fdmgroup.model.Role;
import com.fdmgroup.model.SecurityRole;
import com.fdmgroup.view.View;

public abstract class Controller {
	
	private SecurityRole user;
	private View view;
	private IPersonDAO personDAO;
	private IUserRoleDAO userRoleDAO;
	Logger logger;
	
	public Controller( View view){
		personDAO = PersonDAO.getInstance();
		userRoleDAO = UserRoleDAO.getInstance();
		this.view = view;
		logger = Logger.getLogger("SystemLogger");
		PropertyConfigurator.configure("log4j.properties");
	}
	
	public boolean authenticateUser(String password){	
		SecurityRole tempUser = user;
		user = personDAO.AuthenticateUser(user, password);
		if ( user != null ){
			List<Role> userRoles = userRoleDAO.findByUserID(user.getPersonID());
			for (Role userRole : userRoles) {
				user.addRoleID(userRole.getRoleID());
			}
			logger.info("User: " + user.getUserName() + " successfully logged in");
			return true;
		}
		logger.info("User: " + tempUser.getUserName() + " tried logging in but was unsuccessful");
		return false;
	
	}
	
	public boolean login(){
		int response = 0;
		String userName = null;
		String password = null;
		boolean feedback = false;
		final int LOGIN = 1;
		final int CANCEL = 2;
		
		do{
			view.displayLoginPage();
			response = view.getInteger();
			switch(response){
			case LOGIN:
				view.displayMessage("\nPlease enter UserName: ");
				userName = view.getString();
				view.displayMessage("\nPlease enter password: ");
				password = view.getString();
				// authenticate user
				user = new SecurityRole(userName);
				if (authenticateUser(password))
					feedback = true;
				else
					feedback = false;
				break;
			case CANCEL:
				feedback = false;
				logger.info("User decided to cancel login");
				break;
			default :
				view.displayMessageLine("Error: Please enter 1 for login or 2 to cancel");
				feedback = false;
			}
		}while(response != 1 && response != 2);
		
		return feedback;
	}
	
	public void viewProfile(){
		Person person = personDAO.findByID(user.getPersonID());
		List<Role> userRoles = userRoleDAO.findByUserID(user.getPersonID());
		view.displayProfile(person, userRoles);
	}

	public SecurityRole getUser() {
		return user;
	}

	public View getView() {
		return view;
	}

	public IPersonDAO getPersonDAO() {
		return personDAO;
	}

	public IUserRoleDAO getUserRoleDAO() {
		return userRoleDAO;
	}

	public Logger getLogger() {
		return logger;
	}
	public abstract void run();

	protected void setUser(SecurityRole user){
		this.user = user;
	}

}
