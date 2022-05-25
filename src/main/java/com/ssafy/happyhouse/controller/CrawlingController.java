package com.ssafy.happyhouse.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.BoardDto;
import com.ssafy.happyhouse.model.CrawlDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api("CrawlingController")
@RestController
@RequestMapping("/crawl")
@CrossOrigin("*")
public class CrawlingController {
	@ApiOperation(value = "crawling", notes = "뉴스 크롤링", response = Map.class)
	@GetMapping
	public ResponseEntity<Map<String, Object>> crawling() {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		String WeatherURL = "https://search.naver.com/search.naver?where=news&ie=utf8&sm=nws_hty&query=%EB%B6%80%EB%8F%99%EC%82%B0";
		try {
			Document doc = Jsoup.connect(WeatherURL).get();
//			Elements elem = doc.select(".list_news .news_area .news_tit");
//			Elements img = doc.select(".news_wrap .api_get");
			Elements elem = doc.select(".list_news .bx");
			int cnt = 0;
			List<CrawlDto> crawl = new ArrayList<>();
			for(Element e : elem) {
				CrawlDto crawlDto = new CrawlDto();
				crawlDto.setLink(e.select(".news_area .news_tit").attr("href"));
				crawlDto.setTitle(e.select(".news_area .news_tit").attr("title"));
				crawlDto.setImg(e.select(".api_get").attr("src"));
				crawl.add(crawlDto);
			}
			resultMap.put("news", crawl);
			resultMap.put("message", "success");
			status = HttpStatus.ACCEPTED;
		} catch (IOException e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);

	}
}
