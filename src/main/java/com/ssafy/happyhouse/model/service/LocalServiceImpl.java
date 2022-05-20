package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.DongDto;
import com.ssafy.happyhouse.model.GugunDto;
import com.ssafy.happyhouse.model.SidoDto;
import com.ssafy.happyhouse.model.mapper.LocalMapper;

@Service
public class LocalServiceImpl implements LocalService {

	private LocalMapper localMapper;
	
	public LocalServiceImpl(LocalMapper localMapper) {
		this.localMapper = localMapper;
	}
	
	@Override
	public List<SidoDto> getSidoList() throws SQLException {
		return localMapper.selectSidoList();
	}

	@Override
	public List<GugunDto> getGugunList(String sidoCode) throws SQLException {
		return localMapper.selectGugunList(sidoCode);
	}

	@Override
	public List<DongDto> getDongList(String gugunCode) throws SQLException {
		return localMapper.selectDongList(gugunCode);
	}

}
