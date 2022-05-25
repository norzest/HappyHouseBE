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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	@GetMapping("/list")
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
	@ApiOperation(value = "hit", notes = "게시판 리스트", response = Map.class)
	@PostMapping("/hit")
	public ResponseEntity<Map<String, Object>> hitCounter(@RequestParam String id){
		System.out.println("ghcnf");
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		System.out.println(id);
		try {
			boardService.hitCounter(id);
			logger.debug("조회수 증가 성공");
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		}catch(Exception e) {
			logger.error("조회수 증가 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return  new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	//selectArticle
	@ApiOperation(value = "detail", notes = "게시글 상세조회", response = Map.class)
	@GetMapping("/detail")
	public ResponseEntity<Map<String, Object>> selectDetail(@RequestParam String id) {
		
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		try {
			BoardDto board = boardService.selectArticle(Integer.parseInt(id));
			logger.debug("게시글 불러오기 성공");
			
			resultMap.put("article", board);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			logger.error("게시글 불러오기 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
    @ApiOperation(value = "게시판 글등록", notes = "새로운 게시글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/write")
	public ResponseEntity<String> writeBoard(@RequestBody BoardDto board) {
		logger.debug("writeBoard - 호출");
		try {
			if (boardService.registerArticle(board)) {
				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	@ApiOperation(value = "게시판 글정보 수정", notes = "글번호에 해당하는 게시글의 정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("/detail/{articleno}")
	public ResponseEntity<String> updateBoard(@RequestBody BoardDto board) {
		logger.debug("updateBoard - 호출");
		
		try {
			if (boardService.updateArticle(board)) {
				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

    @ApiOperation(value = "게시판 글삭제", notes = "글번호에 해당하는 게시글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/detail/{articleno}")
	public ResponseEntity<String> deleteBoard(@PathVariable int articleno) {
		logger.debug("deleteBoard - 호출");
		try {
			if (boardService.deleteArticle(articleno)) {
				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
