package com.ssafy.socks.advice.exception;

public class CMagazineNotFoundException extends RuntimeException{
	public CMagazineNotFoundException(String msg, Throwable t) {
		super(msg,t);
	}

	public CMagazineNotFoundException(String msg) {
		super(msg);
	}

	public CMagazineNotFoundException() {
	}
}
