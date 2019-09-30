package com.fdmgroup.model;

public class Company implements IStorable{
	
	private int companyID; 
	private String name;
	private int stockID;
	private double startingPrice;
	
	
	public Company() {
	}
	public Company(int companyID, String name, int stockID, double startingPrice) {
		this.companyID = companyID;
		this.name = name;
		this.stockID = stockID;
		this.startingPrice = startingPrice;
	}
	public int getCompanyID() {
		return companyID;
	}
	public void setCompanyID(int companyID) {
		this.companyID = companyID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStockID() {
		return stockID;
	}
	public void setStockID(int stockID) {
		this.stockID = stockID;
	}
	public double getStartingPrice() {
		return startingPrice;
	}
	public void setStartingPrice(double startingPrice) {
		this.startingPrice = startingPrice;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + companyID;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(startingPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + stockID;
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
		Company other = (Company) obj;
		if (companyID != other.companyID)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(startingPrice) != Double.doubleToLongBits(other.startingPrice))
			return false;
		if (stockID != other.stockID)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Company [companyID=" + companyID + ", name=" + name + ", stockID=" + stockID + ", startingPrice="
				+ startingPrice + "]";
	}


}
