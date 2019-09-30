package com.fdmgroup.DAO.test;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.DAO.TradeDAO;
import com.fdmgroup.model.Company;
import com.fdmgroup.model.Person;
import com.fdmgroup.model.Request;
import com.fdmgroup.model.SecurityRole;
import com.fdmgroup.model.Trade;

public class TradeDAOTest {

	private TradeDAO tradeDAO;
	private Trade trade;
	private Company company;
	private Request buyRequest;
	private Request sellRequest;
	private Request buyRequest1;
	private Request sellRequest1;
	private Person buyer;
	private Person buyer1;
	private Person seller;
	private Calendar date;
	private Trade trade1;
	private SecurityRole user;
	//private Trade trade2;
	
	@Before
	public void setUp(){
		buyer = new Person("calvanize");
		buyer1 = new Person("crazyDude");
		seller = new Person("vinnie");
		
		user = new SecurityRole("crazyDude");
		
		date = Calendar.getInstance();
		tradeDAO = TradeDAO.getInstance();
		
		company = new Company();
		company.setName("Google");
		company.setStartingPrice(89.8);
		
		buyRequest = new Request(1, null, 0, buyer, date, "BUY", "ACTIVE",
				company, 35, 35, "IMMEDIATE OR CANCEL", 34.3, 0);
		sellRequest = new Request(2, null, 0, seller, date, "SELL", "ACTIVE",
				company, 37, 10, "DAY ONLY", 34.3, 0);
		
		buyRequest1 = new Request(3, null, 0, buyer, date, "BUY", "ACTIVE",
				company, 50, 35, "GOOD UNTIL CANCELLED", 34.3, 0);
		sellRequest1 = new Request(4, null, 0, seller, date, "SELL", "ACTIVE",
				company, 50, 10, "DAY ONLY", 34.3, 0);
		
		trade = new Trade(company, 35, 34.3, buyer, seller, buyRequest, sellRequest);
		trade1 = new Trade(company, 50, 34.3, buyer1, seller, buyRequest1, sellRequest1);
		
	}
	
	@Test
	public void testCreateTrade(){
		assertNotNull(tradeDAO.insert(tradeDAO.create(trade)));
		//System.out.println(tradeDAO.findAll());
	}
	
	@Test
	public void testFindByID(){
		tradeDAO.insert(tradeDAO.create(trade));
		tradeDAO.insert(tradeDAO.create(trade1));
		//System.out.println(tradeDAO.findAll());
		//System.out.println(tradeDAO.findByID(1));
	
	}
	
	
	@Test
	public void testFindByCompany(){
		tradeDAO.insert(tradeDAO.create(trade));
		tradeDAO.insert(tradeDAO.create(trade1));
		//System.out.println(tradeDAO.findAll());
		//System.out.println(tradeDAO.findByCompany("Google1", user));
		
	}
	
	
	
	@Test
	public void testFindByDate(){
		Trade tempTrade = tradeDAO.create(trade);
		tempTrade.getTransactionTime().set(2016, 06, 14);
		
		Calendar findDate = Calendar.getInstance();
		findDate.set(2016, 6, 14);
		
		tradeDAO.insert(tempTrade);
		
		tradeDAO.insert(tradeDAO.create(trade1));
		//System.out.println(tradeDAO.findAll());
		//System.out.println(tradeDAO.findByDate(findDate, user));
		
	}
	
	@Test
	public void testFindByUser(){
		Trade tempTrade = tradeDAO.create(trade);
		tempTrade.getTransactionTime().set(2016, 06, 14);
		
		Calendar findDate = Calendar.getInstance();
		findDate.set(2016, 6, 14);
		
		tradeDAO.insert(tempTrade);
		
		tradeDAO.insert(tradeDAO.create(trade1));
		System.out.println(tradeDAO.findAll());
		System.out.println(tradeDAO.findByUser( user));
		
	}
	
	
}
