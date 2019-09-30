package com.fdmgroup.model;

import java.util.Calendar;

public class Request implements IStorable{
	
	private int requestID;
	private Request parentRequest;
	private int sharesFilled;
	private Person shareHolder;
	private Calendar requestDate;
	private String requestType;
	private String requestStatus;
	private Company company;
	private int shares;
	private int minimumShares;
	private String timeInForce;
	private double limitPrice;
	private double stopPrice;
	

	public Request(Request parentRequest, String requestType, Person shareHolder, Company company, int shares, int minimumShares, 
			String timeInForce, double limitPrice, double stopPrice)
	{
		
		setParentRequest(parentRequest);
		
		if( shareHolder == null)
			throw new IllegalArgumentException("Error: Cannot create a request with null shareholder");
		else
			this.shareHolder = shareHolder;
		
		if (requestType.equals("BUY") || requestType.equals("SELL"))
			this.requestType = requestType;
		else
			throw new IllegalArgumentException("Error: Request type can only be \"BUY\" or \"SELL\"");
		
		if (company == null )
			throw new IllegalArgumentException("Error: Cannot create a request with null Company");
		else
			this.company = company;
		
		this.shares = shares;	
		
		setTimeInForce( timeInForce);
		setMinimumShares(minimumShares);
		
		this.limitPrice = limitPrice;
		
		if ( getLimitPrice() != 0 && stopPrice != 0)
			throw new IllegalArgumentException("Error: Cannot set both limit price and stop price");
		else
			this.stopPrice = stopPrice;
	}
	public Request(int requestID, Request parentRequest, int sharesFilled, Person shareHolder, Calendar requestDate,
			String requestType, String requestStatus, Company company, int shares, int minimumShares,
			String timeInForce, double limitPrice, double stopPrice)  throws IllegalArgumentException{
		
		if (requestID == 0)
			throw new IllegalArgumentException("Error: Cannot create a request with null ID");
		else
			this.requestID = requestID;
		
		
		setParentRequest(parentRequest);
		
		if (sharesFilled != 0)
			throw new IllegalArgumentException("Error: Cannot create a request with filled shares");
		else
			this.sharesFilled = sharesFilled;
		
		
		if( shareHolder == null)
			throw new IllegalArgumentException("Error: Cannot create a request with null shareholder");
		else
			this.shareHolder = shareHolder;
		
		if ( requestDate == null )
			throw new IllegalArgumentException("Error: Cannot create a request with null dates");
		else
			this.requestDate = requestDate;
		
		if (requestType.equals("BUY") || requestType.equals("SELL"))
			this.requestType = requestType;
		else
			throw new IllegalArgumentException("Error: Request type can only be \"BUY\" or \"SELL\"");
		
		if ( !requestStatus.equals("ACTIVE"))
			throw new IllegalArgumentException("Error: You can only create a new active request type");
		else
			this.requestStatus = requestStatus;
		
		if (company == null )
			throw new IllegalArgumentException("Error: Cannot create a request with null Company");
		else
			this.company = company;

		this.shares = shares;	
		
		setTimeInForce( timeInForce);
			
		setMinimumShares(minimumShares);
		
		this.limitPrice = limitPrice;
		
		if ( getLimitPrice() != 0 && stopPrice != 0)
			throw new IllegalArgumentException("Error: Cannot set both limit price and stop price");
		else
			this.stopPrice = stopPrice;
	}



	public int getRequestID() {
		return requestID;
	}

	public Request getParentRequest() {
		return parentRequest;
	}


	// can only be used by the constructor
	private void setParentRequest(Request parentRequest) throws IllegalArgumentException {
		// cannot create a child request whose parent is tagged "IMMEDIATE OR CANCEL"
		if (parentRequest != null)
		{
			if (parentRequest.getTimeInForce().equals("IMMEDIATE OR CANCEL"))
				throw new IllegalArgumentException("Error: Cannot create a child request from an \"IMMEDIATE OR CANCEL\" request");
			this.parentRequest = parentRequest;
		}
			
	}

	public int getSharesFilled(){
		return sharesFilled;
	}

