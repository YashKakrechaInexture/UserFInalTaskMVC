package com.inexture.DAO;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.inexture.Beans.UserBean;

/**
 * User Dao Implementation class. Implements the methods related to create, update, read, delete user's data from database table.
 * @author Yash
 *
 */
@Repository
public class DaoMethods extends GenericDaoMethods<UserBean> implements DaoInterface{
	static final Logger LOG = Logger.getLogger(DaoMethods.class);
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public int getUid(String email) {
		
		LOG.debug("Inside Dao. Getting Uid from email.");
		
		String query = "select uid from UserBean where email=?0";
		
		Object queryParam = email;
		
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<Integer> uids = (List<Integer>) hibernateTemplate.find(query, queryParam);
		
		int uid = 0;
		
		if(uids.size()==1) {
			uid = uids.get(0);
		}else if(uids.size()>1){
			LOG.fatal("There are same emails found with different uids in table.");
		}
		
		return uid;
	}
	
	@Override
	public UserBean authUser(String email,String password){
		
		LOG.debug("Inside Dao. Authorizing user.");
		
		String query = "from UserBean where email=?0 and password=?1";
		
		Object[] queryParam = {email,password};
		
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<UserBean> list = (List<UserBean>) hibernateTemplate.find(query, queryParam);
		
		if(list.size()==1) {
			return list.get(0);
		}else if(list.size()==0) {
			return null;
		}else {
			LOG.fatal("There are duplicate email and password found in table.");
			return null;
		}
	}
	
	@Override
	public List<UserBean> showUserData(String type) {
		
		LOG.debug("Inside Dao. Getting Users List.");
		
		String query = "from UserBean where type=?0";
		
		Object queryParam = type;
		
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<UserBean> list = (List<UserBean>) hibernateTemplate.find(query, queryParam);
		
		return list;
	}
	
	@Override
	public boolean findUser(UserBean u) {
		
		LOG.debug("Inside Dao. Finding user for forgot password.");
		
		String query = "from UserBean where email=?0 and birthdate=?1 and que1=?2 and que2=?3 and que3=?4";
		
		Object[] queryParam = {u.getEmail(),u.getBirthdate(),u.getQue1(),u.getQue2(),u.getQue3()};
		
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<UserBean> list = (List<UserBean>) hibernateTemplate.find(query, queryParam);
		
		if(list.size()==1) {
			return true;
		}else if(list.size()==0) {
			return false;
		}else {
			LOG.fatal("There are duplicate email and password found in table.");
			return false;
		}
	}
	
}
