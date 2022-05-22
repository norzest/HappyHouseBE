package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.BoardDto;
import com.ssafy.happyhouse.model.ListParameterDto;
import com.ssafy.happyhouse.model.mapper.BoardMapper;
import com.ssafy.happyhouse.util.PageNavigation;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardMapper boardMapper;
	
	@Override
	public List<BoardDto> listArticle(String pg, String key, String word, String boardType) throws Exception {
		int pgno = pg != null ? Integer.parseInt(pg) : 1;
		int countPerPage = 10;
		int start= (pgno - 1) * countPerPage;
		
		ListParameterDto listParameterDto = new ListParameterDto();
		listParameterDto.setKey(key == null ? "" : key.trim());
		listParameterDto.setWord(word == null ? "" : word.trim());
		listParameterDto.setStart(start);
		listParameterDto.setCurrentPerPage(countPerPage);
		listParameterDto.setBoardType(boardType);
		return boardMapper.listArticle(listParameterDto);
	}

	@Override
	public BoardDto selectArticle(int id) throws Exception {
		return boardMapper.selectArticle(id);
	}

	@Override
	public boolean registerArticle(BoardDto boardDto) throws Exception {
		return boardMapper.registerArticle(boardDto) == 1;
	}

	@Override
	public boolean updateArticle(BoardDto boardDto) throws Exception {
		return boardMapper.updateArticle(boardDto) == 1;
	}

	@Override
	public boolean deleteArticle(int id) throws Exception {
		return boardMapper.deleteArticle(id) == 1;
	}

	@Override
	public PageNavigation makePageNavigation(String pg, String key, String word, String boardType) throws Exception {
		PageNavigation pageNavigation = new PageNavigation();
		int currentPage = pg != null ? Integer.parseInt(pg) : 1;
		int naviSize = 5;
		int countPerPage = 10;
		pageNavigation.setCurrentPage(currentPage);
		pageNavigation.setCountPerPage(countPerPage);
		pageNavigation.setNaviSize(naviSize);
		
		ListParameterDto listParameterDto = new ListParameterDto();
		listParameterDto.setKey(key == null ? "" : key.trim());
		listParameterDto.setWord(word == null ? "" : word.trim());
		listParameterDto.setBoardType(boardType);
		
		int totalCount = boardMapper.getTotalCount(listParameterDto);
		pageNavigation.setTotalCount(totalCount);
		int totalPageCount = (totalCount - 1) / countPerPage + 1;
		pageNavigation.setTotalPageCount(totalPageCount);
		pageNavigation.setStartRange(currentPage <= naviSize);
		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < currentPage;
		pageNavigation.setEndRange(endRange);
		pageNavigation.makeNavigator();
		return pageNavigation;
	}
	
	@Override
	public void hitCounter(String id) throws Exception {
		boardMapper.hitCounter(id);
		
	}

}
