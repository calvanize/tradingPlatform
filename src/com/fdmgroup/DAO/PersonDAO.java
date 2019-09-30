package com.fdmgroup.DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.fdmgroup.model.Person;
import com.fdmgroup.model.SecurityRole;
import com.fdmgroup.util.UniqueIDGenerator;

public class PersonDAO implements IPersonDAO{
	
	private List<Person> personList;
	private UniqueIDGenerator gen;
	private static PersonDAO personDao;
	Logger logger;

	private PersonDAO() {
		// create an array of person
		personList = new ArrayList<Person>();
		
		// get the generator
		gen = UniqueIDGenerator.getInstance();
		
		logger = Logger.getLogger("SystemLogger");
		PropertyConfigurator.configure("log4j.properties");
		
		// populate the person list (optional)
		// with a populateListTable() method
		populateListTable();
	}
	
	public static PersonDAO getInstance(){
		if (personDao == null)
			personDao = new PersonDAO();
		
		return personDao;
	}

	@Override
	public Person create(Person t) throws IllegalArgumentException{
		Person createdPerson = null;
		if (t.getFirstName() == null)
			throw new IllegalArgumentException("Error: first name is null");
		else if ( t.getLastName() == null)
			throw new IllegalArgumentException("Error: Last name is null");
		else if (t.getUserName() == null )
			throw new IllegalArgumentException("Error: User name is null");
		else if ( t.getPassword() == null)
			throw new IllegalArgumentException("Error: Password is null");
			
		createdPerson = new Person(gen.generatePersonID(), t.getUserName(), t.getFirstName(),
				t.getLastName(), t.getPassword());
		return createdPerson;
	}

	@Override
	// The person must have an ID before you can update. Other fields contains the update values
	public Person update(Person t) {
		Person tempPerson = null;
		
		if( t.getPersonID() != 0 )
			tempPerson = findByID(t.getPersonID());
		
		//update if company is found
		if( tempPerson != null && t.getUserName() != null)
		{
			if ( findByUserName(t.getUserName()) == null )
				tempPerson.setUserName(t.getUserName());
			else
				return null;
		}
		
		if ( tempPerson != null && t.getFirstName() != null)
			tempPerson.setFirstName(t.getFirstName());
		
		if (tempPerson != null && t.getLastName() != null)
			tempPerson.setLastName(t.getLastName());
		
		if( tempPerson != null && t.getPassword() != null)
			tempPerson.setPassword(t.getPassword());
			
		return tempPerson;
	}

	@Override
	// Person must be created first before inserted
	public Person insert(Person t) {
		if (findByUserName(t.getUserName()) != null)
		{
			logger.info("Error: Username, " + t.getUserName() + " already exist in the system");
			//throw new IllegalArgumentException("Error: User name already exist in the system");
			return null;
		}
		else
		{		
			personList.add(t);
			return t;
		}
	}

	@Override
	public Person remove(Person t) {
		if( t.getUserName().charAt(0) == '~')
			throw new IllegalArgumentException( "Error: Trying to delete an already deleted person");
		t.setUserName("~" + t.getUserName());
		return t;
	}

	@Override
	public List<Person> findAll() {
		return personList;
	}

	@Override
	public Person findByID(int ID) {
		Person returnPerson = null;
		
		ListIterator<Person> listIterator = personList.listIterator();
		while(listIterator.hasNext())
		{
			returnPerson = listIterator.next();
			if(returnPerson.getPersonID() == ID)
				return returnPerson;
		}
		
		return null;
	}

	@Override
	public SecurityRole AuthenticateUser(SecurityRole user, String password) {
		SecurityRole verifiedUser = null;
		Person tempPerson = null;
		
		tempPerson = findByUserName(user.getUserName());
		if (tempPerson != null)
		{
			if (tempPerson.getPassword().equals(password))
			{
				verifiedUser = new SecurityRole(tempPerson.getUserName());
				verifiedUser.setPersonID(tempPerson.getPersonID());
				return verifiedUser;
			}
			else{
				logger.info("User: " + user.getUserName() + " Supplied invalid password");
				System.err.println("Incorrect Password");
				return null;
			}
		}
		else{
			logger.info("User: " + user.getUserName() + " Supplied non-existing username");
			System.err.println("Username not found in the system");
			return null;
		}
	}

	@Override
	public Person findByUserName(String userName) {
		Person tempPerson = null;
		
		ListIterator<Person> listIterator = personList.listIterator();
		// check to make sure person exist
		while (listIterator.hasNext())
		{
			tempPerson = listIterator.next();
			if ( tempPerson.getUserName().equalsIgnoreCase(userName) || 
					tempPerson.getUserName().equalsIgnoreCase("~" + userName))
				return tempPerson;
		}
		return null;
	}

	@Override
	public boolean removeByID(int ID) {
		Person tempPerson = null;
		
		tempPerson = findByID(ID);
		if (tempPerson != null ){
			remove(tempPerson);
			return true;
		}
		
		return false;
	}

	@Override
	public boolean removeByUserName(String userName) {
		Person tempPerson = null;
		
		tempPerson = findByUserName(userName);
		if (tempPerson != null ){
			remove(tempPerson);
			return true;
		}
		
		return false;
	}
	
	private void populateListTable(){
		
		Person person1 = null;
		for (int i = 1; i < 8; i++)
		{
			person1 = new Person("asmith" + i);
			person1.setFirstName("Alex" + i);
			person1.setLastName("Smith" + i);
			person1.setPassword(person1.getFirstName()+ person1.getLastName());
			person1 = create(person1);
			insert(person1);
		}
	}

}
