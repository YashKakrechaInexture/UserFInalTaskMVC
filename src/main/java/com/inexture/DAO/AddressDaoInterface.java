package com.inexture.DAO;

import java.util.List;

import com.inexture.Beans.AddressBean;
import com.inexture.Beans.UserBean;

/**
 * Address Dao Interface. Stores the methods list related to create, update, read, delete address's data from database table.
 * @author Yash
 *
 */
public interface AddressDaoInterface {
	
	/**
	 * It adds address in database table
	 * @param a - address bean object
	 * @param uid - user id from user database table
	 */
	public void addAddress(AddressBean a,int uid);
	
	/**
	 * It accepts empty userbean, with uid variable filled. and it fills the address list attached with that user id.
	 * @param u - user bean object
	 */
	public void getAddressInfo(UserBean u);
	
	/**
	 * It returns address id's array list attached with the given user id from database table.
	 * @param uid - user id in table
	 * @return AddressList - Address bean object's arraylist
	 */
	public List<Integer> getAid(int uid);

	/**
	 * It updates address row on given address id in database table.
	 * @param a - address bean object
	 * @param aid - address id in table
	 */
	public void updateAddress(AddressBean a,int aid);
	
	/**
	 * It deletes the address from database table based on given address id
	 * @param aid - address id in table
	 */
	public void deleteAddress(int aid);
	
}
