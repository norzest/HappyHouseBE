package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.Map;

import com.ssafy.happyhouse.model.MemberDto;

public interface MemberMapper {
	//회원가입
	void createMember(MemberDto memberDto) throws SQLException;
	
	//회원정보select
	MemberDto selectMember(Map<String, String> map) throws SQLException;
	
	//회원정보수정
	void updateMember(MemberDto memberDto) throws SQLException;
	
	//회원탈퇴
	void deleteMember(MemberDto memberDto) throws SQLException;
}
