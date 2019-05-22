package car.dch.dao;

import car.dch.entity.Admin;

public interface AdminDao {
	/**
	 * 管理员登录判断
	 * @param userName
	 * @param pwd
	 * @return
	 */
	public Admin adminLogin(String userName,String pwd);
}
