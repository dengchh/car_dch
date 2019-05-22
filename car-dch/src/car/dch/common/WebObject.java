package car.dch.common;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebObject {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private ServletConfig config;
	public ServletConfig getConfig() {
		return config;
	}
	public void setConfig(ServletConfig config) {
		this.config = config;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public HttpServletResponse getResponse() {
		return response;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
}
