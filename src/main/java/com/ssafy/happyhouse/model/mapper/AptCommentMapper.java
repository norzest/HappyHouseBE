package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.AptCommentDto;

@Mapper
public interface AptCommentMapper {
	public List<AptCommentDto> selectAptCommentList(Map<String, String> map) throws SQLException;
	public int registAptComment(Map<String, String> map) throws SQLException;
	public int updateAptComment(Map<String, String> map) throws SQLException;
	public int deleteAptComment(Map<String, String> map) throws SQLException;
}
