package com.inexture.Services;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.inexture.Beans.AddressBean;
import com.inexture.Beans.UserBean;
import com.inexture.DAO.AddressDaoInterface;
import com.inexture.DAO.AddressDaoMethods;
import com.inexture.DAO.DaoInterface;
import com.inexture.DAO.DaoMethods;
import com.inexture.DAO.GenericDaoMethods;
import com.inexture.Utilities.ShaEncryption;

/**
 * User service implementation class. It implements all the user related services and calls the dao for CRUD user/address.
 * @author Yash
 *
 */
@Service
public class UserService implements UserInterface{
	static final Logger LOG = Logger.getLogger(UserService.class);
	
//	@Autowired
//	GenericDaoMethods<UserBean> gdm;
//	
//	public GenericDaoMethods<UserBean> getGdm() {
//		return gdm;
//	}
//
//	public void setGdm(GenericDaoMethods<UserBean> gdm) {
//		this.gdm = gdm;
//	}
	
	@Autowired
	private DaoInterface dm;
	
	@Autowired
	private AddressDaoInterface am;

	@Autowired
	UserBean user;
	
	@Override
	public void getaid(String email) {
//		dm.getAids(115);
//		dm.checkUser(email);
	}
	
	@Override
	public List<UserBean> showUsers(String type) {
		
		LOG.debug("Inside ShowUser service.");
		List<UserBean> list = dm.showUserData(type);
		
//		DaoInterface dm = new DaoMethods();
		
		
		LOG.debug("List is returning to Servlet.");
		
		return list;
	}
	
	@Override
	public void deleteUser(int uid) {
		
		LOG.debug("Inside Delete User Service");
		
//		DaoMethods dm = new DaoMethods();
//		dm.deleteUser(uid);
		
		user.setUid(uid);
		dm.delete(user);
	}
	
	@Override
	public void updateUser(UserBean u,String fileName) {
		
		LOG.debug("Inside Update Service.");
		
		List<AddressBean> newAddress = u.getAddress();
		
		List<Integer> aids = dm.getAids(u.getUid());
		
		for(AddressBean a:newAddress) {
			if(aids.size()>0) {
				a.setAid(aids.get(0));
				aids.remove(0);
			}
			a.setUser(u);
		}
		
		for(int i:aids) {
			am.deleteAddress(i);
		}
		
		u.setAddress(newAddress);
		
		u.setType("user");
		
		try {
			u.setImage(IOUtils.toByteArray(u.getInputStream()));
		} catch (IOException e) {
			LOG.fatal("Something went wrong! Exception : "+e);
		}
		
		dm.update(u);
//		DaoInterface dm = new DaoMethods();
//		dm.updateUserDetail(u);
//		
//		LOG.debug("User updated.");
//		
//		if(fileName!=null && !fileName.equals("")) {
//			LOG.debug("Updating image.");
//			dm.updateImage(u);
//		}	
//		
//		AddressDaoInterface am = new AddressDaoMethods();
//		
//		List<Integer> aid = am.getAid(u.getUid());
//		
//		List<AddressBean> address = u.getAddress();
//		
//		if(aid.size()<=address.size()) {
//			for(Integer i : aid) {
//				LOG.debug("Updating addresses.");
//				am.updateAddress(address.get(0), i);
//				address.remove(0);
//			}
//			for(AddressBean a : address) {
//				LOG.debug("Adding new addresses.");
//				am.addAddress(a, u.getUid());
//			}
//		}else {
//			for(AddressBean a : address) {
//				LOG.debug("Updating addresses.");
//				am.updateAddress(a, aid.get(0));
//				aid.remove(0);
//			}
//			
//			LOG.debug("Deleting extra addresses.");
//			am.deleteAddress(u.getUid(), aid.size());
//			
//		}
			
	}
	
