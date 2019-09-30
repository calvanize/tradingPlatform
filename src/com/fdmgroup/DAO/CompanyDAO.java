package com.fdmgroup.DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import com.fdmgroup.model.Company;
import com.fdmgroup.util.UniqueIDGenerator;

public class CompanyDAO implements ICompanyDAO{

	private List<Company> companyList;
	private UniqueIDGenerator gen;
	private static CompanyDAO compDao;
	
	private CompanyDAO(){
		// create an array of companies
		companyList = new ArrayList<>();
		
		// get the generator
		gen = UniqueIDGenerator.getInstance();	
		
		// populate the company list (optional)
		// with a populateListTable() method
		populateListTable();
	}


	public static CompanyDAO getInstance(){
		if (compDao == null)
			compDao = new CompanyDAO();
		
		return compDao;
	}
	
	@Override
	// the argument must be a company with only name and starting price filled
	public Company create(Company t) throws IllegalArgumentException{
		Company newCompany = null;
		
		//make sure the company passed as input has a name and starting price
		if (t.getName() == null || t.getStartingPrice() == 0 )
			throw new IllegalArgumentException( "Error: Argument must have a company name and starting price associated with it");
		
		newCompany = new Company(gen.generateCompID(), t.getName(), gen.generateCompStockID(), t.getStartingPrice());
		return newCompany;
	}

	@Override
	// you must provide a company with an ID or stockID, the update values should be other fields
	public Company update(Company t) {
		Company tempComp = null;
		
		if( t.getCompanyID() != 0 )
			tempComp = findByID(t.getCompanyID());
		else if ( t.getStockID() != 0 )
			tempComp = findByStockID(t.getStockID());
		
		//update if company is found
		if( tempComp != null && t.getName() != null)
		{
			if (findByCompanyName(t.getName()) == null)
				tempComp.setName(t.getName());
			else
				return null;
		}
		
		if (tempComp != null && t.getStartingPrice() != 0)
			tempComp.setStartingPrice(t.getStartingPrice());
		
		return tempComp;
	}

	@Override
	// insert a company that does not exist and return true else returns false
	// company must be created before adding
	public Company insert(Company t) throws IllegalArgumentException{
		if (findByCompanyName(t.getName()) != null)
		{
			throw new IllegalArgumentException("Error: Company name already exist in the system");
		}
		else{
			companyList.add(t);
			return t;
		}
		
	}

	@Override
	// removes a company that exist and returns true else return false
	// company can be removed by
	public Company remove(Company t) throws IllegalArgumentException{
		if( t.getName().charAt(0) == '~')
			throw new IllegalArgumentException( "Error: Trying to delete an already deleted company");
		t.setName("~" + t.getName());
		return t;

	}

	@Override
	public List<Company> findAll() {
		return companyList;
	}

	@Override
	public Company findByID(int ID) {
		Company returnCompany = null;
		
		ListIterator<Company> listIterator = companyList.listIterator();
		while(listIterator.hasNext())
		{
			returnCompany = listIterator.next();
			if(returnCompany.getCompanyID() == ID)
				return returnCompany;
		}
		
		return null;
	}

	@Override
	public Company findByCompanyName(String name) {
		Company tempComp = null;
		
		ListIterator<Company> listIterator = companyList.listIterator();
		// check to make sure company exist
		while (listIterator.hasNext())
		{
			tempComp = listIterator.next();
			if ( tempComp.getName().equalsIgnoreCase(name) || 
					tempComp.getName().equalsIgnoreCase("~" + name))
				return tempComp;
		}
		return null;
	}

	@Override
	public Company findByStockID(int stockID) {
		Company returnCompany = null;
		
		ListIterator<Company> listIterator = companyList.listIterator();
		while(listIterator.hasNext())
		{
			returnCompany = listIterator.next();
			if(returnCompany.getStockID() == stockID)
				return returnCompany;
		}
		
		return returnCompany;
	}

	@Override
	public boolean removeByCompanyName(String name) {
		Company tempComp = null;
		
		tempComp = findByCompanyName(name);
		if (tempComp != null ){
			remove(tempComp);
			return true;
		}
		
		return false;
	}

	@Override
	public boolean removeByStockID(int stockID) {
		Company tempComp = null;
		
		tempComp = findByStockID(stockID);
		if (tempComp != null ){
			remove(tempComp);
			return true;
		}
		
		return false;
	}

	@Override
	public boolean removeByID(int ID) {
		Company tempComp = null;
		
		tempComp = findByID(ID);
		if (tempComp != null ){
			remove(tempComp);
			return true;
		}
		
		return false;
	}
	
	private void populateListTable() {
		Company company = new Company();
		company.setName("Yahoo");
		company.setStartingPrice(20);
		company = create(company);
		insert(company);
		
		Company company1 = new Company();
		company1.setName("Google");
		company1.setStartingPrice(30);
		company1 = create(company1);
		insert(company1);
		
		Company company2 = new Company();
		company2.setName("Facebook");
		company2.setStartingPrice(30);
		company2 = create(company2);
		insert(company2);
		
		Company company3 = new Company();
		company3.setName("PayPal");
		company3.setStartingPrice(40);
		company3 = create(company3);
		insert(company3);
		
		Company company4 = new Company();
		company4.setName("Shell");
		company4.setStartingPrice(50);
		company4 = create(company4);
		insert(company4);
		
	}
}
