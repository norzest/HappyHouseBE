package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.BoardDto;
import com.ssafy.happyhouse.model.ListParameterDto;

@Mapper
public interface BoardMapper {
	List<BoardDto> listArticle(ListParameterDto listParameterDto) throws SQLException;
	BoardDto selectArticle(int id) throws SQLException;
	int registerArticle(BoardDto boardDto) throws SQLException;
	int updateArticle(BoardDto boardDto) throws SQLException;
	int deleteArticle(int id) throws SQLException;
	int getTotalCount(ListParameterDto listParameterDto) throws SQLException;
	void hitCounter(String id) throws SQLException;
}
