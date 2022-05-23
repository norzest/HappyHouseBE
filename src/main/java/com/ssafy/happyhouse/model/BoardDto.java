package com.ssafy.happyhouse.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "BoardDto : 게시판 정보", description = "게시판 정보를 나타낸다")
public class BoardDto {
	
	@ApiModelProperty(value = "게시글 번호")
	private int id;
	@ApiModelProperty(value = "게시글 제목")
	private String title;
	@ApiModelProperty(value = "게시글 내용")
	private String content;
	@ApiModelProperty(value = "게시글 작성 날짜")
	private String createdAt;
	@ApiModelProperty(value = "게시글 작성자")
	private String writerId;
	@ApiModelProperty(value = "게시글 조회수")
	private int hit;
	@ApiModelProperty(value = "게시글 타입")
	private String boardType;
	@ApiModelProperty(value = "댓글 수")	
	private int reply;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getWriterId() {
		return writerId;
	}

	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getBoardType() {
		return boardType;
	}

	public void setBoardType(String boardType) {
		this.boardType = boardType;
	}

	@Override
	public String toString() {
		return "BoardDto [id=" + id + ", title=" + title + ", content=" + content + ", createdAt="
				+ createdAt + ", writerId=" + writerId + ", hit=" + hit + ", boardType=" + boardType + "]";
	}

}
