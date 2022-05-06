package com.inexture.DAO;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

public abstract class GenericDaoMethods<T> implements GenericDaoInterface<T>{
	
	static final Logger LOG = Logger.getLogger(GenericDaoMethods.class);
	
	private Class<T> type;
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public GenericDaoMethods() {
		LOG.debug("Generic Dao Class initializing.");
		
		Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];
        
        LOG.debug("Generic Dao Class initialized and set type of class.");
	}
	
	//public GenericDaoMethods() {}

	public GenericDaoMethods(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}


	@Override
	@Transactional
	public void create(T user) {
		LOG.debug("Inside Generic Dao create method.");
		this.hibernateTemplate.save(user);
	}
	
	@Override
	@Transactional
	public void update(T user) {
		LOG.debug("Inside Generic Dao update method.");
		this.hibernateTemplate.merge(user);
	}
	
	@Override
	public T read(Object uid) {
		LOG.debug("Inside Generic Dao read method.");
		return (T)this.hibernateTemplate.get(type,(int)uid);
	}
	
	@Override
	@Transactional
	public void delete(T user) {
		LOG.debug("Inside Generic Dao delete method.");
		this.hibernateTemplate.delete(user);
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
}
