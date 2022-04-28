package com.inexture.Utilities;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.inexture.Beans.AddressBean;
import com.inexture.Beans.UserBean;

/**
 * This is validation class. It contains static method to validate all user input is empty or exceeds 255 length.
 * @author Yash
 *
 */
public class Validation {
	
	/**
	 * Logger
	 */
	static final Logger LOG = Logger.getLogger(Validation.class);
	
	/**
	 * This method checks validation of firstname, lastname, email, phone, gender, birthdate, hobby, que1, que2, que3, address list
	 * of user bean object. If it is null or length exceeds 255.
	 * @param u - User bean object
	 * @return false - If validation failed.<br>
	 * 		   true - If all validation is passed.
	 */
	public static boolean validate(UserBean u) {
		
		String fname = u.getFname();
		String lname = u.getLname();
		String email = u.getEmail();
		long phone = u.getPhone();
		String gender = u.getGender();
		String birthdate = u.getBirthdate();
		String hobby = u.getHobby();
		String que1 = u.getQue1();
		String que2 = u.getQue2();
		String que3 = u.getQue3();
		ArrayList<AddressBean> address = u.getAddress();
		
		LOG.debug("Inside Validation class.");
		
		if(fname.equals("") || fname.length()>255) {
			LOG.debug("Validation failed for fname.");
			return false;
		}else if(lname.equals("") || lname.length()>255) {
			LOG.debug("Validation failed for lname.");
			return false;
		}else if(email.equals("") || email.length()>255) {
			LOG.debug("Validation failed for email.");
			return false;
		}else if(phone == 0) {
			LOG.debug("Validation failed for phone.");
			return false;
		}else if(gender.equals("") || gender.length()>255) {
			LOG.debug("Validation failed for gender.");
			return false;
		}else if(birthdate.equals("") || birthdate.length()>255) {
			LOG.debug("Validation failed for birthdate.");
			return false;
		}else if(hobby.equals("") || hobby.length()>255) {
			LOG.debug("Validation failed for hobby.");
			return false;
		}else if(que1.equals("") || que1.length()>255) {
			LOG.debug("Validation failed for que1.");
			return false;
		}else if(que2.equals("") || que2.length()>255) {
			LOG.debug("Validation failed for que2.");
			return false;
		}else if(que3.equals("") || que3.length()>255) {
			LOG.debug("Validation failed for que3.");
			return false;
		}else if(address.size()<1) {
			LOG.debug("Validation failed for address size.");
			return false;
		}
		else {
			for(AddressBean a : address) {
				
				String home = a.getHome();
				String city = a.getCity();
				String state = a.getState();
				String country = a.getCountry();
				String pincode = a.getPincode();
				
				if(home.equals("") || home.length()>255) {
					LOG.debug("Validation failed for home.");
					return false;
				}else if(city.equals("") || city.length()>255) {
					LOG.debug("Validation failed for city.");
					return false;
				}else if(state.equals("") || state.length()>255) {
					LOG.debug("Validation failed for state.");
					return false;
				}else if(country.equals("") || country.length()>255) {
					LOG.debug("Validation failed for country.");
					return false;
				}else if(pincode.equals("") || pincode.length()>255) {
					LOG.debug("Validation failed for pincode.");
					return false;
				}
			}
			
			LOG.debug("All validation true.");
			return true;
			
		}
	}
}
