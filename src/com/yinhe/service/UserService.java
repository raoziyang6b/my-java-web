package com.yinhe.service;
import java.sql.SQLException;

import com.yinhe.bean.Admin;
import com.yinhe.bean.User;
import com.yinhe.dao.UserDao;
import com.yinhe.web.servlet.ConfigServlet;
public class UserService {

	private UserDao userDao;
	
	public UserService(){
		userDao=(UserDao) ConfigServlet.getMap().get("userDao");
	}
	  //����û�
	public boolean  addUser(User user){
		int update=userDao.addUser(user);
		
		return update>0?true:false;
	}
	
	//�����û�
	public void active(String code){
		userDao.active(code);
	}
	
	//У���û����Ƿ����
	public boolean isExitUserName(String userName){
		try {
			Long count= userDao.isExitUserName(userName);
			return count>0? true:false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	//��¼
	public User login(String userName,String password) {
		try {
			return userDao.login(userName, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//����Ա��¼
	public Admin adminLogin(String userName,String password){
		try {
			return userDao.adminLogin(userName, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
