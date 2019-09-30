package com.fdmgroup.model;

import java.util.Calendar;

public class Trade implements IStorable{
	
	private int tradeID;
	private Company company;
	private Calendar transactionTime;
	private int shares;
	private double sharePrice;
	private double priceTotal;
	private Person buyer;
	private Person seller;
	private Request buyRequest;
	private Request sellRequest;
	
	public Trade( Company company, int shares, double sharePrice,
			Person buyer, Person seller, Request buyRequest, Request sellRequest)
	{
		if ( company == null )
			throw new IllegalArgumentException("Error: Company cannot be null");
		else
			this.company = company;
		if (shares == 0)
			throw new IllegalArgumentException("Error: shares cannot be zero");
		else
			this.shares = shares;
		
		
		this.sharePrice = sharePrice;
		this.priceTotal = shares * sharePrice;
		this.buyer = buyer;
		this.seller = seller;
		this.buyRequest = buyRequest;
		this.sellRequest = sellRequest;
		
	}
	
	public Trade(int tradeID, Company company, Calendar transactionTime, int shares, double sharePrice,
			Person buyer, Person seller, Request buyRequest, Request sellRequest) throws IllegalArgumentException {
		super();
		if ( tradeID == 0)
			throw new IllegalArgumentException("Error: tradeID cannot be null");
		else
			this.tradeID = tradeID;
		
		if ( company == null )
			throw new IllegalArgumentException("Error: Company cannot be null");
		else
			this.company = company;
		if ( transactionTime == null)
			throw new IllegalArgumentException("Error: Transaction time cannot be null");
		else
			this.transactionTime = transactionTime;
		
		if (shares == 0)
			throw new IllegalArgumentException("Error: shares cannot be zero");
		else
			this.shares = shares;
		
		
		this.sharePrice = sharePrice;
		this.priceTotal = shares * sharePrice;
		this.buyer = buyer;
		this.seller = seller;
		this.buyRequest = buyRequest;
		this.sellRequest = sellRequest;
	}
	public int getTradeID() {
		return tradeID;
	}
	public void setTradeID(int tradeID) {
		this.tradeID = tradeID;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public Calendar getTransactionTime() {
		return transactionTime;
	}
	public void setTransactionTime(Calendar transactionTime) {
		this.transactionTime = transactionTime;
	}
	public int getShares() {
		return shares;
	}
	public void setShares(int shares) {
		this.shares = shares;
	}
	public double getSharePrice() {
		return sharePrice;
	}
	public void setSharePrice(double sharePrice) {
		this.sharePrice = sharePrice;
	}
	public double getPriceTotal() {
		return priceTotal;
	}
	public void setPriceTotal(double priceTotal) {
		this.priceTotal = priceTotal;
	}
	public Person getBuyer() {
		return buyer;
	}
	public void setBuyer(Person buyer) {
		this.buyer = buyer;
	}
	public Person getSeller() {
		return seller;
	}
	public void setSeller(Person seller) {
		this.seller = seller;
	}
	public Request getBuyRequest() {
		return buyRequest;
	}
	public void setBuyRequest(Request buyRequest) {
		this.buyRequest = buyRequest;
	}
	public Request getSellRequest() {
		return sellRequest;
	}
	public void setSellRequest(Request sellRequest) {
		this.sellRequest = sellRequest;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((buyRequest == null) ? 0 : buyRequest.hashCode());
		result = prime * result + ((buyer == null) ? 0 : buyer.hashCode());
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		long temp;
		temp = Double.doubleToLongBits(priceTotal);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((sellRequest == null) ? 0 : sellRequest.hashCode());
		result = prime * result + ((seller == null) ? 0 : seller.hashCode());
		temp = Double.doubleToLongBits(sharePrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + shares;
		result = prime * result + tradeID;
		result = prime * result + ((transactionTime == null) ? 0 : transactionTime.hashCode());
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
		Trade other = (Trade) obj;
		if (buyRequest == null) {
			if (other.buyRequest != null)
				return false;
		} else if (!buyRequest.equals(other.buyRequest))
			return false;
		if (buyer == null) {
			if (other.buyer != null)
				return false;
		} else if (!buyer.equals(other.buyer))
			return false;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (Double.doubleToLongBits(priceTotal) != Double.doubleToLongBits(other.priceTotal))
			return false;
		if (sellRequest == null) {
			if (other.sellRequest != null)
				return false;
		} else if (!sellRequest.equals(other.sellRequest))
			return false;
		if (seller == null) {
			if (other.seller != null)
				return false;
		} else if (!seller.equals(other.seller))
			return false;
		if (Double.doubleToLongBits(sharePrice) != Double.doubleToLongBits(other.sharePrice))
			return false;
		if (shares != other.shares)
			return false;
		if (tradeID != other.tradeID)
			return false;
		if (transactionTime == null) {
			if (other.transactionTime != null)
				return false;
		} else if (!transactionTime.equals(other.transactionTime))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Trade [tradeID=" + tradeID + ", company=" + company.getName() + ", transactionTime=" + transactionTime.getTime()
				+ ", shares=" + shares + ", sharePrice=" + sharePrice + ", priceTotal=" + priceTotal + ", buyer="
				+ buyer + ", seller=" + seller + ", buyRequest=" + buyRequest.getRequestID() + ", sellRequest=" + sellRequest.getRequestID() + "]";
	}
	
	

}
