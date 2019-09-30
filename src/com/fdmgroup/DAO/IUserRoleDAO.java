package com.fdmgroup.DAO;

import java.util.List;

import com.fdmgroup.model.Person;
import com.fdmgroup.model.Role;
import com.fdmgroup.model.UserRole;

public interface IUserRoleDAO extends IStorage<UserRole>{
	
	public boolean removeByID(int personID, int roleID);
	public List<Role> findByUserID(int ID);
	public List<Person> findByRoleID(int ID);

}
