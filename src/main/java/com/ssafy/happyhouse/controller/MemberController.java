package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

import com.ssafy.happyhouse.model.MemberDto;
import com.ssafy.happyhouse.model.service.JwtServiceImpl;
import com.ssafy.happyhouse.model.service.MemberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api("UserController")
@RestController
@RequestMapping("/member")
@CrossOrigin("*")
public class MemberController {

	public static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private JwtServiceImpl jwtService;

	@Autowired
	private MemberService memberService;

	@ApiOperation(value = "login", notes = "로그인 시도", response = Map.class)
	@GetMapping("/login")
	public ResponseEntity<Map<String, Object>> login(
			@RequestParam @ApiParam(value = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) Map<String, String> map) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			MemberDto loginMember = memberService.loginService(map);

			if (loginMember != null) {
				String token = jwtService.create("userid", loginMember.getId(), "access-token");// key, data, subject
				logger.debug("로그인 토큰정보 : {}", token);
				resultMap.put("access-token", token);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "회원인증", notes = "회원 정보를 담은 Token을 반환", response = Map.class)
	@GetMapping("/info/{memberid}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("memberid") @ApiParam(value = "인증할 회원의 아이디.", required = true) String id,
			HttpServletRequest request) {

		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtService.isUsable(request.getHeader("access-token"))) {
			logger.info("사용 가능 토큰");
			try {
//				로그인 사용자 정보.
				MemberDto memberDto = memberService.userInfo(id);
				resultMap.put("userInfo", memberDto);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "회원삭제", notes = "회원 삭제 시도", response = Map.class)
	@DeleteMapping("/delete")
	public ResponseEntity<Map<String, Object>> delete(
			@RequestParam @ApiParam(value = "회원 탈퇴 시 필요한 회원정보(아이디, 비밀번호).", required = true) Map<String, String> map) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		try {
			MemberDto memberDto = memberService.loginService(map);

			if (memberDto != null) {
				memberService.deleteService(memberDto);
				logger.debug("회원 탈퇴 : {}", memberDto.getId());
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("회원 탈퇴 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "회원 정보 수정", notes = "회원 정보 수정 시도", response = Map.class)
	@PutMapping("/modify")
	public ResponseEntity<Map<String, Object>> modify(
			@RequestBody @ApiParam(value = "회원 정보 수정 시 필요한 회원정보.", required = true) MemberDto member) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		try {
			if (member != null) {
				memberService.updateService(member);
				logger.debug("회원 정보 수정 : {}", member.toString());
				MemberDto memberDto = memberService.userInfo(member.getId());
				resultMap.put("userInfo", memberDto);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("회원 정보 수정 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "회원 가입", notes = "회원 가입 시도", response = Map.class)
	@PostMapping("/regist")
	public ResponseEntity<Map<String, Object>> regist(
			@RequestBody @ApiParam(value = "회원 가입 시 필요한 회원정보.", required = true) MemberDto member) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		try {
			if (member != null) {

				memberService.registerService(member);
				logger.debug("회원 가입 : {}", member.toString());
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("회원 가입 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	@ApiOperation(value = "아이디찾기", notes = "비밀번호 찾기를 위한 아이디 찾기", response = Map.class)
	@GetMapping("/find/{memberid}")
	public ResponseEntity<Map<String, Object>> searchId(
			@PathVariable("memberid") @ApiParam(value = "회원의 아이디.", required = true) String id,
			HttpServletRequest request) {

		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		MemberDto memberDto = null;
		try {
			memberDto = memberService.userInfo(id);
			if(memberDto != null) {
				resultMap.put("email", memberDto.getEmail());
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;				
			}
			else {
				resultMap.put("message", FAIL);
			}
		} catch (Exception e) {
			logger.error("정보조회 실패 : {}", e);
			resultMap.put("message", e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "비밀번호 수정", notes = "비밀번호 시도", response = Map.class)
	@PutMapping("/modifypwd")
	public ResponseEntity<Map<String, Object>> modifyPwd(
			@RequestBody @ApiParam(value = "비밀번호 수정 시 필요한 회원정보.", required = true) MemberDto member) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		try {
			if (member != null) {
				memberService.passwordChange(member);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("회원 정보 수정 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
}
