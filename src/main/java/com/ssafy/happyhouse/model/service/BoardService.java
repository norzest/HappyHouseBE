package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.BoardDto;
import com.ssafy.happyhouse.util.PageNavigation;

public interface BoardService {
	List<BoardDto> listArticle(String pg, String key, String word, String boardType) throws Exception;
	BoardDto selectArticle(int id) throws Exception;
	boolean registerArticle(BoardDto boardDto) throws Exception;
	boolean updateArticle(BoardDto boardDto) throws Exception;
	boolean deleteArticle(int id) throws Exception;
	void hitCounter(String id) throws Exception;
	PageNavigation makePageNavigation(String pg, String key, String word, String boardType) throws Exception;
}
