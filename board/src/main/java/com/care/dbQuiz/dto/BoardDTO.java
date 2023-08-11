package com.care.dbQuiz.dto;
/*
CREATE TABLE board(
	writer varchar2(255),
	title varchar2(255),
	content varchar2(255),
	fileName varchar2(255),
	cTime varchar2(255),
	vCount number,
	bNumber number,
	primary key(bNumber)
);
COMMIT;
 */
public class BoardDTO {
	private String writer;
	private String title;
	private String content;
	private String fileName;
	private String cTime;
	private int vCount;
	private int bNumber;
	
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
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
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getcTime() {
		return cTime;
	}
	public void setcTime(String cTime) {
		this.cTime = cTime;
	}
	public int getvCount() {
		return vCount;
	}
	public void setvCount(int vCount) {
		this.vCount = vCount;
	}
	public int getbNumber() {
		return bNumber;
	}
	public void setbNumber(int bNumber) {
		this.bNumber = bNumber;
	}
	
	
}
