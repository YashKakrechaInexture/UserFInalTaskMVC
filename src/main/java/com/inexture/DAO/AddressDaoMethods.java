package com.inexture.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.inexture.Beans.AddressBean;
import com.inexture.Beans.UserBean;

/**
 * Address Dao Implementation class. Implements the methods related to create, update, read, delete address's data from database table.
 * @author Yash
 *
 */
public class AddressDaoMethods implements AddressDaoInterface{
	/**
	 * Logger
	 */
	static final Logger LOG = Logger.getLogger(DaoMethods.class);
	
	@Override
	public void addAddress(AddressBean address,int uid) {
		
		LOG.debug("Inside Dao. Storing Address in database.");
		
		final Connection conn = DaoConnectionClass.getConnection();
		PreparedStatement st = null;
		try {
			
			st = conn.prepareStatement("insert into addresses (uid,home,city,state,country,pincode) values (?,?,?,?,?,?);");
			
			st.setInt(1, uid);
			st.setString(2, address.getHome());
			st.setString(3, address.getCity());
			st.setString(4, address.getState());
			st.setString(5, address.getCountry());
			st.setString(6, address.getPincode());
			
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
	
	@Override
	public void getAddressInfo(UserBean user) {
		
		LOG.debug("Inside Dao. Getting Address list using user id.");
		
		Connection conn = DaoConnectionClass.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			st = conn.prepareStatement("select aid,home,city,state,country,pincode from addresses where uid=?;");
			
			st.setInt(1, user.getUid());

			rs = st.executeQuery();
			
			LOG.info("Query executed successfully.");
			
			final ArrayList<AddressBean> list = new ArrayList<>();
			
			while(rs.next()) {
				list.add( new AddressBean( rs.getInt("aid"),rs.getString("home"),rs.getString("city"),rs.getString("state"),rs.getString("country"),rs.getString("pincode") ) );
			}
			
			LOG.debug("Addresses stored in list.");
			
			user.setAddress(list);
			
			if(rs != null) {
				rs.close();
				LOG.info("ResultSet Closed.");
			}
			
		}catch(Exception e) {
			LOG.fatal("Something went wrong! Exception : {}",e);
		}finally {
			try {
				if(st != null){
					st.close();
					LOG.info("PreparedStatement Closed.");
				}
			}catch(Exception ep){
				LOG.fatal("Something went wrong! Exception : {}",ep);
			}
		}
	}
	
	@Override
	public List<Integer> getAid(final int uid) {
		
		LOG.debug("Inside Dao. Getting address id list using user's id.");
		
		final Connection conn = DaoConnectionClass.getConnection();
		PreparedStatement statement = null;
		ResultSet resultset = null;
		
		final List<Integer> list = new ArrayList<>();
		
		try {
			
			statement = conn.prepareStatement("select aid from addresses where uid=?;");
			
			statement.setInt(1, uid);

			resultset = statement.executeQuery();
			
			LOG.info("Query executed successfully.");
			
			while(resultset.next()) {
				list.add( resultset.getInt("aid") );
			}
			
			LOG.debug("Aid stored in list.");
			
			if(resultset != null) {
				resultset.close();
				LOG.info("ResultSet Closed.");
			}
			
			return list;
			
		}catch(Exception e) {
			LOG.fatal("Something went wrong! Exception : "+e);
		}finally {
			try{
				if(statement != null){
					statement.close();
					LOG.info("PreparedStatement Closed.");
				}
			}catch(Exception ep){
				LOG.fatal("Something went wrong! Exception : "+ep);
			}
		}
		return list;
	}
	
	@Override
	public void updateAddress(AddressBean address,int aid) {
		
		LOG.debug("Inside Dao. Updating Address.");
		
		Connection conn = DaoConnectionClass.getConnection();
		PreparedStatement st = null;
		
		try {
			
			st = conn.prepareStatement("update addresses set home=?,city=?,state=?,country=?,pincode=? where aid=?;");
			
			st.setString(1, address.getHome());
			st.setString(2, address.getCity());
			st.setString(3, address.getState());
			st.setString(4, address.getCountry());
			st.setString(5, address.getPincode());
			st.setInt(6, aid);
			
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
	
	@Override
	public void deleteAddress(int uid,int length) {
		
		LOG.debug("Inside Dao. Deleting Address.");
		
		Connection conn = DaoConnectionClass.getConnection();
		PreparedStatement st = null;

		try {

			st = conn.prepareStatement("delete from addresses where uid=? order by aid desc limit ?");

			st.setInt(1, uid);
			st.setInt(2, length);

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
