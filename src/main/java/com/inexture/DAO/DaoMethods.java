package com.inexture.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.inexture.Beans.AddressBean;
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
	public List<Integer> getAids(int uid){
//		List<Integer> aids = new ArrayList<>();
		
//		DetachedCriteria criteria = DetachedCriteria.forClass( AddressBean.class );
//		criteria.setProjection( Projections.property("aid"));
//		criteria.add( Restrictions.eq("user", uid));
//		
//		aids = (List<Integer>) hibernateTemplate.findByCriteria(criteria);
		LOG.debug("Inside Dao. Getting Aid from uid.");
		
		String query = "select aid from AddressBean where uid=?0";
		
		Object queryParam = uid;
		
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<Integer> aids = (List<Integer>) hibernateTemplate.find(query, queryParam);
		
//		System.out.println(aids);
		
		return aids;
	}
	
//	@Override
//	public boolean checkUser(String email) {
//		
//		LOG.debug("Inside Dao. Checking email exist or not.");
//		
//		
//		String query = "select uid from UserBean where email=?0";
//		
//		Object queryParam = email;
//		
//		@SuppressWarnings({ "unchecked", "deprecation" })
//		List<Integer> uid = (List<Integer>) hibernateTemplate.find(query, queryParam);
//		
////		System.out.println(uid);
//		
//		if(uid.size()==0) {
//			return true;
//		}else {
//			return false;
//		}
//		
//		
//		Connection conn = DaoConnectionClass.getConnection();
//		PreparedStatement st = null;
//		ResultSet rs = null;
//		int i = 0;
//		
//		try {
//			
//			st = conn.prepareStatement("select uid from users where email=?");
//			
//			st.setString(1, email);
//			
//			rs = st.executeQuery();
//			
//			LOG.info("Query executed successfully.");
//			
//			if(rs.next()) {
//				LOG.debug("User found in table.");
//				i++;
//			}else {
//				LOG.debug("No User found in table.");
//			}
//			
//			if(rs != null) {
//				rs.close();
//				LOG.info("ResultSet Closed.");
//			}
//			
//			if(i==0) {
//				return true;
//			}else {
//				return false;
//			}
//			
//		}catch(Exception e) {
//			LOG.fatal("Something went wrong! Exception : "+e);
//		}finally {
//			LOG.debug("Started closing PreparedStatement and ResultSet.");
//			try{
//				if(st != null){
//					st.close();
//					LOG.info("PreparedStatement Closed.");
//				}
//			}catch(Exception ep){
//				LOG.fatal("Something went wrong! Exception : "+ep);
//			}
//			
//		}
//		if(i==0) {
//			return true;
//		}else {
//			return false;
//		}
//	}
	
//	@Override
//	public void register(UserBean u) {
//		
//		LOG.debug("Inside Dao. Registering User.");
//		
//		Connection conn = DaoConnectionClass.getConnection();
//		PreparedStatement st = null;
//		try {
//			
//			st = conn.prepareStatement("insert into users (firstname,lastname,email,phone,password,gender,birthdate,hobby,ans1,ans2,ans3,type,image)"+
//			" values (?,?,?,?,?,?,?,?,?,?,?,?,?);");
//			
//			st.setString(1, u.getFname());
//			st.setString(2, u.getLname());
//			st.setString(3, u.getEmail());
//			st.setLong(4, u.getPhone());
//			st.setString(5, u.getPassword());
//			st.setString(6, u.getGender());
//			st.setString(7, u.getBirthdate());
//			st.setString(8, u.getHobby());
//			st.setString(9, u.getQue1());
//			st.setString(10, u.getQue2());
//			st.setString(11, u.getQue3());
//			st.setString(12, "user");
//			st.setBlob(13, u.getInputStream());
//			
//			st.executeUpdate();
//			
//			LOG.info("User stored in database.");
//			
//		}catch(Exception e) {
//			LOG.fatal("Something went wrong! Exception : "+e);
//		}finally {
//			try{
//				if(st != null){
//					st.close();
//					LOG.info("PreparedStatement Closed.");
//				}
//			}catch(Exception ep){
//				LOG.fatal("Something went wrong! Exception : "+ep);
//			}
//		}
//	}
	
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
		}else {
			LOG.error("There are same emails found with different uids in table.");
		}
		
		return uid;
