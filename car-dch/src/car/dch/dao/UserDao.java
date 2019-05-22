package car.dch.dao;

import java.util.List;

import car.dch.common.Page;
import car.dch.entity.User;

public interface UserDao {
	
	/**
	 * 更改封禁状态
	 * @param uID
	 * @return
	 */
	public boolean ban(int uID,int state);
	/**
	 * 通过用户状态展示用户信息
	 * @return
	 */
	public List<User> listUserByState(Page page,int uState);
	/** 
	 * 用户登录判断
	 * @param userName
	 * @param userPwd
	 * @return
	 */
	public boolean userLogin(String userName,String userPwd);
	
	/**
	 * 通过用户名寻找用户
	 * @param userName
	 * @return
	 */
	public User selectUserByUserName(String userName);
	
	/**
	 * 用户注册
	 * @param userName
	 * @param pwd
	 * @param name
	 * @param phone
	 * @return
	 */
	public boolean addUser(User user);
}
