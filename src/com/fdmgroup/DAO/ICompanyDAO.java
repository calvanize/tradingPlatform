package com.fdmgroup.DAO;

import com.fdmgroup.model.Company;

public interface ICompanyDAO extends IStorage<Company>{
	
	public Company findByCompanyName( String name);
	public Company findByStockID( int stockID);
	public boolean removeByCompanyName( String name);
	public boolean removeByStockID( int stockID);
	public boolean removeByID( int ID);
	

}
