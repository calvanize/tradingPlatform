package com.fdmgroup.DAO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ListIterator;

import com.fdmgroup.model.SecurityRole;
import com.fdmgroup.model.Trade;
import com.fdmgroup.util.UniqueIDGenerator;

public class TradeDAO implements ITradeDAO{
	
	private List<Trade> tradeList;
	private UniqueIDGenerator gen;
	private static TradeDAO  tradeDao;
	

	private TradeDAO() {
		// create an array of trades
		tradeList = new ArrayList<Trade>();
		
		// get the generator
		gen = UniqueIDGenerator.getInstance();
		
		// populate the trade list (optional)
		// with a populateListTable() method
	}
	
	public static TradeDAO getInstance(){
		
		if ( tradeDao == null)
			tradeDao = new TradeDAO();
		
		return tradeDao;
	}

	@Override
	public Trade create(Trade t) {
		Trade createdTrade = null;
		createdTrade = new Trade(gen.generateTradeID(), t.getCompany(), Calendar.getInstance(), t.getShares(), t.getSharePrice(), 
				 t.getBuyer(), t.getSeller(), t.getBuyRequest(), t.getSellRequest());
		return createdTrade;
	}

	@Override
	public Trade update(Trade t) {
		// You cannot update a trade, umimplemented.
		return null;
	}

	@Override
	public Trade insert(Trade t) {
		// insert an already created trade
		tradeList.add(t);
		return t;
	}

	@Override
	public Trade remove(Trade t) {
		// unimplemented, cannot remove a trade.
		return null;
	}

	@Override
	public List<Trade> findAll() {
		return tradeList;
	}

	@Override
	public Trade findByID(int ID) {
		Trade returnTrade = null;
		
		ListIterator<Trade> listIterator = tradeList.listIterator();
		while(listIterator.hasNext())
		{
			returnTrade = listIterator.next();
			if(returnTrade.getTradeID() == ID)
				return returnTrade;
		}
		
		return null;
	}

	@Override
	public List<Trade> findByCompany(String company, SecurityRole user) {
		List<Trade> companyTrades = new ArrayList<>();
		Trade tempTrade = null;
		
		ListIterator<Trade> listIterator = tradeList.listIterator();
		while ( listIterator.hasNext())
		{
			tempTrade = listIterator.next();
			if( tempTrade.getCompany().getName().equals(company) && 
					(tempTrade.getBuyer().getUserName().equalsIgnoreCase(user.getUserName()) || 
							tempTrade.getSeller().getUserName().equalsIgnoreCase(user.getUserName())))
				companyTrades.add(tempTrade);
		}
		if( companyTrades.size() != 0)
			return companyTrades;
		else
			return null;
	}

	@Override
	public List<Trade> findByDate(Calendar date, SecurityRole user) {
		List<Trade> companyTrades = new ArrayList<>();
		Trade tempTrade = null;
		
		ListIterator<Trade> listIterator = tradeList.listIterator();
		while ( listIterator.hasNext())
		{
			tempTrade = listIterator.next();
			if(tempTrade.getTransactionTime().get(Calendar.MONTH) == date.get(Calendar.MONTH)
					&& tempTrade.getTransactionTime().get(Calendar.YEAR) == date.get(Calendar.YEAR)
					&& tempTrade.getTransactionTime().get(Calendar.DATE) == date.get(Calendar.DATE)
					&& (tempTrade.getBuyer().getUserName().equals(user.getUserName()) || tempTrade.getSeller().getUserName().equals(user.getUserName())))
				companyTrades.add(tempTrade);
		}
		if( companyTrades.size() != 0)
			return companyTrades;
		else
			return null;
	}

	@Override
	public List<Trade> findByUser(SecurityRole user) {
		List<Trade> companyTrades = new ArrayList<>();
		Trade tempTrade = null;
		
		ListIterator<Trade> listIterator = tradeList.listIterator();
		while ( listIterator.hasNext())
		{
			tempTrade = listIterator.next();
			if(tempTrade.getBuyer().getUserName().equalsIgnoreCase(user.getUserName()) || tempTrade.getSeller().getUserName().equalsIgnoreCase(user.getUserName()))
				companyTrades.add(tempTrade);
		}
		if( companyTrades.size() != 0)
			return companyTrades;
		else
			return null;
	}
	
	

}
