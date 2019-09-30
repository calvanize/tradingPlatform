package com.fdmgroup.DAO;

import com.fdmgroup.model.Person;
import com.fdmgroup.model.SecurityRole;

public interface IPersonDAO extends IStorage<Person>{
	
	public SecurityRole AuthenticateUser(SecurityRole user, String password);
	public Person findByUserName(String userName);
	public boolean removeByID(int ID);
	public boolean removeByUserName(String userName);
}
