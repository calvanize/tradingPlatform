package com.fdmgroup.model;

public class PortFolio implements IStorable{
	
	private Person shareHolder;
	private Company company;
	private int shares;
	
	public PortFolio(Person shareHolder, Company company, int shares) {
		super();
		this.shareHolder = shareHolder;
		this.company = company;
		this.shares = shares;
	}

	public Person getShareHolder() {
		return shareHolder;
	}

	public void setShareHolder(Person shareHolder) {
		this.shareHolder = shareHolder;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public int getShares() {
		return shares;
	}

	public void setShares(int shares) {
		this.shares = shares;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((shareHolder == null) ? 0 : shareHolder.hashCode());
		result = prime * result + shares;
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
		PortFolio other = (PortFolio) obj;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (shareHolder == null) {
			if (other.shareHolder != null)
				return false;
		} else if (!shareHolder.equals(other.shareHolder))
			return false;
		if (shares != other.shares)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PortFolio [shareHolder=" + shareHolder + ", company=" + company + ", shares=" + shares + "]";
	}
	
	
}
