package com.fdmgroup.model;

import java.util.ArrayList;
import java.util.List;

public class SecurityRole {
	
	private int personID;
	private String userName;
	private List<Integer> roleIDs;
	
	
	public SecurityRole( String userName){
		this.userName = userName;
		roleIDs = new ArrayList<>();
	}
	public SecurityRole( int personID, String userName){
		this.personID = personID;
		this.userName = userName;
		roleIDs = new ArrayList<>();
	}
	
	public int getPersonID() {
		return personID;
	}
	public void setPersonID(int personID) {
		this.personID = personID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public List<Integer> getRoleID() {
		return roleIDs;
	}
	public void addRoleID(int roleID) {
		this.roleIDs.add(roleID);
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + personID;
		result = prime * result + ((roleIDs == null) ? 0 : roleIDs.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SecurityRole other = (SecurityRole) obj;
		if (personID != other.personID)
			return false;
		if (roleIDs == null) {
			if (other.roleIDs != null)
				return false;
		} else if (!roleIDs.equals(other.roleIDs))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "SecurityRole [personID=" + personID + ", userName=" + userName + ", roleIDs=" + roleIDs + "]";
	}

	

}
