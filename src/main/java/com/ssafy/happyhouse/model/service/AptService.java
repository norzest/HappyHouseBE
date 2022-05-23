package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.AptDto;

public interface AptService {
	public List<AptDto> getAptList(Map<String, String> map) throws Exception;
	public List<AptDto> searchInterestedApt(Map<String, String> map) throws Exception;
	public boolean deleteInterestedApt(Map<String, String> map) throws Exception;
	public boolean registInterestedApt(Map<String, String> map) throws Exception;
}
