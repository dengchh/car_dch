package car.dch.daoImpl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import car.dch.common.Page;
import car.dch.dao.CarDao;
import car.dch.entity.Car;
import car.dch.utils.MybatisUtil;

public class CarDaoImpl implements CarDao{

	@Override
	public boolean insertCar(Car car) {
		SqlSession session = MybatisUtil.getSession();
		int flag = session.insert("car.dch.mapping.CarMapper.addCar", car);
		session.commit();
		session.close();
		return flag > 0;
	}

	@Override
	public List<Car> listCar(Page page) {
		SqlSession session = MybatisUtil.getSession();
		int currPage = page.getCurrPage();
		int pageSize = page.getPageSize();
		String statement = "car.dch.mapping.CarMapper.listCar";
		RowBounds rowBounds = new RowBounds((currPage-1)*pageSize,pageSize);
		List<Car> cars = session.selectList(statement,null,rowBounds);
		session.close();
		return cars;
	}

	@Override
	public boolean deleteCar(int cID) {
		SqlSession session = MybatisUtil.getSession();
		int flag = session.insert("car.dch.mapping.CarMapper.deleteCar", cID);
		session.commit();
		session.close();
		return flag > 0;
	}

	@Override
	public Car getCar(int cID) {
		SqlSession session = MybatisUtil.getSession();
		return session.selectOne("car.dch.mapping.CarMapper.getCar", cID);
	}

	@Override
	public boolean updateCar(Car car) {
		SqlSession session = MybatisUtil.getSession();
		boolean flag = session.update("car.dch.mapping.CarMapper.updateCar", car) > 0;
		session.commit();
		session.close();
		return flag;
	}

	@Override
	public List<Car> listBorrowCar(int uID, Page page) {
		SqlSession session = MybatisUtil.getSession();
		int currPage = page.getCurrPage();
		int pageSize = page.getPageSize();
		String statement = "car.dch.mapping.CarMapper.listBorrowCar";
		RowBounds rowBounds = new RowBounds((currPage-1)*pageSize,pageSize);
		List<Car> cars = session.selectList(statement,uID,rowBounds);
		session.close();
		return cars;
	}

}
