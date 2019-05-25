package car.dch.common;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import car.dch.common.Result;
import car.dch.common.ResultType;
import tech.be.javasdk.core.tool.JsonTool;

@SuppressWarnings("null")
public class ServiceCommon {
	/**
	 * 操作成功返回result
	 * @param response
	 * @param obj
	 */
	public static void success(HttpServletResponse response,Object obj){
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = null;
		Result result = new Result();
		try {
			out = response.getWriter();
			result.setCode(ResultType.success.getTypeCode());
			result.setMsg(ResultType.success.getTypeMsg());
			result.setData(obj);
			out.print(JsonTool.objToJson(result));
		} catch (IOException e) {
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
	 * 操作失败返回result
	 * @param response
	 */
	public static void fail(HttpServletResponse response){
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = null;
		Result result = new Result();
		try {
			out = response.getWriter();
			result.setCode(ResultType.fail.getTypeCode());
			result.setMsg(ResultType.fail.getTypeMsg());
			out.print(JsonTool.objToJson(result));
		} catch (IOException e) {
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
