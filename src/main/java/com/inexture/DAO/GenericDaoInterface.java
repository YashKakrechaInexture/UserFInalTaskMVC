package com.inexture.DAO;

import com.inexture.Beans.UserBean;

/**
 * Generic Dao class. Basic CRUD (Create, Read, Update, Delete) methods of user.
 * @author Yash
 *
 */
public interface GenericDaoInterface {
	
	/**
	 * This method creates the user in database table.
	 * @param user - User bean object
	 */
	public void create(UserBean user);
	
	/**
	 * This method updates the user in database table.
	 * @param user - User bean object
	 */
	public void update(UserBean user);
	
	/**
	 * This method finds user based on given email, birthdate and security answers in forgot password.
	 * @param uid - user's id from table
	 * @return UserBean object - If user found in database table.<br>
	 * 		   null - If no user found with given information in database table.
	 */
	public UserBean read(int uid);
	
	/**
	 * It deletes the user from database table, based on given user id
	 * @param uid - user id in table
	 */
	public void delete(int uid);
}
