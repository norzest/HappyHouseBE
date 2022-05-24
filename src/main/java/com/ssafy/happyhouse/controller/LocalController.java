package com.ssafy.happyhouse.controller;

import java.sql.SQLException;
import java.util.List;

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

import com.ssafy.happyhouse.model.DongDto;
import com.ssafy.happyhouse.model.GugunDto;
import com.ssafy.happyhouse.model.SidoDto;
import com.ssafy.happyhouse.model.service.LocalService;

@RestController
@RequestMapping("/local")
@CrossOrigin("*")
public class LocalController {

	private static final Logger logger = LoggerFactory.getLogger(LocalController.class);
	
	@Autowired
	private LocalService localService;

	@GetMapping("/sido")
	public ResponseEntity<?> sido() {
		try {
			return new ResponseEntity<List<SidoDto>> (localService.getSidoList(), HttpStatus.OK);
		} catch (SQLException e) {
			e.printStackTrace();
			return new ResponseEntity<Void> (HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/gugun")
	public ResponseEntity<?> gugun(@RequestParam("sidoCode") String sidoCode) {
		logger.debug("현재 시/도 : {}", sidoCode);
		try {
			return new ResponseEntity<List<GugunDto>> (localService.getGugunList(sidoCode), HttpStatus.OK);
		} catch (SQLException e) {
			e.printStackTrace();
			return new ResponseEntity<Void> (HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/dong")
	public ResponseEntity<?> dong(@RequestParam("gugunCode") String gugunCode) {
		logger.debug("현재 구/군 : {}", gugunCode);
		try {
			List<DongDto> list = localService.getDongList(gugunCode);
			list.remove(0);
			return new ResponseEntity<List<DongDto>> (list, HttpStatus.OK);
		} catch (SQLException e) {
			e.printStackTrace();
			return new ResponseEntity<Void> (HttpStatus.NOT_FOUND);
		}
	}
}
