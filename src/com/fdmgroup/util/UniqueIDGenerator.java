package com.fdmgroup.util;

public class UniqueIDGenerator {
	
	private int compID;
	private int compStockID;
	private int requestID;
	private int tradeID;
	private int roleID;
	private int personID;
	private static UniqueIDGenerator gen;
	
	public static UniqueIDGenerator getInstance(int compID, int compStockID, int requestID,
				int tradeID, int roleID, int personID)
	{
		if ( gen == null)
			gen = new UniqueIDGenerator(compID, compStockID, requestID,
					tradeID, roleID, personID);
		return gen;
	}
	
	public static UniqueIDGenerator getInstance()
	{
		if ( gen == null)
			gen = new UniqueIDGenerator();
		return gen;
	}
	
	private UniqueIDGenerator(){
	}
	
	private UniqueIDGenerator(int compID, int compStockID, int requestID, int tradeID, int roleID, int personID) {
		super();
		this.compID = compID;
		this.compStockID = compStockID;
		this.requestID = requestID;
		this.tradeID = tradeID;
		this.roleID = roleID;
		this.personID = personID;
	}
	
	
	public int generateCompID(){
		return ++compID;
	}
	public int generateCompStockID(){
		return ++compStockID;
	}
	
	public int generateRequestID(){
		return ++requestID;
	}
	
	public int generateTradeID(){
		return ++tradeID;
	}
	
	public int generateRoleID(){
		return ++roleID;
	}
	
	public int generatePersonID(){
		return ++personID;
	}

	public int getCompID() {
		return compID;
	}

	public int getCompStockID() {
		return compStockID;
	}

	public int getRequestID() {
		return requestID;
	}

	public int getTradeID() {
		return tradeID;
	}

	public int getRoleID() {
		return roleID;
	}

	public int getPersonID() {
		return personID;
	}

	public static UniqueIDGenerator getGen() {
		return gen;
	}
	
	

}
