package car.dch.controller;

import javax.servlet.http.HttpServletRequest;

import car.dch.common.WebObject;
import car.dch.service.AdminService;

public class AdminController {
	AdminService adminService = new AdminService();
	public void service(WebObject webObj){
		HttpServletRequest request = webObj.getRequest();
		String uri = request.getRequestURI();
		String action = uri.substring(uri.lastIndexOf("/"));
		try{
			if(action.equals("/addCar.do")){
				addCar(webObj);
			} else if(action.equals("/listCar.do")){
				listCar(webObj);
			} else if(action.equals("/login.do")){
				login(webObj);
			} else if(action.equals("/listUserByState.do")){
				listUserByState(webObj);
			} else if(action.equals("/ban.do")){
				ban(webObj);
			} else if(action.equals("/unban.do")){
				unban(webObj);
			} else if(action.equals("/getSessionName.do")){
				getSessionName(webObj);
			} else if(action.equals("/deleteCar.do")){
				deleteCar(webObj);
			} else if(action.equals("/getCar.do")){
				getCar(webObj);
			} else if(action.equals("/updateCar.do")){
				updateCar(webObj);
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	/** 更新车辆信息 */
	private void updateCar(WebObject webObj){
		adminService.updateCar(webObj);
	}
	/** 获得车辆信息 */
	private void getCar(WebObject webObj){
		adminService.getCar(webObj);
	}
	/** 删除车辆信息 */
	private void deleteCar(WebObject webObj){
		adminService.deleteCar(webObj);
	}
	/** 通过Session获得昵称 */
	private void getSessionName(WebObject webObj){
		adminService.getSessionName(webObj);
	}
	/** 解封用户 */
	private void unban(WebObject webObj){
		adminService.unban(webObj);
	}
	/** 禁用用户 */
	private void ban(WebObject webObj){
		adminService.ban(webObj);
	}
	/** 通过状态是否被禁用展示用户列表 */
	private void listUserByState(WebObject webObj){
		adminService.listUserByState(webObj);
	}
	/** 管理员登录 */
	private void login(WebObject webObj){
		adminService.login(webObj);
	}
	/** 展示车辆信息 */
	private void listCar(WebObject webObj){
		adminService.listCar(webObj);
	}
	/** 添加车辆信息 */
	private void addCar(WebObject webObj){
		adminService.addCar(webObj);
	}
}
