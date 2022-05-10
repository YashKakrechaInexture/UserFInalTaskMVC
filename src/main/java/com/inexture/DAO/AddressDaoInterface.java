package com.inexture.DAO;

import java.util.List;

import com.inexture.Beans.AddressBean;

/**
 * Address Dao Interface. Stores the methods list related to create, update, read, delete address's data from database table.
 * @author Yash
 *
 */
public interface AddressDaoInterface extends GenericDaoInterface<AddressBean>{
	
	/**
	 * It returns address id's array list attached with the given user id from database table.
	 * @param uid - user id in table
	 * @return List<Integer> - List of aid from address table
	 */
	public List<Integer> getAids(final int uid);
	
}
