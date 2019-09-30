package com.fdmgroup.DAO;

import java.util.Calendar;
import java.util.List;

import com.fdmgroup.model.Request;
import com.fdmgroup.model.SecurityRole;

public interface IRequestDAO extends IStorage<Request>{

	public List<Request> findByUser(SecurityRole user);
	public List<Request> findByRequestDate(Calendar date, SecurityRole user);
	public List<Request> findByRequesType(String type, SecurityRole user);
	public List<Request> findByRequestStatus(String status, SecurityRole user);
	public List<Request> findByRequestedCompany(String company, SecurityRole user);
	public List<Request> findByTimeInForce(String timeInForce, SecurityRole user);
	public List<Request> findByOrderType( String orderType, SecurityRole user);
	public Request updateSharesFilled(int ID, int sharesFilled);
	public Request updateRequestStatus(int ID, String requestStatus);
	
	
}
