package com.fdmgroup.model;

public class Role implements IStorable{
	
	private int roleID;
	private String roleName;
	public static final int ADMINISTRATOR = 1;
	public static final int SHAREHOLDER = 2;
	public static final int BROKER = 3;
	public static final int HYBRID = 4;
	
	public Role(int role){
		setRoleName(role);
	}
	public Role(String roleName){
		this.roleName = roleName;
	}
	
	public int getRoleID() {
		return roleID;
	}
	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}
	public String getRoleName() {
		return roleName;
	}
	private void setRoleName(int role) {
		switch( role )
		{
		case ADMINISTRATOR:
			roleName = "ADMINISTRATOR";
			break;
		case SHAREHOLDER:
			roleName = "SHAREHOLDER";
			break;
		case BROKER:
			roleName = "BROKER";
			break;
		case HYBRID:
			roleName = "HYBRID";
			break;
		default :
				throw new IllegalArgumentException("Error: Invalid role supplied");
			
		}
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + roleID;
		result = prime * result + ((roleName == null) ? 0 : roleName.hashCode());
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
		Role other = (Role) obj;
		if (roleID != other.roleID)
			return false;
		if (roleName == null) {
			if (other.roleName != null)
				return false;
		} else if (!roleName.equals(other.roleName))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Role [roleID=" + roleID + ", roleName=" + roleName + "]";
	}


}
