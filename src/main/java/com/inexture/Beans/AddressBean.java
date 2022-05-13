package com.inexture.Beans;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
* 
* Address bean, which stores the 1 address of User
* @author Yash
*/
@Entity
@Table(name = "addresses")
@Component
@Scope("prototype")
public class AddressBean implements Serializable{
	
	/**
	 * Logger
	 */
	static final Logger LOG = Logger.getLogger(AddressBean.class);
	
	/**
	 * Serializable id
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Stores address id from database table
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int aid;
	
	/**
	 * Stores home line of address
	 */
	@NotEmpty(message = "Home is required.")
	private String home;
	
	/**
	 * Stores city name of address
	 */
	@NotEmpty(message = "City is required.")
	private String city;
	
	/**
	 * Stores state line of address
	 */
	@NotEmpty(message = "State is required.")
	private String state;
	
	/**
	 * Stores country line of address
	 */
	@NotEmpty(message = "Country is required.")
	private String country;
	
	/**
	 * Stores pincode of address. String because some countries have Alpha-numeric pincode. exa, Canada, Argentina, etc.
	 */
	@NotEmpty(message = "Pincode is required.")
	private String pincode;
	
	/**
	 * Stores uid in table as ManyToMany
	 */
	@ManyToOne
	@JoinColumn(name = "uid")
	private UserBean user;
	
	/**
	 * Default constructor of address bean
	 */
	public AddressBean() {}
	
	/**
	 * Constructor of address bean
	 * @param aid - Stores address id from database table
	 * @param home - Stores home line of address
	 * @param city - Stores city name of address
	 * @param state - Stores state line of address
	 * @param country - Stores country line of address
	 * @param pincode - Stores pincode of address
	 */
	public AddressBean(int aid,String home, String city, String state, String country, String pincode) {
		this.aid = aid;
		this.home = home;
		this.city = city;
		this.state = state;
		this.country = country;
		this.pincode = pincode;
	}
	
	/**
	 * Counstructor of address bean
	 * @param home - Stores home line of address
	 * @param city - Stores city name of address
	 * @param state - Stores state line of address
	 * @param country - Stores country line of address
	 * @param pincode - Stores pincode of address
	 */
	public AddressBean(String home, String city, String state, String country, String pincode) {
		this.home = home;
		this.city = city;
		this.state = state;
		this.country = country;
		this.pincode = pincode;
	}
	
	/**
	 * Setter method of Aid in address bean
	 * @param aid - Stores address id from database table
	 */
	public void setAid(int aid) {
		this.aid = aid;
	}
	
	/**
	 * Setter method of home in address bean
	 * @param home - Stores home line of address
	 */
	public void setHome(String home) {
		this.home = home;
	}
	
	/**
	 * Setter method of city in address bean
	 * @param city - Stores city name of address
	 */
	public void setCity(String city) {
		this.city = city;
	}
	
	/**
	 * Setter method of state in address bean
	 * @param state - Stores state line of address
	 */
	public void setState(String state) {
		this.state = state;
	}
	
	
	/**
	 * Setter method of country in address bean
	 * @param country - Stores country line of address
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	
	
	/**
	 * Setter method of pincode in address bean
	 * @param pincode - Stores pincode of address
	 */
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	
	/**
	 * Getter method of aid in address bean
	 * @return aid - Stores address id from database table
	 */
	public int getAid() {
		return this.aid;
	}
	
	/**
	 * Getter method of home in address bean
	 * @return home - Stores home line of address
	 */
	public String getHome() {
		return this.home;
	}
	
	/**
	 * Getter method of city in address bean
	 * @return city - Stores city name of address
	 */
	public String getCity() {
		return this.city;
	}
	
	/**
	 * Getter method of state in address bean
	 * @return state - Stores state line of address
	 */
	public String getState() {
		return this.state;
	}
	
	/**
	 * Getter method of country in address bean
	 * @return country - Stores country line of address
	 */
	public String getCountry() {
		return this.country;
	}
	
	/**
	 * Getter method of pincode in address bean
	 * @return pincode - Stores pincode of address
	 */
	public String getPincode() {
		return this.pincode;
	}

	/**
	 * Getter method of UserBean user
	 * @return UserBean - user bean object
	 */
	public UserBean getUser() {
		return this.user;
	}

	/**
	 * Setter method of UserBean user
	 * @param user - User bean object
	 */
	public void setUser(UserBean user) {
		this.user = user;
	}
	
}
