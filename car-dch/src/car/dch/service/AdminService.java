package car.dch.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import car.dch.common.ConstantClassField;
import car.dch.common.Page;
import car.dch.common.ServiceCommon;
import car.dch.common.WebObject;
import car.dch.dao.AdminDao;
import car.dch.dao.CarDao;
import car.dch.dao.UserDao;
import car.dch.daoImpl.AdminDaoImpl;
import car.dch.daoImpl.CarDaoImpl;
import car.dch.daoImpl.UserDaoImpl;
import car.dch.entity.Admin;
import car.dch.entity.Car;
import car.dch.entity.User;

public class AdminService {

	/**
	 * 更新车辆信息
	 * 
	 * @param webObj
	 */
	public void updateCar(WebObject webObj) {
		HttpServletResponse response = webObj.getResponse();
		HttpServletRequest request = webObj.getRequest();
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		CarDao carDao = new CarDaoImpl();
		Car car = new Car();
		car.setcID(Integer.parseInt(request.getParameter("cID")));
		car.setcName(request.getParameter("carName"));
		car.setPrice(Integer.parseInt(request.getParameter("carPrice")));
		car.setCompany(request.getParameter("carCompany"));
		car.setType(request.getParameter("carType"));
		if (carDao.updateCar(car)) {
			ServiceCommon.success(response, null);
		} else {
			ServiceCommon.fail(response);
		}
	}

	/**
	 * 获得车辆信息
	 * 
	 * @param webObj
	 */
	public void getCar(WebObject webObj) {
		HttpServletResponse response = webObj.getResponse();
		HttpServletRequest request = webObj.getRequest();
		CarDao carDao = new CarDaoImpl();
		Car car = carDao.getCar(Integer.parseInt(request.getParameter("cID")));
		if (car != null) {
			ServiceCommon.success(response, car);
		} else {
			ServiceCommon.fail(response);
		}
	}

	/**
	 * 删除车辆信息
	 * 
	 * @param webObj
	 */
	public void deleteCar(WebObject webObj) {
		HttpServletResponse response = webObj.getResponse();
		HttpServletRequest request = webObj.getRequest();
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		CarDao carDao = new CarDaoImpl();
		if (carDao.deleteCar(Integer.parseInt(request.getParameter("id")))) {
			ServiceCommon.success(response, null);
		} else {
			ServiceCommon.fail(response);
		}
	}

	/**
	 * 通过Session获得昵称
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
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		if (admin != null) {
			ServiceCommon.success(response, admin.getaUserName());
		} else {
			ServiceCommon.fail(response);
		}
	}

	/**
	 * 解封用户
	 * 
	 * @param webObj
	 */
	public void unban(WebObject webObj) {
		HttpServletResponse response = webObj.getResponse();
		HttpServletRequest request = webObj.getRequest();
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		UserDao userDao = new UserDaoImpl();
		if (userDao.ban(Integer.parseInt(request.getParameter("uID")), ConstantClassField.unBan)) {
			ServiceCommon.success(response, null);
		} else {
			ServiceCommon.fail(response);
		}
	}

	/**
	 * 禁用用户
	 * 
	 * @param webObj
	 */
	public void ban(WebObject webObj) {
		HttpServletResponse response = webObj.getResponse();
		HttpServletRequest request = webObj.getRequest();
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		UserDao userDao = new UserDaoImpl();
		if (userDao.ban(Integer.parseInt(request.getParameter("uID")), ConstantClassField.ban)) {
			ServiceCommon.success(response, null);
		} else {
			ServiceCommon.fail(response);
		}
	}

	/**
	 * 通过用户状态展示用户列表
	 * 
	 * @param webObj
	 */
	public void listUserByState(WebObject webObj) {
		HttpServletResponse response = webObj.getResponse();
		HttpServletRequest request = webObj.getRequest();
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		UserDao userDao = new UserDaoImpl();
		Page page = new Page();
		page.setCurrPage(Integer.parseInt(request.getParameter("page")));
		page.setPageSize(Integer.parseInt(request.getParameter("limit")));
		List<User> userList = userDao.listUserByState(page, Integer.parseInt(request.getParameter("state")));
		if (userList != null) {
			ServiceCommon.success(response, userList);
		} else {
			ServiceCommon.fail(response);
		}
	}

	/**
	 * 管理员登录
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
		AdminDao adminDao = new AdminDaoImpl();
		String aUserName = request.getParameter("userName");
		String aPwd = request.getParameter("pwd");
		Admin admin = adminDao.adminLogin(aUserName, aPwd);
		if (admin != null) {
			request.getSession().setAttribute("admin", admin);
			ServiceCommon.success(response, null);
		} else {
			ServiceCommon.fail(response);
		}
	}

	/**
	 * 添加车辆信息
	 * 
	 * @param webObj
	 */
	public void addCar(WebObject webObj) {
		HttpServletResponse response = webObj.getResponse();
		HttpServletRequest request = webObj.getRequest();
		Car car = new Car();
		CarDao carDao = new CarDaoImpl();
		car.setcName(request.getParameter("carName"));
		car.setCompany(request.getParameter("carCompany"));
		car.setPrice(Integer.parseInt(request.getParameter("carPrice")));
		car.setType(request.getParameter("carType"));
		if (carDao.insertCar(car)) {
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
}