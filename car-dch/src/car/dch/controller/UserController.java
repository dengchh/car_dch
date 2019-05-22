package car.dch.controller;

import java.io.IOException;


import javax.servlet.http.HttpServletRequest;

import car.dch.common.WebObject;
import car.dch.service.UserService;

public class UserController {
	UserService userService = new UserService();
	public void service(WebObject webObj) throws IOException{		
		HttpServletRequest request = webObj.getRequest();
		String uri = request.getRequestURI();
		String action = uri.substring(uri.lastIndexOf("/"));
		try{
			if(action.equals("/reg.do")){
				reg(webObj);
			} else if (action.equals("/checkUser.do")){
				checkUserName(webObj);
			} else if (action.equals("/login.do")){
				login(webObj);
			} else if (action.equals("/getSessionName.do")){
				getSessionName(webObj);
			} else if (action.equals("/listCar.do")){
				listCar(webObj);
			} else if (action.equals("/borrowCar.do")){
				borrowCar(webObj);
			} else if (action.equals("/returnCar.do")){
				returnCar(webObj);
			} else if (action.equals("/listBorrowCar.do")){
				listBorrowCar(webObj);
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 展示已经被当前用户租借的车辆信息
	 * @param webObj
	 */
	private void listBorrowCar(WebObject webObj){
		userService.listBorrowCar(webObj);
	}
	
	/** 归还车辆 */
	private void returnCar(WebObject webObj){
		userService.returnCar(webObj);
	}
	
	/** 租借车辆 */
	private void borrowCar(WebObject webObj){
		userService.borrowCar(webObj);
	}
	
	/** 展示车辆信息 */
	private void listCar(WebObject webObj){
		userService.listCar(webObj);
	}
	
	/** 从session得到用户昵称 */
	private void getSessionName(WebObject webObj){
		userService.getSessionName(webObj);
	}
	
	/** 登录 */
	private void reg(WebObject webObj){
		userService.reg(webObj);
	}
	
	/** 检查用户名是否存在  */
	private void checkUserName(WebObject webObj){
		userService.checkUserName(webObj);
	}
	
	private void login(WebObject webObj){
		userService.login(webObj);
	}
}
