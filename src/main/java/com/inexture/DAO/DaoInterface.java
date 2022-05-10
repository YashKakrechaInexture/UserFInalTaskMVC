package com.inexture.DAO;

import java.util.List;

import com.inexture.Beans.UserBean;


/**
 * User Dao Interface. Stores the methods list related to create, update, read, delete user's data from database table.
 * @author Yash
 *
 */
public interface DaoInterface extends GenericDaoInterface<UserBean>{
	
	/**
	 * It returns user id from the table related to the email given, if no user found then returns 0.
	 * @param email - Stores email of user
	 * @return uid - User id from user database table<br>
	 * 		   0 - If no user is found with the given email
	 */
	public int getUid(String email);
	
	/**
	 * This method checks if user is present in database table or not based on given email, password. 
	 * If User is present then it returns user bean object filled with all the details of user.
	 * Else it returns null.
	 * @param email - email of user
	 * @param password - encrypted password of user
	 * @return u - User bean object
	 * 		   null - if no user found in table
	 */
	public UserBean authUser(String email,String password);
	
	/**
	 * It accepts empty arraylist and type of user and fills the arraylist with user bean objects from database table based on given type of user. 
	 * @param type - type of user. Exa. admin, user.
	 * @return list - list of users
	 */
	public List<UserBean> showUserData(String type);
	
	/**
	 * This method finds user based on given email, birthdate and security answers in forgot password.
	 * @param u - user bean objects
	 * @return false - If user found in database table.<br>
	 * 		   true - If no user found with given information in database table.
	 */
	public boolean findUser(UserBean u);
	
}
