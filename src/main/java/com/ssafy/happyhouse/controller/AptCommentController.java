package com.ssafy.happyhouse.controller;

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
	@GetMapping("/getcomment")
	private ResponseEntity<?> getAptComment(@RequestParam Map<String, String> map) {
		logger.debug("아파트 댓글 : {}", map.get("aptCode"));
		
		try {
			return new ResponseEntity<List<AptCommentDto>>(aptCommentService.selectAptCommentList(map), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void> (HttpStatus.NOT_FOUND);
		}
	}

}
