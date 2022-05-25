package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.MemberDto;

@Mapper
public interface MemberMapper {
	//회원가입
	void createMember(MemberDto memberDto) throws SQLException;
	
	//회원정보 select
	MemberDto selectMember(Map<String, String> map) throws SQLException;
	
	//회원정보수정
	void updateMember(MemberDto memberDto) throws SQLException;
	
	//회원탈퇴
	void deleteMember(MemberDto memberDto) throws SQLException;
	
	//회원정보 select By Id
	MemberDto selectMemberById(String id) throws SQLException;
	
	//비밀번호 변경
	void updatePassword(MemberDto memberDto) throws SQLException;
	

}
