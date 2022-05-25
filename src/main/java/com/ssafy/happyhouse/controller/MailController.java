package com.ssafy.happyhouse.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.MailDto;
import com.ssafy.happyhouse.model.MemberDto;
import com.ssafy.happyhouse.model.service.MailService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api("MailController")
@RestController
@RequestMapping("/api/mail")
@CrossOrigin("*")
public class MailController {
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private MailService mailService;
	
	@ApiOperation(value = "mail", notes = "메일전송")
	@PostMapping
	public @ResponseBody void mailsend(@RequestBody MailDto mailDto) {
	SimpleMailMessage message = new SimpleMailMessage();
	String pwd;
	try {
		pwd = mailService.ModifyPassword(mailDto.getId());
		message.setTo(mailDto.getEmail());
        message.setFrom("happyhousesk@naver.com");
        message.setSubject("[HappyHouse] 비밀번호 찾기 - 임시 비밀번호 안내드립니다.");
        message.setText("임시 비밀번호 : " + pwd);
        javaMailSender.send(message);
	} catch (Exception e) {
		e.printStackTrace();
	}

}
	
}
