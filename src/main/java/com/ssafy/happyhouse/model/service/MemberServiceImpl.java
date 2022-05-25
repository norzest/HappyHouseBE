package com.ssafy.happyhouse.model.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.MemberDto;
import com.ssafy.happyhouse.model.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper memberMapper;
	
//	public UserServiceImpl(UserMapper userMapper) {
//		this.userMapper = userMapper;
//	}
	
	@Override
	public void registerService(MemberDto memberDto) throws Exception {
		memberMapper.createMember(memberDto);
	}

	@Override
	public MemberDto loginService(Map<String, String> map) throws Exception {
		return memberMapper.selectMember(map);
	}

	@Override
	public void updateService(MemberDto memberDto) throws Exception {
		memberMapper.updateMember(memberDto);
	}

	@Override
	public void deleteService(MemberDto memberDto) throws Exception {
		memberMapper.deleteMember(memberDto);
	}

	@Override
	public MemberDto userInfo(String id) throws Exception {
		return memberMapper.selectMemberById(id);
	}
	
	@Override
	public void passwordChange(MemberDto memberDto) throws Exception {
		memberMapper.updatePassword(memberDto);
	}
	

}
