package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.BoardDto;
import com.ssafy.happyhouse.model.service.BoardService;
import com.ssafy.happyhouse.util.PageNavigation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api("BoardController")
@RestController
@RequestMapping("/board")
@CrossOrigin("*")
public class BoardController {

	public static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private BoardService boardService;
	
	@ApiOperation(value = "list", notes = "게시판 리스트", response = Map.class)
	@GetMapping
	public ResponseEntity<Map<String, Object>> list(
			@RequestParam @ApiParam(value = "게시판 로드시 필요한 정보(페이지번호, 키워드, 검색명, 게시판 타입).", required = true) Map<String, String> map) {
		logger.debug("키 : {}", map.get("key"));
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		try {
			List<BoardDto> list = boardService.listArticle(map.get("pg"), map.get("key"), map.get("word"), map.get("boardType"));
			PageNavigation navigation = boardService.makePageNavigation(map.get("pg"), map.get("key"), map.get("word"), map.get("boardType"));
			logger.debug("게시판 불러오기 성공");
			
			resultMap.put("articles", list);
			resultMap.put("navi", navigation);
			resultMap.put("key", map.get("key"));
			resultMap.put("word", map.get("word"));
			resultMap.put("message", SUCCESS);
			
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			logger.error("게시판 불러오기 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
}
