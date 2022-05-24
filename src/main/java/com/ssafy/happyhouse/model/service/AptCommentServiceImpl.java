package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.AptCommentDto;
import com.ssafy.happyhouse.model.mapper.AptCommentMapper;

@Service
public class AptCommentServiceImpl implements AptCommentService {

	@Autowired
	AptCommentMapper aptCommentMapper;
	
	@Override
	public List<AptCommentDto> selectAptCommentList(Map<String, String> map) throws Exception {
		return aptCommentMapper.selectAptCommentList(map);
	}

	@Override
	public boolean registAptComment(Map<String, String> map) throws Exception {
		return aptCommentMapper.registAptComment(map) == 1;
	}

	@Override
	public boolean updateAptComment(Map<String, String> map) throws Exception {
		return aptCommentMapper.updateAptComment(map) == 1;
	}
	
	@Override
	public boolean deleteAptComment(Map<String, String> map) throws Exception {
		return aptCommentMapper.deleteAptComment(map) == 1;
	}

}
