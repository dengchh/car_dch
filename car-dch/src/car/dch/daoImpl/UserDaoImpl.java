package car.dch.daoImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import car.dch.common.ConstantClassField;
import car.dch.common.Page;
import car.dch.dao.UserDao;
import car.dch.entity.User;
import car.dch.utils.MybatisUtil;

public class UserDaoImpl implements UserDao {

	@Override
	public boolean userLogin(String userName, String userPwd) {
		SqlSession session = MybatisUtil.getSession();
		Map<String,String> map = new HashMap<String,String>();
		map.put("userName", userName);
		map.put("userPwd", userPwd);
		User user = session.selectOne("car.dch.mapping.UserMapper.userLogin",map);
		if(user == null){
			return false;
		} else {
			return user.getuState() == ConstantClassField.unBan;
		}
	}

	@Override
	public User selectUserByUserName(String userName) {
		SqlSession session = MybatisUtil.getSession();
		return session.selectOne("car.dch.mapping.UserMapper.selectUserByUserName", userName);
	}

	@Override
	public boolean addUser(User user) {
		SqlSession session = MybatisUtil.getSession();
		int flag = session.insert("car.dch.mapping.UserMapper.addUser", user);
		session.commit();
		session.close();
		return flag > 0;
	}

	@Override
	public List<User> listUserByState(Page page,int uState) {
		SqlSession session = MybatisUtil.getSession();
		int currPage=page.getCurrPage();
		int pageSize=page.getPageSize();
		String statement = "car.dch.mapping.UserMapper.listUserByState";
		RowBounds rowBounds = new RowBounds((currPage-1)*pageSize,pageSize);
		List<User> users = session.selectList(statement,uState,rowBounds);
		session.close();
		return users;
	}

	@Override
	public boolean ban(int uID,int state) {
		SqlSession session = MybatisUtil.getSession();
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("userID", uID);
		map.put("userState", state);
		int flag = session.update("car.dch.mapping.UserMapper.ban", map);
		session.commit();
		session.close();
		return flag > 0;
	}

}
