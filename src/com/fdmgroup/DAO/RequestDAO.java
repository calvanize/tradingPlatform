package com.fdmgroup.DAO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ListIterator;

import com.fdmgroup.model.Request;
import com.fdmgroup.model.SecurityRole;
import com.fdmgroup.util.UniqueIDGenerator;

public class RequestDAO implements IRequestDAO{
	
	private List<Request> requestList;
	private UniqueIDGenerator gen;
	private static RequestDAO requestDao;
	
	

	private RequestDAO() {
		requestList = new ArrayList<>();
		
		gen = UniqueIDGenerator.getInstance();
	}
	
	public static RequestDAO getInstance(){
		if (requestDao == null)
			requestDao = new RequestDAO();
		
		return requestDao;
	}

	@Override
	public Request create(Request t) {
		Request createdRequest = new Request(gen.generateRequestID(), t.getParentRequest(), 0, t.getShareHolder(),
				Calendar.getInstance(), t.getRequestType(), "ACTIVE", t.getCompany(), t.getShares(), t.getMinimumShares(),
				t.getTimeInForce(), t.getLimitPrice(), t.getStopPrice());
		return createdRequest;
	}

	@Override
	// not using this update
	public Request update(Request t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Request insert(Request t) {
		requestList.add(t);
		return t;
	}

	@Override
	// cannot remove a request
	public Request remove(Request t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Request> findAll() {
		return requestList;
	}

	@Override
	public Request findByID(int ID) {
		ListIterator<Request> listIterator = requestList.listIterator();
		
		Request returnRequest = null;
		while ( listIterator.hasNext())
		{
			returnRequest = listIterator.next();
			if ( returnRequest.getRequestID() == ID)
				return returnRequest;
		}
		return null;
	}

	@Override
	public List<Request> findByUser(SecurityRole user) {
		ListIterator<Request> listIterator = requestList.listIterator();
		
		List<Request> returnList = new ArrayList<>();
		Request tempRequest = null;
		
		while ( listIterator.hasNext())
		{
			tempRequest = listIterator.next();
			if ( tempRequest.getShareHolder().getPersonID() == user.getPersonID())
				returnList.add(tempRequest);
		}
		if ( returnList.size() != 0)
			return returnList;
		else
			return null;
	}


	@Override
	public List<Request> findByRequesType(String type, SecurityRole user) {
		ListIterator<Request> listIterator = requestList.listIterator();
		
		List<Request> returnList = new ArrayList<>();
		Request tempRequest = null;
		
		while ( listIterator.hasNext())
		{
			tempRequest = listIterator.next();
			if ( tempRequest.getShareHolder().getPersonID() == user.getPersonID()
					&& tempRequest.getRequestType().equals(type))
				returnList.add(tempRequest);
		}
		if ( returnList.size() != 0)
			return returnList;
		else
			return null;
	}

	@Override
	public List<Request> findByRequestStatus(String status, SecurityRole user) {
		ListIterator<Request> listIterator = requestList.listIterator();
		
		List<Request> returnList = new ArrayList<>();
		Request tempRequest = null;
		
		while ( listIterator.hasNext())
		{
			tempRequest = listIterator.next();
			if ( tempRequest.getShareHolder().getPersonID() == user.getPersonID()
					&& tempRequest.getRequestStatus().equals(status))
				returnList.add(tempRequest);
		}
		if ( returnList.size() != 0)
			return returnList;
		else
			return null;
	}

	@Override
	public List<Request> findByRequestedCompany(String company, SecurityRole user) {
		ListIterator<Request> listIterator = requestList.listIterator();
		
		List<Request> returnList = new ArrayList<>();
		Request tempRequest = null;
		
		while ( listIterator.hasNext())
		{
			tempRequest = listIterator.next();
			if ( tempRequest.getShareHolder().getPersonID() == user.getPersonID()
					&& tempRequest.getCompany().getName().equalsIgnoreCase(company))
				returnList.add(tempRequest);
		}
		if ( returnList.size() != 0)
			return returnList;
		else
			return null;
	}

	@Override
	public List<Request> findByTimeInForce(String timeInForce, SecurityRole user) {
		ListIterator<Request> listIterator = requestList.listIterator();
		
		List<Request> returnList = new ArrayList<>();
		Request tempRequest = null;
		
		while ( listIterator.hasNext())
		{
			tempRequest = listIterator.next();
			if ( tempRequest.getShareHolder().getPersonID() == user.getPersonID()
					&& tempRequest.getTimeInForce().equals(timeInForce))
				returnList.add(tempRequest);
		}
		if ( returnList.size() != 0)
			return returnList;
		else
			return null;
	}

	@Override
	public List<Request> findByOrderType(String orderType, SecurityRole user) {
		ListIterator<Request> listIterator = requestList.listIterator();
		
		List<Request> returnList = new ArrayList<>();
		Request tempRequest = null;
		
		while ( listIterator.hasNext())
		{
			tempRequest = listIterator.next();
			if ( tempRequest.getShareHolder().getPersonID() == user.getPersonID()
					&& tempRequest.getLimitPrice() != 0 && orderType.equals("Limit Order"))
				returnList.add(tempRequest);
			else if ( tempRequest.getShareHolder().getPersonID() == user.getPersonID()
					&& tempRequest.getStopPrice() != 0 && orderType.equals("Stop Order"))
				returnList.add(tempRequest);
		}
		if ( returnList.size() != 0)
			return returnList;
		else
			return null;
	}

	@Override
	public List<Request> findByRequestDate(Calendar date, SecurityRole user) {
		ListIterator<Request> listIterator = requestList.listIterator();
		
		List<Request> returnList = new ArrayList<>();
		Request tempRequest = null;
		
		while ( listIterator.hasNext())
		{
			tempRequest = listIterator.next();
			if ( tempRequest.getShareHolder().getPersonID() == user.getPersonID()
					&& tempRequest.getRequestDate().get(Calendar.YEAR) == date.get(Calendar.YEAR)
					&& tempRequest.getRequestDate().get(Calendar.MONTH) == date.get(Calendar.MONTH)
					&& tempRequest.getRequestDate().get(Calendar.DATE) == date.get(Calendar.DATE)
					)
				returnList.add(tempRequest);
		}
		if ( returnList.size() != 0)
			return returnList;
		else
			return null;
	}

	@Override
	public Request updateSharesFilled(int ID, int sharesFilled) {
		Request tempRequest = null;
		
		tempRequest = findByID(ID);
		
		if ( tempRequest != null)
			tempRequest.setSharesFilled(sharesFilled);
		
		return tempRequest;
	}

	@Override
	public Request updateRequestStatus(int ID, String requestStatus) {
		Request tempRequest = null;
		
		tempRequest = findByID(ID);
		
		if ( tempRequest != null)
			tempRequest.setRequestStatus(requestStatus);
		
		return tempRequest;
	}

}
