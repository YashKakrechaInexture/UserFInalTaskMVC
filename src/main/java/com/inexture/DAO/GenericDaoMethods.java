package com.inexture.DAO;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

public abstract class GenericDaoMethods<T> implements GenericDaoInterface<T>{
	
	private Class<T> type;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public GenericDaoMethods() {
		Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];
	}
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	//public GenericDaoMethods() {}

	public GenericDaoMethods(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}


	@Override
	@Transactional
	public void create(T user) {
		this.hibernateTemplate.save(user);
	}
	
	@Override
	@Transactional
	public void update(T user) {
		this.hibernateTemplate.update(user);
	}
	
	@Override
	public T read(int uid) {
		return (T)this.hibernateTemplate.get(type,uid);
	}
	
	@Override
	@Transactional
	public void delete(int uid) {
		this.hibernateTemplate.delete(uid);
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
}
