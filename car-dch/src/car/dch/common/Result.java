package car.dch.common;

public class Result {
	/**
	 * 消息编码
	 */
	private int code;
	/**
	 * 消息
	 */
	private String msg;
	/**
	 * 数据
	 */
	private Object data;
	/**
	 * 记录总数
	 */
	private int count;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
