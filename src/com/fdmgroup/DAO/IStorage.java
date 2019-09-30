package com.fdmgroup.DAO;

import java.util.List;

import com.fdmgroup.model.IStorable;

public interface IStorage<T extends IStorable> {
	
	T create(T t);
	T update(T t);
	T insert(T t);
	T remove(T t);
	List<T> findAll();
	T findByID(int ID);
}