	public void setSharesFilled(int sharesFilled) throws IllegalArgumentException{
		if ( requestStatus.equals("COMPLETED") || requestStatus.equals("CANCELLED"))
			throw new IllegalArgumentException("Error: Cannot fill shares for a " + requestStatus + " request.");
		else if (sharesFilled > getShares())
			throw new IllegalArgumentException("Error: Shares filled > Shares");
		else if (sharesFilled < getMinimumShares())
			throw new IllegalArgumentException("Error: Shares filled is less than the minimum shares");
		else if( sharesFilled == shares )
		{
			this.sharesFilled = sharesFilled;
		}
		
		
		this.sharesFilled = sharesFilled;
	}

	public Person getShareHolder() {
		return shareHolder;
	}

	public Calendar getRequestDate() {
		return requestDate;
	}

	public String getRequestType() {
		return requestType;
	}

	public String getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(String requestStatus) throws IllegalArgumentException{
		
		// update 
		if ( getTimeInForce().equals("IMMEDIATE OR CANCEL") && sharesFilled != shares && requestStatus.equals("COMPLETED"))
		{
			this.requestStatus = "CANCELLED";
			throw new IllegalArgumentException("Error: shares filled is not equal to shares, status cannot be updated to " + requestStatus +
			" hence set to \"CANCELLED\" for " + getTimeInForce() + " requests");
		}
		else if (getTimeInForce().equals("IMMEDIATE OR CANCEL") && 
				(requestStatus.equals("CANCELLED") || (sharesFilled == shares && requestStatus.equals("COMPLETED"))))
			this.requestStatus = requestStatus;
		else if (getTimeInForce().equals("IMMEDIATE OR CANCEL") && requestStatus.equals("PARTIAL"))
		{
			this.requestStatus = "CANCELLED";
			throw new IllegalArgumentException("Error: Cannot set status to " + requestStatus +
			" hence status is set to \"CANCELLED\" for " + getTimeInForce() + " requests");
		}
		else if ( (getRequestStatus().equals("ACTIVE") || getRequestStatus().equals("PARTIAL")) && 
				requestStatus.equals("COMPLETED") && sharesFilled != shares && getTimeInForce().equals("GOOD UNTIL CANCELLED"))
			throw new IllegalArgumentException("Error: Cannot update status to " + requestStatus + " since sharesFilled != shares");
		else if ( (getRequestStatus().equals("ACTIVE") || getRequestStatus().equals("PARTIAL")) && 
				requestStatus.equals("COMPLETED") && sharesFilled == shares&& (getTimeInForce().equals("GOOD UNTIL CANCELLED") || getTimeInForce().equals("DAY ONLY")))
			this.requestStatus = requestStatus;
		// ensure you call "day only" request at the end of the day
		else if ( getRequestStatus().equals("PARTIAL") && 
				requestStatus.equals("COMPLETED") && sharesFilled >= minimumShares && getTimeInForce().equals("DAY ONLY"))
						this.requestStatus = requestStatus;
		else if ( getRequestStatus().equals("ACTIVE")  && 
				requestStatus.equals("COMPLETED") && sharesFilled < minimumShares && getTimeInForce().equals("DAY ONLY"))
		{
			this.requestStatus = "CANCELLED";
			throw new IllegalArgumentException("Error: shares filled is not equal to minimum shares, status cannot be updated to " + requestStatus +
			" hence set to \"CANCELLED\" for " + getTimeInForce() + " requests");				
		}
		else if ( (getRequestStatus().equals("ACTIVE") || getRequestStatus().equals("PARTIAL")) && 
				requestStatus.equals("PARTIAL") && sharesFilled < shares)
			this.requestStatus = requestStatus;
		else if ( (getRequestStatus().equals("ACTIVE") || getRequestStatus().equals("PARTIAL")) && 
				requestStatus.equals("PARTIAL") && sharesFilled == shares)
			throw new IllegalArgumentException("Error: cannot update status to " + requestStatus + " since sharesFilled == shares");
		else if (requestStatus.equals("CANCELLED") || requestStatus.equals("COMPLETED"))
			this.requestStatus = requestStatus;
		else
			throw new IllegalArgumentException("Error: Argument must be either \"PARTIAL\", \"CANCELLED\", OR \"COMPLETED\"");
		
		
	}

	public Company getCompany() {
		return company;
	}

