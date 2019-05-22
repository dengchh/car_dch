package car.dch.dao;

import java.util.List;

import car.dch.common.Page;
import car.dch.entity.Car;

public interface CarDao {
	
	/**
	 * 得到被该用户租借的车辆信息
	 * @param uID
	 * @param page
	 * @return
	 */
	public List<Car> listBorrowCar(int uID,Page page);
	/**
	 * 更新车辆信息
	 * @param car
	 * @return
	 */
	public boolean updateCar(Car car);
	
	/**
	 * 得到车辆信息
	 * @param cID
	 * @return
	 */
	public Car getCar(int cID);
	
	/**
	 * 删除车辆信息
	 * @param car
	 * @return
	 */
	public boolean deleteCar(int cID);
	/**
	 * 插入车辆信息
	 * @return
	 */
	public boolean insertCar(Car car);
	
	/**
	 * 展示没有被租借的车辆信息
	 * @return
	 */
	public List<Car> listCar(Page page);
}
