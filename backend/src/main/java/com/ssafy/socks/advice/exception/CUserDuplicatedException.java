package com.ssafy.socks.advice.exception;

public class CUserDuplicatedException extends IllegalStateException {
	public CUserDuplicatedException(String msg, Throwable t) {
		super(msg,t);
	}

	public CUserDuplicatedException(String msg) {
		super(msg);
	}

	public CUserDuplicatedException() {
	}
}
