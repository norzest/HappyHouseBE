package com.ssafy.happyhouse.model.service;

import java.util.Map;

import com.ssafy.happyhouse.model.MemberDto;

public interface MemberService {
	void registerService(MemberDto memberDto) throws Exception;
	MemberDto loginService(Map<String, String> map) throws Exception;
	void updateService(MemberDto memberDto) throws Exception;
	void deleteService(MemberDto memberDto) throws Exception;
	MemberDto userInfo(String id) throws Exception;
	void passwordChange(MemberDto memberDto) throws Exception;
	
}
