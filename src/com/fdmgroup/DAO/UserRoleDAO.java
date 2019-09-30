package com.fdmgroup.DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import com.fdmgroup.model.Person;
import com.fdmgroup.model.Role;
import com.fdmgroup.model.UserRole;

public class UserRoleDAO implements IUserRoleDAO{
	
	private List<UserRole> userRolesList;
	private static UserRoleDAO userRoleDao;
	

	private UserRoleDAO(){
		userRolesList = new ArrayList<>();
		populateListTable();
	}
	
	public static UserRoleDAO getInstance(){
		
		if ( userRoleDao == null)
			userRoleDao = new UserRoleDAO();
		
		return userRoleDao;
	}
	@Override
	public UserRole create(UserRole t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserRole update(UserRole t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserRole insert(UserRole t) {
		if (findByID(t.getPerson().getPersonID(), t.getRole().getRoleID()) != null)
		{
			throw new IllegalArgumentException("Error: user already has this role");
			//return null;
		}
		else{
			userRolesList.add(t);
			return t;
		}
	}

	@Override
	public UserRole remove(UserRole t) {
		userRolesList.remove(t);
		return t;
	}

	@Override
	public List<UserRole> findAll() {
		return userRolesList;
	}

	@Override
	public UserRole findByID(int ID) {
		// TODO Auto-generated method stub
		return null;
	}

	public UserRole findByID(int ID, int ID2) {
		ListIterator<UserRole> listIterator = userRolesList.listIterator();
		UserRole tempUserRole = null;
		
		while (listIterator.hasNext())
		{
			tempUserRole = listIterator.next();
			if(tempUserRole.getPerson().getPersonID() == ID &&
					tempUserRole.getRole().getRoleID() == ID2)
				return tempUserRole;
		}
		
		return null;
	}

	@Override
	public boolean removeByID(int personID, int roleID) {

		UserRole tempUserRole = null;
		tempUserRole = findByID(personID, roleID);
		if ( tempUserRole != null){
			remove(tempUserRole);
			return true;
		}
			
		return false;
	}

	@Override
	public List<Role> findByUserID(int ID) {
		ListIterator<UserRole> listIterator = userRolesList.listIterator();
		List<Role> roleList = new ArrayList<>();
		UserRole tempUserRole = null;
		
		while (listIterator.hasNext())
		{
			tempUserRole = listIterator.next();
			if(tempUserRole.getPerson().getPersonID() == ID)
				roleList.add(tempUserRole.getRole());
		}
		if (roleList.size() == 0)
			return null;
		else
			return roleList;
	}

	@Override
	public List<Person> findByRoleID(int ID) {
		ListIterator<UserRole> listIterator = userRolesList.listIterator();
		List<Person> personList = new ArrayList<>();
		UserRole tempUserRole = null;
		
		while (listIterator.hasNext())
		{
			tempUserRole = listIterator.next();
			if(tempUserRole.getRole().getRoleID() == ID)
				personList.add(tempUserRole.getPerson());
		}
		if (personList.size() == 0)
			return null;
		else
			return personList;
	}

	private void populateListTable(){
		
		Role role = new Role(Role.SHAREHOLDER);
		role.setRoleID(1);
		
		Role role1 = new Role(Role.BROKER);
		role1.setRoleID(2);
		
		Role role2 = new Role(Role.ADMINISTRATOR);
		role2.setRoleID(3);
		
		Person person1 = null;
		for (int i = 1; i < 8; i++)
		{
			person1 = new Person("asmith" + i);
			person1.setUserID(i);
			person1.setFirstName("Alex" + i);
			person1.setLastName("Smith" + i);
			person1.setPassword(person1.getFirstName()+ person1.getLastName());
			
			if (i == 1 || i == 5)
			{
				insert(new UserRole(role2, person1));
			}
			else if (i == 2 || i == 6)
			{
				insert(new UserRole(role, person1));
			}
			else if ( i == 3)
			{
				insert(new UserRole(role1, person1));
			}
			else
			{
				insert(new UserRole(role, person1));
				insert(new UserRole(role1, person1));
			}
		}
		
		
	}
	
}
