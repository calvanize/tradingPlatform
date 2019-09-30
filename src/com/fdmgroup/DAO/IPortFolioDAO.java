package com.fdmgroup.DAO;

import java.util.List;

import com.fdmgroup.model.PortFolio;
import com.fdmgroup.model.Request;
import com.fdmgroup.model.Trade;

public interface IPortFolioDAO extends IStorage<PortFolio>{

	public boolean IsSharesEnough(Request request);
	public boolean IsOwnShares(Request request);
	public List<PortFolio> findByUser( int userID );
	public PortFolio findByCompany( int userID, String company);
	public void insertOrUpdateShares(Trade trade);
}
