package com.bms.utils;

/**
 * @author lichao
 * API 接口返回对象
 * @param <T>
 * 2018年11月25日
 */
public class APIResult<T> {
	private int code;
	private String msg;
	private T obj;
	
	public APIResult() {
		init(200, "", null);
	}
	
	public APIResult(int code) {
		init(code, "", null);
	}
	
	public APIResult(int code, String msg) {
		init(code, msg, null);
	}
	
	public APIResult(int code, String msg, T obj) {
		init(code, msg, obj);
	}
	
	private void init(int code, String msg, T obj) {
		this.code = code;
		this.msg = msg;
		this.obj = obj;
	}
	
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
	public T getObj() {
		return obj;
	}
	public void setObj(T obj) {
		this.obj = obj;
	}
	
	@Override
	public String toString() {
		return "APIResult [code=" + code + ", msg=" + msg + ", obj=" + obj + "]";
	}
	
}
