package com.ssafy.happyhouse.exception;

public class UnauthorizedException extends RuntimeException {

	private static final long serialVersionUID = 2741805788429225721L;

	public UnauthorizedException() {
		super("계정 권한이 유효하지 않습니다.\n다시 로그인을 해주세요.");
	}
}