//		Connection conn = DaoConnectionClass.getConnection();
//		PreparedStatement st = null;
//		ResultSet rs = null;
//		int i = 0;
//		try {
//			
//			st = conn.prepareStatement("select uid from users where email=?");
//			
//			st.setString(1, email);
//			
//			rs = st.executeQuery();
//			
//			LOG.info("Query executed successfully.");
//			
//			if(rs.next()) {
//				LOG.debug("Uid found.");
//				i = rs.getInt(1);
//			}else {
//				LOG.fatal("Uid not found for given email.");
//				i = 0;
//			}
//			
//			if(rs != null) {
//				rs.close();
//				LOG.info("ResultSet Closed.");
//			}
//			
//			return i;
//			
//		}catch(Exception e) {
//			LOG.fatal("Something went wrong! Exception : "+e);
//		}finally {
//			try{
//				if(st != null){
//					st.close();
//					LOG.info("PreparedStatement Closed.");
//				}
//			}catch(Exception ep){
//				LOG.fatal("Something went wrong! Exception : "+ep);
//			}
//		}
		
//		return 0;
	}
	
	@Override
	public UserBean authUser(String email,String password){
		
		LOG.debug("Inside Dao. Authorizing user.");
		
		Connection conn = DaoConnectionClass.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			
			st = conn.prepareStatement("select * from users where email=? and password=?;");
			
			st.setString(1, email );
			st.setString(2, password );
			
			rs = st.executeQuery();
			
			LOG.info("Query executed successfully.");
			
			if(rs.next()) {
				UserBean u = new UserBean(email);
				u.setUid(rs.getInt("uid"));
				u.setFname(rs.getString("firstname"));
				u.setLname(rs.getString("lastname"));
				u.setEmail(rs.getString("email"));
				u.setPhone(rs.getInt("phone"));
				u.setGender(rs.getString("gender"));
				u.setBirthdate(rs.getString("birthdate"));
				u.setHobby(rs.getString("hobby"));
				u.setQue1(rs.getString("ans1"));
				u.setQue2(rs.getString("ans2"));
				u.setQue3(rs.getString("ans3"));
				u.setType(rs.getString("type"));
				u.setInputStream(rs.getBlob("image").getBinaryStream());
				
				
				
				LOG.debug("User data stored in bean.");
				
				if(rs != null) {
					rs.close();
					LOG.info("ResultSet Closed.");
				}
				
				return u;
			}else {
				
				LOG.debug("No user found in database, returning null.");
				
				if(rs != null) {
					rs.close();
					LOG.info("ResultSet Closed.");
				}
				
				return null;
			}
			
		}catch(Exception e) {
			LOG.fatal("Something went wrong! Exception : "+e);
		}finally {
			try{
				if(st != null){
					st.close();
					LOG.info("PreparedStatement Closed.");
				}
			}catch(Exception ep){
				LOG.fatal("Something went wrong! Exception : "+ep);
			}
		}
		return null;
	}
	
	@Override
	public List<UserBean> showUserData(String type) {
		
		LOG.debug("Inside Dao. Getting Users List.");
		
		String query = "from UserBean where type=?0";
		
		Object queryParam = type;
		
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<UserBean> list = (List<UserBean>) hibernateTemplate.find(query, queryParam);
		
		return list;
//		Connection conn = DaoConnectionClass.getConnection();
//		PreparedStatement st = null;
//		ResultSet rs = null;
//		try {
//			
//			st = conn.prepareStatement("select uid,firstname,lastname,email,phone,gender,birthdate,hobby from users where type=?;");
//			
//			st.setString(1, type);
//
//			rs = st.executeQuery();
//			
//			LOG.info("Query executed successfully.");
//			
//			while(rs.next()) {
//				list.add( new UserBean(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getString(6),rs.getString(7),rs.getString(8)) );
//			}
//			
//			LOG.debug("User data stored in list.");
//			
//			if(rs != null) {
//				rs.close();
//				LOG.info("ResultSet Closed.");
//			}
//			
//		}catch(Exception e) {
//			LOG.fatal("Something went wrong! Exception : "+e);
//		}finally {
//			try{
//				if(st != null){
//					st.close();
//					LOG.info("PreparedStatement Closed.");
//				}
//			}catch(Exception ep){
//				LOG.fatal("Something went wrong! Exception : "+ep);
//			}
//		}
	}
	
