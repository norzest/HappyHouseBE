package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.DongDto;
import com.ssafy.happyhouse.model.GugunDto;
import com.ssafy.happyhouse.model.SidoDto;

public interface LocalService {
	public List<SidoDto> getSidoList() throws SQLException;
	public List<GugunDto> getGugunList(String sidoCode) throws SQLException;
	public List<DongDto> getDongList(String gugunCode) throws SQLException;
}
