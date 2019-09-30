package com.fdmgroup.DAO;

import com.fdmgroup.model.Role;

public interface IRoleDAO extends IStorage<Role>{
	
	public Role findByRoleName(String name);

}
