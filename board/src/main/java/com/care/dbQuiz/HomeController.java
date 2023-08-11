package com.care.dbQuiz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping("/")
	public String index() {
		return "member/index";
	}
	
	@RequestMapping("index")
	public String index(Model model) {
		return  "member/index";
	}
	@RequestMapping("header")
	public String header() {
		return  "default/header";
	}
	@RequestMapping("main")
	public String main() {
		return  "default/main";
	}
	@RequestMapping("footer")
	public String footer() {
		return  "default/footer";
	}
}
