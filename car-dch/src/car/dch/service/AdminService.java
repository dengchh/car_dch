package car.dch.service;


import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import car.dch.common.ConstantClassField;
import car.dch.common.Page;
import car.dch.common.Result;
import car.dch.common.ResultType;
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
import tech.be.javasdk.core.tool.JsonTool;

public class AdminService {
	
	/**
	 * 更新车辆信息
	 * @param webObj
	 */
	public void updateCar(WebObject webObj){
		HttpServletResponse response = webObj.getResponse();
		HttpServletRequest request = webObj.getRequest();
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = null;
		Result result = new Result();
		try{
			out = response.getWriter();
			request.setCharacterEncoding("UTF-8");
			CarDao carDao = new CarDaoImpl();
			Car car = new Car();
			car.setcID(Integer.parseInt(request.getParameter("cID")));
			car.setcName(request.getParameter("carName"));
			car.setPrice(Integer.parseInt(request.getParameter("carPrice")));
			car.setCompany(request.getParameter("carCompany"));
			car.setType(request.getParameter("carType"));
			if(carDao.updateCar(car)){
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
	 * 获得车辆信息
	 * @param webObj
	 */
	public void getCar(WebObject webObj){
		HttpServletResponse response = webObj.getResponse();
		HttpServletRequest request = webObj.getRequest();
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = null;
		Result result = new Result();
		try{
			out = response.getWriter();
			request.setCharacterEncoding("UTF-8");
			CarDao carDao = new CarDaoImpl();
			Car car = carDao.getCar(Integer.parseInt(request.getParameter("cID")));
			if(!car.equals(null)){
				result.setCode(ResultType.success.getTypeCode());
				result.setMsg(ResultType.success.getTypeMsg());
				result.setData(car);
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
	 * 删除车辆信息
	 * @param webObj
	 */
	public void deleteCar(WebObject webObj){
		HttpServletResponse response = webObj.getResponse();
		HttpServletRequest request = webObj.getRequest();
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = null;
		Result result = new Result();
		try{
			out = response.getWriter();
			request.setCharacterEncoding("UTF-8");
			CarDao carDao = new CarDaoImpl();
			if(carDao.deleteCar(Integer.parseInt(request.getParameter("id")))){
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
	 * 通过Session获得昵称
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
			Admin admin = (Admin) request.getSession().getAttribute("admin");
				if(admin != null){
				result.setCode(ResultType.success.getTypeCode());
				result.setMsg(ResultType.success.getTypeMsg());
				result.setData(admin.getaUserName());
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
	 * 解封用户
	 * @param webObj
	 */
	public void unban(WebObject webObj){
		HttpServletResponse response = webObj.getResponse();
		HttpServletRequest request = webObj.getRequest();
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = null;
		Result result = new Result();
		try{
			out = response.getWriter();
			request.setCharacterEncoding("UTF-8");
			UserDao userDao = new UserDaoImpl();
			if(userDao.ban(Integer.parseInt(request.getParameter("uID")),ConstantClassField.unBan)){
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
	 *禁用用户
	 * @param webObj
	 */
	public void ban(WebObject webObj){
		HttpServletResponse response = webObj.getResponse();
		HttpServletRequest request = webObj.getRequest();
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = null;
		Result result = new Result();
		try{
			out = response.getWriter();
			request.setCharacterEncoding("UTF-8");
			UserDao userDao = new UserDaoImpl();
			if(userDao.ban(Integer.parseInt(request.getParameter("uID")),ConstantClassField.ban)){
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
	 * 通过用户状态展示用户列表
	 * @param webObj
	 */
	public void listUserByState(WebObject webObj){
		HttpServletResponse response = webObj.getResponse();
		HttpServletRequest request = webObj.getRequest();
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = null;
		Result result = new Result();
		try{
			out = response.getWriter();
			request.setCharacterEncoding("UTF-8");
			UserDao userDao = new UserDaoImpl();
			String state = request.getParameter("state");
			Page page = new Page();
			String currPage = request.getParameter("page");
			String pageSize = request.getParameter("limit");
			page.setCurrPage(Integer.parseInt(currPage));
			page.setPageSize(Integer.parseInt(pageSize));
			List<User> userList = userDao.listUserByState(page,Integer.parseInt(state));
			if (userList != null) {
				result.setCode(ResultType.success.getTypeCode());
				result.setMsg(ResultType.success.getTypeMsg());
				result.setData(userList);
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
	 * 管理员登录
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
			AdminDao adminDao = new AdminDaoImpl();
			String aUserName = request.getParameter("userName");
			String aPwd = request.getParameter("pwd");
			Admin admin = adminDao.adminLogin(aUserName, aPwd);
			if(admin != null){
				request.getSession().setAttribute("admin", admin);
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
	 * 添加车辆信息
	 * @param webObj
	 */
	public void addCar(WebObject webObj){
		HttpServletResponse response = webObj.getResponse();
		HttpServletRequest request = webObj.getRequest();
//		ServletConfig config = webObj.getConfig();
		response.setContentType("application/json;charset=utf-8");
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		PrintWriter out = null;
		Result result = new Result();
		try {
/*			String coverPath = "D:\\car\\";
			File cover_path = new File(coverPath);
			com.jspsmart.upload.File cover = null;
			// 判断文件是否存在
			if (!cover_path.exists()) {
				cover_path.mkdir();// 根据文件名创建一个路径
			}*/
/*			// 实例化上传组件
			SmartUpload su = new SmartUpload();
			// 初始化
			su.initialize(config, request, response);
			try {
				// 上传文件，但是没保存
				su.upload();
			} catch (SmartUploadException e) {
				e.printStackTrace();
			}*/
			out = response.getWriter();
/*			try {
				// 保存文件
				cover = su.getFiles().getFile(0);
				cover.saveAs(coverPath + cover.getFileName());
			} catch (SmartUploadException e) {
				e.printStackTrace();
			}
			// 读取表单中的其他信息
			com.jspsmart.upload.Request req = su.getRequest();*/
			Car car = new Car();
			CarDao carDao = new CarDaoImpl();
			car.setcName(request.getParameter("carName"));
			car.setCompany(request.getParameter("carCompany"));
			car.setPrice(Integer.parseInt(request.getParameter("carPrice")));
			car.setType(request.getParameter("carType"));
			boolean flag = carDao.insertCar(car);
			if (flag) {
				result.setCode(ResultType.success.getTypeCode());
				result.setMsg(ResultType.success.getTypeMsg());
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
}