	@Override
	public void registerUser(UserBean u) {
		
		LOG.debug("Inside Register Service.");
		
//		DaoInterface dm = new DaoMethods();
//		AddressDaoInterface am = new AddressDaoMethods();
		//checking if user already exist
		if(dm.getUid(u.getEmail())==0) {
			
			LOG.debug("No email found, registering to database.");
			
			//encrypting password
			ShaEncryption sha = new ShaEncryption();
			u.setPassword( sha.passwordEncryption( u.getPassword() ) );
			
			LOG.info("Password Encrypted.");
			
			//adding user details in table
//			dm.register(u);
			
			List<AddressBean> newAddress = u.getAddress();
			
			for(AddressBean a:newAddress) {
				a.setUser(u);
			}
			
			u.setAddress(newAddress);
			
			u.setType("user");
			
			try {
				u.setImage(IOUtils.toByteArray(u.getInputStream()));
			} catch (IOException e) {
				LOG.fatal("Something went wrong! Exception : "+e);
			}
			
			dm.create(u);
			
			LOG.debug("User Registered.");
			
			//getting uid
//			int uid = dm.getUid(u.getEmail());
//			
//			//adding address in table
//			if(uid>0) {
//
//				LOG.debug("Adding all address in database.");
//				
//				//adding all addresses in table
//				for(AddressBean a : u.getAddress()) {
//					am.addAddress(a,uid);
//				}
//			}
		}
	}
	
	@Override
	public void resetPass(String email,String password) {
		
		LOG.debug("Inside Reset Password Service.");
		
		//encrypting password
		ShaEncryption sha = new ShaEncryption();
		String encryptedPassword = sha.passwordEncryption(password);
		
		LOG.info("Password Incrypted.");
		
//		DaoInterface dm = new DaoMethods();
		dm.changePassword(email, encryptedPassword);
		
		LOG.info("Password changed");
	}
	
	@Override
	public UserBean checkUser(String email,String password) {
		
		//encrypting password
		ShaEncryption sha = new ShaEncryption();
		String encryptedPassword = sha.passwordEncryption(password);
		
		LOG.info("Password Encrypted.");
		
		//checking if user/admin exist
//		DaoInterface dm = new DaoMethods();
		UserBean u = dm.authUser(email,encryptedPassword);
		System.out.println(u.getUid());
		UserBean user = dm.read(u.getUid());
		
		user.setInputStream(new ByteArrayInputStream(user.getImage()));
		
		LOG.debug("Checked User in Dao.");
		
		return user;
		
	}
	
	@Override
	public UserBean editProfile(String email) {
		
		LOG.debug("Inside Edit profile service");
		
//		DaoInterface dm = new DaoMethods();
//		dm.getUserInfo(u);
		int uid = dm.getUid(email);
//		AddressDaoInterface am = new AddressDaoMethods();
//		am.getAddressInfo(u);
		user = dm.read(uid);
		
		byte[] initarray = {0,1,2};
		
		if(user.getImage()!=null) {
			initarray = user.getImage();
		}
		
		user.setInputStream(new ByteArrayInputStream(initarray));
		
		user.setBase64Image( this.convertToBase64Image(user.getInputStream()) );
		
		return user;
	}
	
	@Override
	public boolean checkEmail(String email) {
		LOG.debug("Inside AuthEmail Service.");
		
//		DaoInterface dm = new DaoMethods();
		
		return dm.getUid(email)==0;
		
	}
	
	@Override
	public boolean findUser(UserBean u) {
		LOG.debug("Inside FindUser Service.");
//		DaoInterface dm = new DaoMethods();
		return dm.findUser(u);
	}
	
	@Override
	public String convertToBase64Image(InputStream inputStream) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		String base64Image = "";
		try {
			
			byte[] buffer = new byte[4096];
	        int bytesRead = -1;
	         
	        while ((bytesRead = inputStream.read(buffer)) != -1) {
	            outputStream.write(buffer, 0, bytesRead);                  
	        }
	         
	        byte[] imageBytes = outputStream.toByteArray();
	        
	        base64Image = Base64.getEncoder().encodeToString(imageBytes);
	        
	        LOG.debug("Converted image to base64image");
	        
		}catch(Exception e) {
			LOG.fatal("Something went wrong! Exception : "+e);
		}finally {
			
			try {
				
				if(inputStream != null) {
					LOG.debug("InputStream closed.");
					inputStream.close();    
				}
				if(outputStream != null) {
					LOG.debug("OutputStream closed.");
					outputStream.close();    
				}

			}catch(Exception ex) {
				LOG.fatal("Something went wrong! Exception : "+ex);
			}
		}
		return base64Image;
	}
}
