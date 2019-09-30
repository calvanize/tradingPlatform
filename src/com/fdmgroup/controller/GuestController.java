package com.fdmgroup.controller;

import com.fdmgroup.DAO.IRoleDAO;
import com.fdmgroup.DAO.RoleDAO;
import com.fdmgroup.model.Person;
import com.fdmgroup.model.Role;
import com.fdmgroup.model.SecurityRole;
import com.fdmgroup.model.UserRole;
import com.fdmgroup.view.GuestView;
import com.fdmgroup.view.View;

public class GuestController extends Controller{
	
	private IRoleDAO roleDAO;

	public GuestController(View view) {
		super(view);
		
		if (!(getView()instanceof GuestView))
			throw new ClassCastException("Error: View provided is not a guestView");
		
		roleDAO = RoleDAO.getInstance();
	}
	@Override
	public void run(){
		GuestView guestView = (GuestView)getView();
		int response = 0;
		do{
			guestView.displayMenu();
			response = guestView.getInteger();
			if (response == 1)
				login();
			else if (response == 2)
				register();
		}while (getUser() == null && response != 3);
	}
	public void register(){
		GuestView guestView = (GuestView)getView();
		
		int response = 0;
		int roleType = 0;
		Role role = null;
		Role createdRole = null;
		Person person = null;
		
		boolean feedback = false;
		final int REGISTER = 1;
		final int CANCEL = 2;
		
		do{
			person = new Person();
			guestView.displayRegisterPage();
			response = guestView.getInteger();
			switch(response){
			case REGISTER:
				guestView.displayMessage("\nPlease enter UserName: ");
				person.setUserName(guestView.getString());
				guestView.displayMessage("\nPlease enter first name: ");
				person.setFirstName(guestView.getString());
				guestView.displayMessage("\nPlease enter last name: ");
				person.setLastName( guestView.getString());
				guestView.displayMessage("\nPlease enter password: ");
				person.setPassword(guestView.getString());
				
				do{
					guestView.displayMessage("Please select your role");
					guestView.displayAvailableRoles();
					roleType = guestView.getInteger();
					
					switch( roleType ){
					case Role.ADMINISTRATOR:
						role = new Role(Role.ADMINISTRATOR);
						createdRole = roleDAO.create(role);
						break;
					case Role.BROKER:
						role = new Role(Role.BROKER);
						createdRole = roleDAO.create(role);
						break;
					case Role.HYBRID:
						role = new Role(Role.HYBRID);
						createdRole = roleDAO.create(role);
						break;
					case Role.SHAREHOLDER:
						role = new Role(Role.SHAREHOLDER);
						createdRole = roleDAO.create(role);
						break;
					default	:
						guestView.displayMessageLine("\nError: Invalid input");
					}
					
				}while (!(roleType >= 1 && roleType <= 4));
				
				// create and insert a person
				person = getPersonDAO().create(person);
				if (person != null)
				{
					person = getPersonDAO().insert(person);
					if (person != null)
					{
						logger.info("Person with username " + person.getUserName() + " was created and inserted");
						setUser(new SecurityRole(person.getPersonID(), person.getUserName()));
						feedback = true;
					}
					else
					{
						logger.error("Person cannot be inserted into database");
						feedback = false;
					}
				}
				
				// insert an already created role if person has been inserted
				if (createdRole != null && person != null)
				{
					roleDAO.insert(createdRole);
					logger.info("Role " + createdRole.getRoleName() + " was created and inserted");
				}
				
				// insert into the user-role table if person has been inserted
				if (!role.getRoleName().equals("HYBRID") && person != null)
				{
					// create a UserRole using fetching role from the db and person created	
					Role userRole = roleDAO.findByRoleName(role.getRoleName());
					if (getUserRoleDAO().insert(new UserRole(userRole, person)) != null)
					{
						logger.info("User " + person.getUserName() + " has been assigned " + userRole.getRoleName() + " role");
						getUser().addRoleID(userRole.getRoleID());
						getUser().setPersonID(person.getPersonID());
						getUser().setUserName(person.getUserName());
						feedback = true;
					}
					else
					{
						logger.error("User " + person.getUserName() + " could not be assigned"+ userRole.getRoleName() + " role");
						feedback = false;
						getUser().setPersonID(0);
					}
					
				}
				else if (person != null)
				{
					Role userRole = roleDAO.findByRoleName("SHAREHOLDER");
					if (getUserRoleDAO().insert(new UserRole(userRole, person)) != null)
					{
						logger.info("User " + person.getUserName() + " has been assigned " + " a SHAREHOLDER role");
						getUser().addRoleID(userRole.getRoleID());
						feedback = true;
					}
					else
					{
						logger.error("User " + person.getUserName() + " could not be assigned a HYBRID role");
						feedback = false;
						getUser().setPersonID(0);
					}
					
					userRole = roleDAO.findByRoleName("BROKER");
					if(getUserRoleDAO().insert(new UserRole(userRole, person)) != null)
					{
						logger.info("User " + person.getUserName() + " has been assigned " + " a BROKER role");
						logger.info("User " + person.getUserName() + " is an hybrid user");
						getUser().addRoleID(userRole.getRoleID());
						getUser().setPersonID(person.getPersonID());
						getUser().setUserName(person.getUserName());
						feedback = true;
					}
					else{
						logger.error("User " + person.getUserName() + " could not be assigned a HYBRID role");
						feedback = false;
						getUser().setPersonID(0);
					}
				}
				break;
			case CANCEL:
				guestView.displayRegisterPage();
				logger.info("User decided to cancel Registration");
				break;
			default :
				guestView.displayMessageLine("Error: Please enter 1 for login or 2 to cancel");	
				
			} // end response switch
				
		}while( !(response == 1 && feedback) && response != 2);
		
		//return feedback;
	}

}
