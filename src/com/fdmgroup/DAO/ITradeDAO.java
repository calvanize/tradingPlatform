package com.fdmgroup.DAO;

import java.util.Calendar;
import java.util.List;

import com.fdmgroup.model.Trade;
import com.fdmgroup.model.SecurityRole;

public interface ITradeDAO extends IStorage<Trade>{
	
	public List<Trade> findByCompany(String company, SecurityRole user);
	public List<Trade> findByDate( Calendar date, SecurityRole user);
	public List<Trade> findByUser(SecurityRole user);

}
