package com.example.demo.exception;

/**
 * Created by lixiang38 on 2018/7/27.
 */
public class BizException extends RuntimeException {
	int code;

	public BizException(String message) {
		super(message);
	}

	public BizException(String message, Throwable e) {
		super(message, e);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
