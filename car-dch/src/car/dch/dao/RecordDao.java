package car.dch.dao;

import car.dch.entity.Record;

public interface RecordDao {
	/**
	 *租借车辆
	 * @return
	 */
	public boolean borrowCar(Record record);
	
	/**
	 * 判断是否已经租借过车辆
	 * @param record
	 * @return
	 */
	public boolean isBorrow(int cID, int uID);
	
	/**
	 * 归还车辆
	 * @param cID
	 * @param uID
	 * @return
	 */
	public boolean returnCar(int cID, int uID);
}
