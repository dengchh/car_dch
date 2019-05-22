package car.dch.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import car.dch.entity.User;

/**
 * 过滤器和Servlet是一样的，要生效的话 必须到web.xml文件里边去注册给Tomcat
 * 当有*.do的请求过来以后,都首先进入这个Filter的doFilter方法 然后由这个方法决定是否继续执行请求
 */
public class LoginCheckFilter implements Filter {

	public void destroy() {

	}

	/**
	 * 
	 */
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
	}

	public void init(FilterConfig config) throws ServletException {

	}

}
