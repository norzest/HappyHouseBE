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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.AptCommentDto;
import com.ssafy.happyhouse.model.service.AptCommentService;

@RestController
@RequestMapping("/aptcomment")
@CrossOrigin("*")
public class AptCommentController {
	
	private static final Logger logger = LoggerFactory.getLogger(AptCommentController.class);

	@Autowired
	private AptCommentService aptCommentService;
	
	// params : aptCode
	@GetMapping("/comment")
	private ResponseEntity<?> getAptComment(@RequestParam Map<String, String> map) {
		logger.debug("아파트 댓글 불러오기 : {}", map.get("aptCode"));
		
		try {
			return new ResponseEntity<List<AptCommentDto>>(aptCommentService.selectAptCommentList(map), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void> (HttpStatus.NOT_FOUND);
		}
	}
	
	// params : writerId, aptCode, content
	@PostMapping("/comment")
	private ResponseEntity<?> registAptComment(@RequestParam Map<String, String> map) {
		Map<String, Object> resultMap = new HashMap<>();
		logger.debug("아파트 댓글 작성 : {}", map.get("aptCode"));
		logger.debug("아파트 댓글 작성 : {}", map.get("writerId"));
		
		try {
			aptCommentService.registAptComment(map);
			resultMap.put("message", "success");
			return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void> (HttpStatus.NOT_FOUND);
		}
	}
	
	// params : commentId, content
	@PutMapping("/comment")
	private ResponseEntity<?> updateAptComment(@RequestParam Map<String, String> map) {
		Map<String, Object> resultMap = new HashMap<>();
		logger.debug("아파트 댓글 수정 : {}", map.get("commentId"));
		logger.debug("아파트 댓글 수정 : {}", map.get("content"));
		
		try {
			aptCommentService.updateAptComment(map);
			resultMap.put("message", "success");
			return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void> (HttpStatus.NOT_FOUND);
		}
	}
	
	// params : commentId
	@DeleteMapping("/comment")
	private ResponseEntity<?> deleteAptComment(@RequestParam Map<String, String> map) {
		Map<String, Object> resultMap = new HashMap<>();
		logger.debug("아파트 댓글 삭제 : {}", map.get("aptCode"));
		logger.debug("아파트 댓글 삭제 : {}", map.get("writerId"));
		
		try {
			aptCommentService.deleteAptComment(map);
			resultMap.put("message", "success");
			return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void> (HttpStatus.NOT_FOUND);
		}
	}

}
