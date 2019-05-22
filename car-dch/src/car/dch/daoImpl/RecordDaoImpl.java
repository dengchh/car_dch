package car.dch.daoImpl;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import car.dch.dao.RecordDao;
import car.dch.entity.Record;
import car.dch.utils.MybatisUtil;

public class RecordDaoImpl implements RecordDao{

	@Override
	public boolean borrowCar(Record record) {
		SqlSession session = MybatisUtil.getSession();
		boolean flag = session.insert("car.dch.mapping.RecordMapper.borrowCar", record) > 0;
		session.commit();
		session.close();
		return flag;
	}

	@Override
	public boolean isBorrow(int cID, int uID) {
		SqlSession session = MybatisUtil.getSession();
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("cID", cID);
		map.put("uID", uID);
		return session.selectOne("car.dch.mapping.RecordMapper.isBorrow",map) == null;
	}

	@Override
	public boolean returnCar(int cID, int uID) {
		SqlSession session = MybatisUtil.getSession();
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("cID", cID);
		map.put("uID", uID);
		boolean flag = session.update("car.dch.mapping.RecordMapper.deleteRecord", map) > 0;
		session.commit();
		session.close();
		return flag;
	}

}