//	@Override
//	public void getUserInfo(UserBean u) {
//		
//		LOG.debug("Inside Dao. Getting User Detail from email.");
//		
//		Connection conn = DaoConnectionClass.getConnection();
//		PreparedStatement st = null;
//		ResultSet rs = null;
//		
//		try {
//			
//			st = conn.prepareStatement("select uid,firstname,lastname,phone,gender,birthdate,hobby,ans1,ans2,ans3,image from users where email=?;");
//			
//			st.setString(1, u.getEmail());
//
//			rs = st.executeQuery();
//			
//			LOG.info("Query executed successfully.");
//			
//			if(rs.next()) {
//				u.setUid(rs.getInt("uid"));
//				u.setFname(rs.getString("firstname"));
//				u.setLname(rs.getString("lastname"));
//				u.setPhone( Long.parseLong(rs.getString("phone")) );
//				u.setGender(rs.getString("gender"));
//				u.setBirthdate(rs.getString("birthdate"));
//				u.setHobby(rs.getString("hobby"));
//				u.setQue1(rs.getString("ans1"));
//				u.setQue2(rs.getString("ans2"));
//				u.setQue3(rs.getString("ans3"));
//				u.setInputStream(rs.getBlob("image").getBinaryStream());
//				
//				LOG.debug("User data stored in bean.");
//				
//			}
//
//			if(rs != null) {
//				rs.close();
//				LOG.info("ResultSet Closed.");
//			}
//			
//		}catch(Exception e) {
//			LOG.fatal("Something went wrong! Exception : "+e);
//		}finally {
//			try{
//				if(st != null){
//					st.close();
//					LOG.info("PreparedStatement Closed.");
//				}
//			}catch(Exception ep){
//				LOG.fatal("Something went wrong! Exception : "+ep);
//			}
//		}
//	}
//	
//	@Override
//	public void updateImage(UserBean u) {
//		
//		LOG.debug("Inside Dao. Updating Image.");
//		
//		Connection conn = DaoConnectionClass.getConnection();
//		PreparedStatement st = null;
//		
//		try {
//			
//			st = conn.prepareStatement("update users set image=? where email=?");
//			
//			st.setBlob(1, u.getInputStream());
//			st.setString(2, u.getEmail());
//			
//			st.executeUpdate();
//			
//			LOG.info("Query executed successfully.");
//			
//		}catch(Exception e) {
//			LOG.fatal("Something went wrong! Exception : "+e);
//		}finally {
//			try{
//				if(st != null){
//					st.close();
//					LOG.info("PreparedStatement Closed.");
//				}
//			}catch(Exception ep){
//				LOG.fatal("Something went wrong! Exception : "+ep);
//			}
//		}
//	}
//	
//	@Override
//	public void updateUserDetail(UserBean u) {
//		
//		LOG.debug("Inside Dao. Updating user details.");
//		
//		Connection conn = DaoConnectionClass.getConnection();
//		PreparedStatement st = null;
//
//		try {
//
//			st = conn.prepareStatement("update users set firstname=?,lastname=?,email=?,phone=?,gender=?,birthdate=?,hobby=?,ans1=?,ans2=?,ans3=? where email=?");
//
//			st.setString(1, u.getFname());
//			st.setString(2, u.getLname());
//			st.setString(3, u.getEmail());
//			st.setLong(4, u.getPhone());
//			st.setString(5, u.getGender());
//			st.setString(6, u.getBirthdate());
//			st.setString(7, u.getHobby());
//			st.setString(8, u.getQue1());
//			st.setString(9, u.getQue2());
//			st.setString(10, u.getQue3());
//			st.setString(11, u.getEmail());
//
//			st.executeUpdate();
//
//			LOG.info("Query executed successfully.");
//			
//		}catch(Exception e) {
//			LOG.fatal("Something went wrong! Exception : "+e);
//		}finally {
//			try{
//				if(st != null){
//					st.close();
//					LOG.info("PreparedStatement Closed.");
//				}
//			}catch(Exception ep){
//				LOG.fatal("Something went wrong! Exception : "+ep);
//			}
//		}
//	}
	
