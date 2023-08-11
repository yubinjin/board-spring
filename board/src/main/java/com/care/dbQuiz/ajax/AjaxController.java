package com.care.dbQuiz.ajax;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AjaxController {
	
	@GetMapping("ex1")
	public String ex1Get() {
		return "ajax/ex1";
	}
	
	@ResponseBody
	@PostMapping(value="ex1", produces = "text/plain;charset=UTF-8")
	public String ex1Post() {
		return "서버의 응답 데이터 !!!!!!!!";
	}
	
	@GetMapping("ex2")
	public String ex2Get() {
		return "ajax/ex2";
	}
	
	@ResponseBody
	@PostMapping(value="ex2", produces = "text/plain;charset=UTF-8")
	public String ex2Post(@RequestBody String sd) {
		if("admin".equals(sd))
			return "이미 가입된 정보입니다.";
		return "사용 가능한 정보입니다.";
//		return "서버의 응답 데이터 : " + sd;
	}
	
	@GetMapping("ex3")
	public String ex3Get() {
		return "ajax/ex3";
	}
	@ResponseBody
	@PostMapping(value="ex3", produces = "application/json;charset=UTF-8")
	public Map<String, String> ex3Post(@RequestBody Map<String, String> map) {
		String id = map.get("id");
		String pw = map.get("pw");
		String confirm = map.get("confirm");
		
		id = "서버 응답 데이터 : " + id;
		pw = "서버 응답 데이터 : " + pw;
		confirm = "서버 응답 데이터 : " + confirm;
		
		map.put("id", id);
		map.put("pw", pw);
		map.put("confirm", confirm);
		
		return map;
	}
}














