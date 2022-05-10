package com.inexture.DAO;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.inexture.Beans.AddressBean;

/**
 * Address Dao Implementation class. Implements the methods related to create, update, read, delete address's data from database table.
 * @author Yash
 *
 */
@Repository
public class AddressDaoMethods extends GenericDaoMethods<AddressBean> implements AddressDaoInterface{
	/**
	 * Logger
	 */
	static final Logger LOG = Logger.getLogger(AddressDaoMethods.class);
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public List<Integer> getAids(final int uid) {
		
		LOG.debug("Inside Dao. Getting address id list using user's id.");
		
		String query = "select aid from AddressBean where uid=?0";
		
		Object queryParam = uid;
		
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<Integer> aids = (List<Integer>) hibernateTemplate.find(query, queryParam);
		
		System.out.println(aids);
		
		return aids;
	}
	
}
