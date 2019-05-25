package car.dch.service;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import car.dch.common.Page;
import car.dch.common.ServiceCommon;
import car.dch.common.WebObject;
import car.dch.dao.CarDao;
import car.dch.dao.RecordDao;
import car.dch.dao.UserDao;
import car.dch.daoImpl.CarDaoImpl;
import car.dch.daoImpl.RecordDaoImpl;
import car.dch.daoImpl.UserDaoImpl;
import car.dch.entity.Car;
import car.dch.entity.Record;
import car.dch.entity.User;

public class UserService {

	/**
	 * 展示已经被用户租借的车辆信息
	 * 
	 * @param webObj
	 */
	public void listBorrowCar(WebObject webObj) {
		HttpServletResponse response = webObj.getResponse();
		HttpServletRequest request = webObj.getRequest();
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Page page = new Page();
		page.setCurrPage(Integer.parseInt(request.getParameter("page")));
		page.setPageSize(Integer.parseInt(request.getParameter("limit")));
		User user = (User) request.getSession().getAttribute("user");
		CarDao carDao = new CarDaoImpl();
		List<Car> list = carDao.listBorrowCar(user.getuID(), page);
		if (list != null) {
			ServiceCommon.success(response, list);
		} else {
			ServiceCommon.fail(response);
		}
	}

	/**
	 * 归还车辆
	 * 
	 * @param webObj
	 */
	public void returnCar(WebObject webObj) {
		HttpServletResponse response = webObj.getResponse();
		HttpServletRequest request = webObj.getRequest();
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		User user = (User) request.getSession().getAttribute("user");
		RecordDao recordDao = new RecordDaoImpl();
		if (recordDao.returnCar(Integer.parseInt(request.getParameter("cID")), user.getuID())) {
			ServiceCommon.success(response, null);
		} else {
			ServiceCommon.fail(response);
		}
	}

	/**
	 * 租借车辆
	 * 
	 * @param webObj
	 */
	public void borrowCar(WebObject webObj) {
		HttpServletResponse response = webObj.getResponse();
		HttpServletRequest request = webObj.getRequest();
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		User user = (User) request.getSession().getAttribute("user");
		RecordDao recordDao = new RecordDaoImpl();
		Record record = new Record();
		record.setcID(Integer.parseInt(request.getParameter("cID")));
		record.setuID(user.getuID());
		record.setBorrDate(new Timestamp(new Date().getTime()));
		if (recordDao.isBorrow(Integer.parseInt(request.getParameter("cID")), user.getuID())
				&& recordDao.borrowCar(record)) {
			ServiceCommon.success(response, null);
		} else {
			ServiceCommon.fail(response);
		}
	}

	/**
	 * 展示车辆信息
	 * 
	 * @param webObj
	 */
	public void listCar(WebObject webObj) {
		List<Car> list = new ArrayList<>();
		HttpServletResponse response = webObj.getResponse();
		HttpServletRequest request = webObj.getRequest();
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Page page = new Page();
		page.setCurrPage(Integer.parseInt(request.getParameter("page")));
		page.setPageSize(Integer.parseInt(request.getParameter("limit")));
		CarDao carDao = new CarDaoImpl();
		list = carDao.listCar(page);
		if (list != null) {
			ServiceCommon.success(response, list);
		} else {
			ServiceCommon.fail(response);
		}
	}

	/**
	 * 从session获得昵称
	 * 
	 * @param webObj
	 */
	public void getSessionName(WebObject webObj) {
		HttpServletResponse response = webObj.getResponse();
		HttpServletRequest request = webObj.getRequest();
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			ServiceCommon.success(response, user.getuName());
		} else {
			ServiceCommon.fail(response);
		}
	}

	/**
	 * 完成用户注册
	 * 
	 * @param webObj
	 */
	public void reg(WebObject webObj) {
		HttpServletRequest request = webObj.getRequest();
		HttpServletResponse response = webObj.getResponse();
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		User user = new User();
		user.setName(request.getParameter("name"));
		user.setuName(request.getParameter("userName"));
		user.setuPhone(request.getParameter("phone"));
		user.setuPwd(request.getParameter("userPwd"));
		user.setuState(1);
		UserDao userDao = new UserDaoImpl();
		if (userDao.selectUserByUserName(user.getName()) == null && userDao.addUser(user)) {
			ServiceCommon.success(response, null);
		} else {
			ServiceCommon.fail(response);
		}
	}

	/**
	 * 检查用户名是否存在
	 * 
	 * @param webObj
	 */
	public void checkUserName(WebObject webObj) {
		HttpServletResponse response = webObj.getResponse();
		HttpServletRequest request = webObj.getRequest();
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		UserDao userDao = new UserDaoImpl();
		if (userDao.selectUserByUserName(request.getParameter("userName")) == null) {
			ServiceCommon.success(response, null);
		} else {
			ServiceCommon.fail(response);
		}
	}

	/**
	 * 用户登录
	 * 
	 * @param webObj
	 */
	public void login(WebObject webObj) {
		HttpServletResponse response = webObj.getResponse();
		HttpServletRequest request = webObj.getRequest();
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		UserDao userDao = new UserDaoImpl();
		String userName = request.getParameter("userName");
		String userPwd = request.getParameter("pwd");
		if (userDao.userLogin(userName, userPwd)) {
			request.getSession().setAttribute("user", userDao.selectUserByUserName(userName));
			ServiceCommon.success(response, null);
		} else {
			ServiceCommon.fail(response);
		}
	}
}