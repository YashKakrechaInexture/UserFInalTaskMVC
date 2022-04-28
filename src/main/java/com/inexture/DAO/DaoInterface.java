package com.inexture.DAO;

import java.util.List;

import com.inexture.Beans.UserBean;


/**
 * User Dao Interface. Stores the methods list related to create, update, read, delete user's data from database table.
 * @author Yash
 *
 */
public interface DaoInterface {
		
	/**
	 * Checks if email is already present in database or not.
	 * @param email - Stores email of user
	 * @return true - If no user found in mysql database table.<br>
	 * 		   false - If user found in mysql database table.
	 */
	public boolean checkUser(String email);
	
	/**
	 * This method registers all the details accept address in the database table
	 * @param u - User bean object
	 */
	public void register(UserBean u);
	
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
	 * @param list - empty list
	 * @param type - type of user. Exa. admin, user.
	 */
	public void showUserData(List<UserBean> list,String type);
	
	/**
	 * It accepts empty userbean, with one email variable filled. and it fills the data from table based on given email.
	 * @param u - user bean object
	 */
	public void getUserInfo(UserBean u);
	
	/**
	 * It accepts userbean with image inputstream and email filled. and this method fills image input stream to the given email in table.
	 * @param u - user bean object
	 */
	public void updateImage(UserBean u);
	
	/**
	 * This method updates all the user data accept image and address.
	 * @param u - user bean object
	 */
	public void updateUserDetail(UserBean u);
	
	/**
	 * It deletes the user from database table, based on given user id
	 * @param uid - user id in table
	 */
	public void deleteUser(int uid);
	
	/**
	 * This method finds user based on given email, birthdate and security answers in forgot password.
	 * @param u - user bean objects
	 * @return false - If user found in database table.<br>
	 * 		   true - If no user found with given information in database table.
	 */
	public boolean findUser(UserBean u);
	
	/**
	 * This method changes password to the provided email address in database table.
	 * @param email - email of the user
	 * @param Password - new password of the user
	 */
	public void changePassword(String email,String password);
}
