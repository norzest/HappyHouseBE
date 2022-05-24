package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.AptCommentDto;

public interface AptCommentService {
	public List<AptCommentDto> selectAptCommentList(Map<String, String> map) throws Exception;
	public boolean registAptComment(Map<String, String> map) throws Exception;
	public boolean updateAptComment(Map<String, String> map) throws Exception;
	public boolean deleteAptComment(Map<String, String> map) throws Exception;
}
