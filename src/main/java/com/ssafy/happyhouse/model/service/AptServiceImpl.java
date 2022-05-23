package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.AptDto;
import com.ssafy.happyhouse.model.mapper.AptMapper;

@Service
public class AptServiceImpl implements AptService {
	
	@Autowired
	AptMapper aptMapper;

	@Override
	public List<AptDto> getAptList(Map<String, String> map) throws Exception {
		return aptMapper.selectAptList(map);
	}

	@Override
	public List<AptDto> searchInterestedApt(Map<String, String> map) throws Exception {
		return aptMapper.searchInterestedApt(map);
	}

	@Override
	public boolean deleteInterestedApt(Map<String, String> map) throws Exception {
		return aptMapper.deleteInterestedApt(map) == 1;
	}

	@Override
	public boolean registInterestedApt(Map<String, String> map) throws Exception {
		return aptMapper.registInterestedApt(map) == 1;
	}

}
