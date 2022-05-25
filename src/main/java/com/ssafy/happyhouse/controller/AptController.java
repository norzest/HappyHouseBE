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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.AptDto;
import com.ssafy.happyhouse.model.service.AptService;

@RestController
@RequestMapping("/apt")
@CrossOrigin("*")
public class AptController {
	
	private static final Logger logger = LoggerFactory.getLogger(AptController.class);

	@Autowired
	private AptService aptService;

	// parameter : dongCode(예. 1111011500), year(예. 2020), month(예. 07), apartmentName(예. 자이)
	@GetMapping("/dongApt")
	private ResponseEntity<?> markApt(@RequestParam Map<String, String> map) {
		logger.debug("시도코드 : {}", map.get("sidoCode"));
		logger.debug("구군코드 : {}", map.get("gugunCode"));
		logger.debug("동코드 : {}", map.get("dongCode"));
		logger.debug("년도 : {}", map.get("year"));
		logger.debug("월 : {}", map.get("month"));
		try {
			return new ResponseEntity<List<AptDto>>(aptService.getAptList(map), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void> (HttpStatus.NOT_FOUND);
		}
	}
	
	// parameter : memberId(예. admin)
	@GetMapping("/followApt")
	private ResponseEntity<?> getFollowApt(@RequestParam Map<String, String> map) {
		try {
			return new ResponseEntity<List<AptDto>>(aptService.searchInterestedApt(map), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void> (HttpStatus.NOT_FOUND);
		}
	}
	
	// parameter : aptCode(예. 11110000000001), memberId(예. admin)
	@PostMapping("/followApt")
	private ResponseEntity<?> registFollowApt(@RequestParam Map<String, String> map) {
		Map<String, Object> resultMap = new HashMap<>();
		logger.debug("관심목록 입력 aptCode: {}", map.get("aptCode"));
		logger.debug("관심목록 입력 memberId: {}", map.get("memberId"));
		try {
			aptService.registInterestedApt(map);
			resultMap.put("message", "success");
			return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void> (HttpStatus.NOT_FOUND);
		}
	}
	
	// parameter : aptCode(예. 11110000000001), memberId(예. admin)
	@DeleteMapping("/followApt")
	private ResponseEntity<?> delFollowApt(@RequestParam Map<String, String> map) {
		Map<String, Object> resultMap = new HashMap<>();
		
		try {
			aptService.deleteInterestedApt(map);
			resultMap.put("message", "success");
			return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void> (HttpStatus.NOT_FOUND);
		}
	}
}
