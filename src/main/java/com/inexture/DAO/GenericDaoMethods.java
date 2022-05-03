package com.inexture.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;

import com.inexture.Beans.UserBean;

public class GenericDaoMethods implements GenericDaoInterface{
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public void create(UserBean user) {
		this.hibernateTemplate.save(user);
	}
	
	@Override
	public void update(UserBean user) {
		this.hibernateTemplate.update(user);
	}
	
	@Override
	public UserBean read(int uid) {
		return this.hibernateTemplate.get(UserBean.class,uid);
	}
	
	@Override
	public void delete(int uid) {
		this.hibernateTemplate.delete(uid);
	}
}
