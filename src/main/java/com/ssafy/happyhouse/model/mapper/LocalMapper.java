package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.DongDto;
import com.ssafy.happyhouse.model.GugunDto;
import com.ssafy.happyhouse.model.SidoDto;

public interface LocalMapper {
	public List<DongDto> selectDongList(String gugunCode) throws SQLException;
	public List<GugunDto> selectGugunList(String sidoCode) throws SQLException;
	public List<SidoDto> selectSidoList() throws SQLException;

}
