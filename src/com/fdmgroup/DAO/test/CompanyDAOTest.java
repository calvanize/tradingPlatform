package com.fdmgroup.DAO.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.DAO.CompanyDAO;
import com.fdmgroup.model.Company;

public class CompanyDAOTest {
	
	private CompanyDAO compDAO;
	
	@Before
	public void setUp(){
		compDAO = CompanyDAO.getInstance();
	}

	@Test
	public void testCreateForLegalCompanyArgument_HavingCompanyName_AndStartingPrice() {
		Company comp = new Company();
		comp.setName("Google");
		comp.setStartingPrice(89.8);
		//System.out.println(compDAO.create(comp));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreateForIllegalCompanyArgument_NoCompanyName() {
		Company comp = new Company();
		//comp.setName("Google");
		comp.setStartingPrice(89.8);
		compDAO.create(comp);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreateForIllegalCompanyArgument_NoStartingPrice() {
		Company comp = new Company();
		comp.setName("Google");
		//comp.setStartingPrice(89.8);
		compDAO.create(comp);
	}
	
	@Test
	public void testUpdateWithILLegalArgument(){
		Company comp = new Company();
		comp.setName("Google");
		comp.setStartingPrice(89);
		compDAO.insert(compDAO.create(comp));
		Company compUpdate = new Company();
		compUpdate.setName("Yahoo");
		compUpdate.setStartingPrice(34);
		assertNull(compDAO.update(compUpdate));
	}
	
	@Test
	public void testUpdateWithILegalArgument(){
		
		Company comp = new Company();
		comp.setName("Google");
		comp.setStartingPrice(50);
		compDAO.insert(compDAO.create(comp));
		
		Company comp2 = new Company();
		comp2.setName("Kenya");
		comp2.setStartingPrice(4);
		compDAO.insert(compDAO.create(comp2));
		
		//System.out.println(compDAO);
		
		Company compUpdate = new Company();
		compUpdate.setName("Yahoo");
		compUpdate.setCompanyID(1);
				
		Company compUpdate2 = new Company();
		compUpdate2.setName("Ariosh");
		compUpdate2.setStockID(2);
		compUpdate2.setStartingPrice(1000);
		
		comp = compDAO.update(compUpdate);
		comp2 = compDAO.update(compUpdate2);
		//System.out.println(compDAO);
		
		assertNotNull(comp);
		assertNotNull(comp2);
		
	}
	@Test
	public void testRemoveWithLegalArgument(){
		
		Company comp = new Company();
		comp.setName("Google");
		comp.setStartingPrice(50);
		compDAO.insert(compDAO.create(comp));
		
		Company comp2 = new Company();
		comp2.setName("Kenya");
		comp2.setStartingPrice(4);
		compDAO.insert(compDAO.create(comp2));
		
		//System.out.println(compDAO);
		compDAO.removeByCompanyName("Kenya");
		//System.out.println(compDAO);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testRemoveByNameAnAlreadyExistingCompany(){
		
		Company comp = new Company();
		comp.setName("Google");
		comp.setStartingPrice(50);
		compDAO.insert(compDAO.create(comp));
		
		Company comp2 = new Company();
		comp2.setName("Kenya");
		comp2.setStartingPrice(4);
		compDAO.insert(compDAO.create(comp2));
		
		//System.out.println(compDAO);
		compDAO.removeByCompanyName("Kenya");
		//System.out.println(compDAO);
		compDAO.removeByCompanyName("Kenya");
		//System.out.println(compDAO);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testRemoveByStockID(){
		
		Company comp = new Company();
		comp.setName("Google");
		comp.setStartingPrice(50);
		compDAO.insert(compDAO.create(comp));
		
		Company comp2 = new Company();
		comp2.setName("Kenya");
		comp2.setStartingPrice(4);
		compDAO.insert(compDAO.create(comp2));
		
		System.out.println(compDAO);
		compDAO.removeByStockID(8);
		System.out.println(compDAO);
		compDAO.removeByStockID(8);
		System.out.println(compDAO);
	}

}