//	@Override
//	public void deleteUser(int uid) {
//		
//		LOG.debug("Inside Dao. Deleting user.");
//		
//		Connection conn = DaoConnectionClass.getConnection();
//		PreparedStatement st = null;
//
//		try {
//
//			st = conn.prepareStatement("delete from users where uid=?");
//
//			st.setInt(1, uid);
//
//			st.executeUpdate();
//
//			LOG.info("Query executed successfully.");
//			
//		}catch(Exception e) {
//			LOG.fatal("Something went wrong! Exception : "+e);
//		}finally {
//			try{
//				if(st != null){
//					st.close();
//					LOG.info("PreparedStatement Closed.");
//				}
//			}catch(Exception ep){
//				LOG.fatal("Something went wrong! Exception : "+ep);
//			}
//		}
//	}
	
	@Override
	public boolean findUser(UserBean u) {
		
		LOG.debug("Inside Dao. Finding user for forgot password.");
		
		Connection conn = DaoConnectionClass.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		int i = 0;
		try {
			
			st = conn.prepareStatement("select uid from users where email=? and birthdate=? and ans1=? and ans2=? and ans3=?");
			
			st.setString(1, u.getEmail());
			st.setString(2, u.getBirthdate());
			st.setString(3, u.getQue1());
			st.setString(4, u.getQue2());
			st.setString(5, u.getQue3());
			
			rs = st.executeQuery();
			
			LOG.info("Query executed successfully.");
			
			if(rs.next()) {
				i = 0;
			}else {
				i++;
			}

			if(rs != null) {
				rs.close();
				LOG.info("ResultSet Closed.");
			}
			
			if(i==0) {
				return true;
			}else {
				return false;
			}
			
		}catch(Exception e) {
			LOG.fatal("Something went wrong! Exception : "+e);
		}finally {
			try{
				if(st != null){
					st.close();
					LOG.info("PreparedStatement Closed.");
				}
			}catch(Exception ep){
				LOG.fatal("Something went wrong! Exception : "+ep);
			}
		}
		
		if(i==0) {
			return true;
		}else {
			return false;
		}
		
	}
	
	@Override
	public void changePassword(String email,String password) {
		
		LOG.debug("Inside Dao. Changing password.");
		
		Connection conn = DaoConnectionClass.getConnection();
		PreparedStatement st = null;

		try {

			st = conn.prepareStatement("update users set password=? where email=?");
			
			st.setString(1, password);
			st.setString(2, email);
			
			st.executeUpdate();

			LOG.info("Query executed successfully.");
			
		}catch(Exception e) {
			LOG.fatal("Something went wrong! Exception : "+e);
		}finally {
			try{
				if(st != null){
					st.close();
					LOG.info("PreparedStatement Closed.");
				}
			}catch(Exception ep){
				LOG.fatal("Something went wrong! Exception : "+ep);
			}
		}
	}
}
