package car.dch.controller;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import car.dch.common.WebObject;

@SuppressWarnings("serial")
public class MainControllerServlet extends HttpServlet{
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException{
		AdminController adminController = new AdminController();
		UserController userController = new UserController();
		WebObject webObject = new WebObject();
		webObject.setRequest(request);
		webObject.setResponse(response);
		String uri = request.getRequestURI();
		if(uri.contains("/admin")){//管理员操作
			adminController.service(webObject); 
		}else if(uri.contains("/user")){//客户操作
			userController.service(webObject);
		}
	}
}
