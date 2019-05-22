package car.dch.daoImpl;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import car.dch.dao.AdminDao;
import car.dch.entity.Admin;
import car.dch.utils.MybatisUtil;

public class AdminDaoImpl implements AdminDao {
	@Override
	public Admin adminLogin(String aUserName, String aPwd) {
		SqlSession session = MybatisUtil.getSession();
		Map<String,String> map = new HashMap<String,String>();
		map.put("aUserName", aUserName);
		map.put("aPwd", aPwd);
		Admin admin = session.selectOne("car.dch.mapping.AdminMapper.adminLogin",map);
		return admin;
	}
}
