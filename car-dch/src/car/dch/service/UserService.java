package car.dch.service;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import car.dch.common.Page;
import car.dch.common.Result;
import car.dch.common.ResultType;
import car.dch.common.WebObject;
import car.dch.dao.CarDao;
import car.dch.dao.RecordDao;
import car.dch.daoImpl.CarDaoImpl;
import car.dch.daoImpl.RecordDaoImpl;
import car.dch.daoImpl.UserDaoImpl;
import car.dch.entity.Car;
import car.dch.entity.Record;
import car.dch.entity.User;
import tech.be.javasdk.core.tool.JsonTool;

public class UserService {
	
	/**
	 * 展示已经被用户租借的车辆信息
	 * @param webObj
	 */
	public void listBorrowCar(WebObject webObj){
		HttpServletResponse response = webObj.getResponse();
		HttpServletRequest request = webObj.getRequest();
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = null;
		Result result = new Result();
		try {
			out = response.getWriter();
			request.setCharacterEncoding("UTF-8");
			Page page = new Page();
			String currPage = request.getParameter("page");
			String pageSize = request.getParameter("limit");
			page.setCurrPage(Integer.parseInt(currPage));
			page.setPageSize(Integer.parseInt(pageSize));
			User user = (User) request.getSession().getAttribute("user");
			CarDao carDao = new CarDaoImpl();
			List<Car> list = carDao.listBorrowCar(user.getuID(),page);
			if (list != null) {
				result.setCode(ResultType.success.getTypeCode());
				result.setMsg(ResultType.success.getTypeMsg());
				result.setData(list);
				out.print(JsonTool.objToJson(result));
			} else {
				result.setCode(ResultType.fail.getTypeCode());
				result.setMsg(ResultType.fail.getTypeMsg());
				out.print(JsonTool.objToJson(result));
			}
		} catch (Exception e) {
			result.setCode(ResultType.error.getTypeCode());
			result.setMsg(ResultType.error.getTypeMsg());
			out.print(JsonTool.objToJson(result));
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
	
	/**
	 * 归还车辆
	 * @param webObj
	 */
	public void returnCar(WebObject webObj){
		HttpServletResponse response = webObj.getResponse();
		HttpServletRequest request = webObj.getRequest();
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = null;
		Result result = new Result();
		try{
			out = response.getWriter();
			request.setCharacterEncoding("UTF-8");
			User user = (User) request.getSession().getAttribute("user");
			RecordDao recordDao = new RecordDaoImpl();
			if(recordDao.returnCar(Integer.parseInt(request.getParameter("cID")), user.getuID())){
				result.setCode(ResultType.success.getTypeCode());
				result.setMsg(ResultType.success.getTypeMsg());
				out.print(JsonTool.objToJson(result));
			} else {
				result.setCode(ResultType.fail.getTypeCode());
				result.setMsg(ResultType.fail.getTypeMsg());
				out.print(JsonTool.objToJson(result));
			}
		}catch(IOException e){
			result.setCode(ResultType.error.getTypeCode());
			result.setMsg(ResultType.error.getTypeMsg());
			out.print(JsonTool.objToJson(result));
			e.printStackTrace();
		}
	}
	
	/**
	 * 租借车辆
	 * @param webObj	
	 */
	public void borrowCar(WebObject webObj){
		HttpServletResponse response = webObj.getResponse();
		HttpServletRequest request = webObj.getRequest();
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = null;
		Result result = new Result();
		try{
			out = response.getWriter();
			request.setCharacterEncoding("UTF-8");
			User user = (User) request.getSession().getAttribute("user");
			RecordDao recordDao = new RecordDaoImpl();
			Record record = new Record();
			record.setcID(Integer.parseInt(request.getParameter("cID")));
			record.setuID(user.getuID());
			record.setBorrDate(new Timestamp(new Date().getTime()));
			if(recordDao.isBorrow(Integer.parseInt(request.getParameter("cID")), user.getuID())){
				recordDao.borrowCar(record);
				result.setCode(ResultType.success.getTypeCode());
				result.setMsg(ResultType.success.getTypeMsg());
				out.print(JsonTool.objToJson(result));
			} else {
				result.setCode(ResultType.fail.getTypeCode());
				result.setMsg(ResultType.fail.getTypeMsg());
				out.print(JsonTool.objToJson(result));
			}
		}catch(IOException e){
			result.setCode(ResultType.error.getTypeCode());
			result.setMsg(ResultType.error.getTypeMsg());
			out.print(JsonTool.objToJson(result));
			e.printStackTrace();
		}
	}
	
	/**
	 * 展示车辆信息
	 * @param webObj
	 */
	public void listCar(WebObject webObj){
		List<Car> list = new ArrayList<>();
		HttpServletResponse response = webObj.getResponse();
		HttpServletRequest request = webObj.getRequest();
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = null;
		Result result = new Result();
		try {
			out = response.getWriter();
			request.setCharacterEncoding("UTF-8");
			Page page = new Page();
			String currPage = request.getParameter("page");
			String pageSize = request.getParameter("limit");
			page.setCurrPage(Integer.parseInt(currPage));
			page.setPageSize(Integer.parseInt(pageSize));
			CarDao carDao = new CarDaoImpl();
			list = carDao.listCar(page);
			if (list != null) {
				result.setCode(ResultType.success.getTypeCode());
				result.setMsg(ResultType.success.getTypeMsg());
				result.setData(list);
				out.print(JsonTool.objToJson(result));
			} else {
				result.setCode(ResultType.fail.getTypeCode());
				result.setMsg(ResultType.fail.getTypeMsg());
				out.print(JsonTool.objToJson(result));
			}
		} catch (Exception e) {
			result.setCode(ResultType.error.getTypeCode());
			result.setMsg(ResultType.error.getTypeMsg());
			out.print(JsonTool.objToJson(result));
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
	
	/**
	 * 从session获得昵称
	 * @param webObj
	 */
	public void getSessionName(WebObject webObj){
		HttpServletResponse response = webObj.getResponse();
		HttpServletRequest request = webObj.getRequest();
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = null;
		Result result = new Result();
		try{
			out = response.getWriter();
			request.setCharacterEncoding("UTF-8");
			User user = (User) request.getSession().getAttribute("user");
			if(user != null){
				result.setCode(ResultType.success.getTypeCode());
				result.setMsg(ResultType.success.getTypeMsg());
				result.setData(user.getuName());
				out.print(JsonTool.objToJson(result));
			} else {
				result.setCode(ResultType.fail.getTypeCode());
				result.setMsg(ResultType.fail.getTypeMsg());
				out.print(JsonTool.objToJson(result));
			}
		}catch(IOException e){
			result.setCode(ResultType.error.getTypeCode());
			result.setMsg(ResultType.error.getTypeMsg());
			out.print(JsonTool.objToJson(result));
			e.printStackTrace();
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
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = null;
		Result result = new Result();
		try {
			request.setCharacterEncoding("UTF-8");
			out = response.getWriter();
			User user = new User();
			user.setName(request.getParameter("name"));
			user.setuName(request.getParameter("userName"));
			user.setuPhone(request.getParameter("phone"));
			user.setuPwd(request.getParameter("userPwd"));
			user.setuState(1);
			UserDaoImpl userDao = new UserDaoImpl();
			if(userDao.selectUserByUserName(user.getName()) == null && userDao.addUser(user)){
				result.setCode(ResultType.success.getTypeCode());
				result.setMsg(ResultType.success.getTypeMsg());
				out.print(JsonTool.objToJson(result));
			} else {
				result.setCode(ResultType.fail.getTypeCode());
				result.setMsg(ResultType.fail.getTypeMsg());
				out.print(JsonTool.objToJson(result));
			}
		}catch (IOException e) {
			e.printStackTrace();
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
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = null;
		Result result = new Result();
		String userName = request.getParameter("userName");
		UserDaoImpl userDao = new UserDaoImpl();
		User user = userDao.selectUserByUserName(userName);
		try {
			request.setCharacterEncoding("UTF-8");
			out = response.getWriter();
			if (user == null) {
				result.setCode(ResultType.success.getTypeCode());
				result.setMsg(ResultType.success.getTypeMsg());
			} else {
				result.setCode(ResultType.fail.getTypeCode());
				result.setMsg(ResultType.fail.getTypeMsg());
			}
			out.print(JsonTool.objToJson(result));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
	
	/**
	 * 用户登录
	 * @param webObj
	 */
	public void login(WebObject webObj){
		HttpServletResponse response = webObj.getResponse();
		HttpServletRequest request = webObj.getRequest();
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = null;
		Result result = new Result();
		try {
			out = response.getWriter();
			request.setCharacterEncoding("UTF-8");
			UserDaoImpl userDao = new UserDaoImpl();
			String userName = request.getParameter("userName");
			String userPwd = request.getParameter("pwd");
			if(userDao.userLogin(userName, userPwd)){
				request.getSession().setAttribute("user", userDao.selectUserByUserName(userName));
				result.setCode(ResultType.success.getTypeCode());
				result.setMsg(ResultType.success.getTypeMsg());
			} else {
				result.setCode(ResultType.fail.getTypeCode());
				result.setMsg(ResultType.fail.getTypeMsg());
			}
			out.print(JsonTool.objToJson(result));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
}
