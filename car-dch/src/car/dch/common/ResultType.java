package car.dch.common;

public enum ResultType {
	success(1000,"操作成功"),
	fail(2000,"操作失败"),
	error(3000,"系统异常");
	private final int code;
	private final String msg;
	private ResultType(int code,String msg){
		this.code = code;
		this.msg = msg;
	}
	public int getTypeCode() {
		return code;
	}
	public String getTypeMsg() {
		return msg;
	}
	
}
