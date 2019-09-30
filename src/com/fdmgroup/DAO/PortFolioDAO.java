package com.fdmgroup.DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import com.fdmgroup.model.PortFolio;
import com.fdmgroup.model.Request;
import com.fdmgroup.model.Trade;

public class PortFolioDAO implements IPortFolioDAO{
	
	private List<PortFolio> portfolioList;
	private static PortFolioDAO portFolioDao;
	
	private PortFolioDAO(){
		portfolioList = new ArrayList<>();
	}
	
	public static PortFolioDAO getInstance(){
		if ( portFolioDao == null )
			portFolioDao = new PortFolioDAO();
		return portFolioDao;
	}

	@Override
	// will not be used
	public PortFolio create(PortFolio t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	// not Used
	public PortFolio update(PortFolio t) {
		
		return null;
	}

	@Override
	// not used
	public PortFolio insert(PortFolio t) {
		return t;
	}

	@Override
	// not used
	public PortFolio remove(PortFolio t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PortFolio> findAll() {
		// TODO Auto-generated method stub
		return portfolioList;
	}

	@Override
	// not used
	public PortFolio findByID(int ID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	// returns true of false if the shares are enough
	public boolean IsSharesEnough(Request request) {
		PortFolio tempPortFolio = null;
		tempPortFolio = findByCompany(request.getShareHolder().getPersonID(), request.getCompany().getName());
		if (tempPortFolio ==  null)
			return false;
		else if (tempPortFolio.getShares() < request.getShares())
			return false;
		else
			return true;
	}

	@Override
	public boolean IsOwnShares(Request request) {
		PortFolio tempPortFolio = null;
		tempPortFolio = findByCompany(request.getShareHolder().getPersonID(), request.getCompany().getName());
		if (tempPortFolio !=  null)
			return true;
		else
			return false;
	}

	@Override
	public List<PortFolio> findByUser(int userID) {
		ListIterator<PortFolio> listIterator = portfolioList.listIterator();
		List<PortFolio> returnList = new ArrayList<>();
		PortFolio tempPortFolio = null;
		
		while ( listIterator.hasNext()){
			tempPortFolio = listIterator.next();
			if (tempPortFolio.getShareHolder().getPersonID() == userID)
				returnList.add(tempPortFolio);
		}
		if (returnList.size() == 0)
			return null;
		else
			return returnList;
	}

	@Override
	public PortFolio findByCompany(int userID, String company) {
		ListIterator<PortFolio> listIterator = portfolioList.listIterator();
		PortFolio tempPortFolio = null;
		
		while ( listIterator.hasNext()){
			tempPortFolio = listIterator.next();
			if (tempPortFolio.getShareHolder().getPersonID() == userID
					&& tempPortFolio.getCompany().getName().equalsIgnoreCase(company))
				return tempPortFolio;
		}
		
		return null;
	}

	@Override
	// calls anytime a trade occurs
	public void insertOrUpdateShares(Trade trade) {
		PortFolio buyerPort = null;
		PortFolio sellerPort = null;
		
		buyerPort = findByCompany(trade.getBuyer().getPersonID(), trade.getCompany().getName());
		sellerPort = findByCompany(trade.getSeller().getPersonID(), trade.getCompany().getName());
		
		if (buyerPort == null)
			// add portFolio to the buyer
			portfolioList.add(new PortFolio(trade.getBuyer(), trade.getCompany(), trade.getShares()));
		else 
			buyerPort.setShares(buyerPort.getShares() + trade.getShares());
		
		if (sellerPort == null)
			// throw an exception. something went wrong
			throw new IllegalArgumentException("Error: Trade is an error, seller does not have the shares");		
		else
		{
			sellerPort.setShares(sellerPort.getShares() - trade.getShares());
			if (sellerPort.getShares() == 0)
				portfolioList.remove(sellerPort);
		}
	}

}
