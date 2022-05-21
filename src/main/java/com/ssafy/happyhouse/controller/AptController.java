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

import com.ssafy.happyhouse.model.AptDto;
import com.ssafy.happyhouse.model.service.AptService;

@RestController
@RequestMapping("/getApt")
@CrossOrigin("*")
public class AptController {
	
	private static final Logger logger = LoggerFactory.getLogger(AptController.class);

	@Autowired
	private AptService aptService;

	// parameter : dongCode(예. 1111011500), year(예. 2020), month(예. 07)
	@GetMapping("/dongApt")
	private ResponseEntity<?> markApt(@RequestParam Map<String, String> map) {
		logger.debug("동코드 : {}", map.get("dongCode"));
		try {
			return new ResponseEntity<List<AptDto>>(aptService.getAptList(map), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void> (HttpStatus.NOT_FOUND);
		}
	}
}