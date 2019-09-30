package com.fdmgroup.DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import com.fdmgroup.model.Role;
import com.fdmgroup.util.UniqueIDGenerator;

public class RoleDAO implements IRoleDAO{
	
	private List<Role> roleList;
	private UniqueIDGenerator gen;
	private static RoleDAO roleDao;
	

	private RoleDAO() {
		roleList = new ArrayList<>();
		
		gen = UniqueIDGenerator.getInstance();
		
		populateListTable();
	}
	
	public static RoleDAO getInstance(){
		if ( roleDao == null)
			roleDao = new RoleDAO();
		
		return roleDao;
	}

	@Override
	// argument has a role name
	public Role create(Role t) {
		if (findByRoleName(t.getRoleName()) == null || t.getRoleName().equals("HYBRID"))
		{
			Role createdRole = new Role(t.getRoleName());
			createdRole.setRoleID(gen.generateRoleID());
			return createdRole;
		}
		else
			return null;
		
		
	}

	@Override
	// you cannot edit a role
	public Role update(Role t) {
		/*Role tempRole = null;
		
		if (t.getRoleID() != 0 )
			tempRole = findByID(t.getRoleID());
		
		if (tempRole != null)
		{
			if( t.getRoleName() != null)
				tempRole.setRoleName(t.getRoleName());
		}
			
		return tempRole;*/
		return null;
	}

	@Override
	// can only add created role
	public Role insert(Role t) {
		if (t.getRoleID() == 0)
			return null;
		if (!t.getRoleName().equals("HYBRID"))
		{
			roleList.add(t);				
			return t;
		}
		else
		{
			if (findByRoleName("SHAREHOLDER") == null)
			{
				Role temp = new Role(Role.SHAREHOLDER);
				temp.setRoleID(t.getRoleID());
				insert(temp);
			}
				
			if (findByRoleName("BROKER") == null)
				insert(create(new Role(Role.BROKER)));
			return t;
		}

	}

	@Override
	// no need to remove roles
	public Role remove(Role t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> findAll() {
		return roleList;
	}

	@Override
	public Role findByID(int ID) {
		Role returnRole = null;
		
		ListIterator<Role> listIterator = roleList.listIterator();
		while(listIterator.hasNext())
		{
			returnRole = listIterator.next();
			if(returnRole.getRoleID() == ID)
				return returnRole;
		}
		
		return null;
	}

	@Override
	public Role findByRoleName(String name) {
		Role returnRole = null;
		
		ListIterator<Role> listIterator = roleList.listIterator();
		while(listIterator.hasNext())
		{
			returnRole = listIterator.next();
			if(returnRole.getRoleName().equalsIgnoreCase(name))
				return returnRole;
		}
		
		return null;
	}
	
	private void populateListTable(){
		Role role = new Role(Role.SHAREHOLDER);
		role = create(role);
		insert(role);
		
		Role role1 = new Role(Role.BROKER);
		role1 = create(role1);
		insert(role1);
		
		Role role2 = new Role(Role.ADMINISTRATOR);
		role2 = create(role2);
		insert(role2);
	}

}