	public int getShares()  throws IllegalArgumentException {
		return shares;
	}

	public int getMinimumShares() {
		return minimumShares;
	}

	public void setMinimumShares(int minimumShares) throws IllegalArgumentException{
		if(timeInForce.equals("IMMEDIATE OR CANCEL") && minimumShares != shares)
		{
			this.requestStatus = "CANCELLED";
			throw new IllegalArgumentException("Error: minimum shares must be equal to shares for" +
					getTimeInForce() + " request" + " hence status is set to \"CANCELLED\"");
		}
		else if ( minimumShares > shares)
			throw new IllegalArgumentException( "Error: minimum shares must be less than shares");
		else if (minimumShares >= 1)
			this.minimumShares = minimumShares;
		else{
			this.minimumShares = 1;
			throw new IllegalArgumentException( "Error: minimum shares must be at lease 1 hence it is set to 1");
		}
	}

	public String getTimeInForce() {
		return timeInForce;
	}

	// can only be used by the constructor
	private void setTimeInForce(String timeInForce) throws IllegalArgumentException {
		if (timeInForce.equals("IMMEDIATE OR CANCEL") || timeInForce.equals("DAY ONLY")
				|| timeInForce.equals("GOOD UNTIL CANCELLED"))
			this.timeInForce = timeInForce;
		else
			throw new IllegalArgumentException("Error: Illegal Argument " + timeInForce);
	}
	
	public double getLimitPrice(){
		return limitPrice;
	}
	
	public double getStopPrice(){
		return stopPrice;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		long temp;
		temp = Double.doubleToLongBits(limitPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + minimumShares;
		result = prime * result + ((parentRequest == null) ? 0 : parentRequest.hashCode());
		result = prime * result + ((requestDate == null) ? 0 : requestDate.hashCode());
		result = prime * result + requestID;
		result = prime * result + ((requestStatus == null) ? 0 : requestStatus.hashCode());
		result = prime * result + ((requestType == null) ? 0 : requestType.hashCode());
		result = prime * result + ((shareHolder == null) ? 0 : shareHolder.hashCode());
		result = prime * result + shares;
		result = prime * result + sharesFilled;
		temp = Double.doubleToLongBits(stopPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((timeInForce == null) ? 0 : timeInForce.hashCode());
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
		Request other = (Request) obj;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (Double.doubleToLongBits(limitPrice) != Double.doubleToLongBits(other.limitPrice))
			return false;
		if (minimumShares != other.minimumShares)
			return false;
		if (parentRequest == null) {
			if (other.parentRequest != null)
				return false;
		} else if (!parentRequest.equals(other.parentRequest))
			return false;
		if (requestDate == null) {
			if (other.requestDate != null)
				return false;
		} else if (!requestDate.equals(other.requestDate))
			return false;
		if (requestID != other.requestID)
			return false;
		if (requestStatus == null) {
			if (other.requestStatus != null)
				return false;
		} else if (!requestStatus.equals(other.requestStatus))
			return false;
		if (requestType == null) {
			if (other.requestType != null)
				return false;
		} else if (!requestType.equals(other.requestType))
			return false;
		if (shareHolder == null) {
			if (other.shareHolder != null)
				return false;
		} else if (!shareHolder.equals(other.shareHolder))
			return false;
		if (shares != other.shares)
			return false;
		if (sharesFilled != other.sharesFilled)
			return false;
		if (Double.doubleToLongBits(stopPrice) != Double.doubleToLongBits(other.stopPrice))
			return false;
		if (timeInForce == null) {
			if (other.timeInForce != null)
				return false;
		} else if (!timeInForce.equals(other.timeInForce))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "Request [requestID=" + requestID + ", parentRequest=" + parentRequest + ", sharesFilled=" + sharesFilled
				+ ", shareHolder=" + shareHolder + ", requestDate=" + requestDate.getTime() + ", requestType=" + requestType
				+ ", requestStatus=" + requestStatus + ", company=" + company.getName() + ", shares=" + shares
				+ ", minimumShares=" + minimumShares + ", timeInForce=" + timeInForce + ", limitPrice=" + limitPrice
				+ ", stopPrice=" + stopPrice + "]";
	}

	

}
