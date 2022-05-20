package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.AptDto;

public interface AptMapper {
	public List<AptDto> selectAptList(Map<String, String> map) throws SQLException;
	public List<AptDto> searchApt(Map<String, String> map) throws SQLException;
}
