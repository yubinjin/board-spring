package com.care.dbQuiz.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.care.dbQuiz.dto.MemberDTO;
import com.care.dbQuiz.service.MemberService;

@Controller
public class MemberController {
	@Autowired private MemberService service;
	@Autowired private HttpSession session;
	
	@ResponseBody
	@PostMapping(value="overlap", produces = "text/plain;charset=UTF-8")
	public String overlap(@RequestBody(required = false) String id) {
		return service.overlap(id);
	}
	
	@GetMapping("register")
	public String resiget() {
		return "member/register";
	}
	
	@PostMapping("register")
	public String resiget(MemberDTO member, Model model ) {
		String msg = service.register(member);
		model.addAttribute("msg", msg);
		if(msg.equals("가입 완료")) {
			return "redirect:index";
		}
		return "member/register";
	}
	
	@GetMapping("login")
	public String login() {
		return "member/login";
	}
	
	@PostMapping("login")
	public String login(MemberDTO member, Model model ) {
		String msg = service.login(member);
		model.addAttribute("msg", msg);
		if(msg.equals("로그인 성공")) {
			return "redirect:index";
		}
		return "member/login";
	}
	
	@RequestMapping("memberInfo")
	public String memberInfo(Model model) {
		model.addAttribute("members", service.memberInfo());
		return "member/memberInfo";
	}
	@RequestMapping("logout")
	public String logout() {
		session.invalidate();
		return "redirect:index";
	}
	
	@RequestMapping("userInfo")
	public String userInfo(String id, Model model) {
		String sessionId = (String)session.getAttribute("id");
		if(sessionId == null) {
			return "member/login";
		}
		
		if(id == null || id.isEmpty())
			return "forward:memberInfo";
		
		if(sessionId.equals(id) || sessionId.equals("admin")) {
			model.addAttribute("member", service.userInfo(id));
			return "member/userInfo";
		}
		return "forward:memberInfo";
	}
	
	@GetMapping("update")
	public String update(String id, Model model) {
		String sessionId = (String)session.getAttribute("id");
		if(sessionId == null)
			return "redirect:login";
		
		if(sessionId.equals(id)) {
			model.addAttribute("member", service.userInfo(id));
			return "member/update";
		}
		 
		return "forward:memberInfo";
	}
	
	@PostMapping("update")
	public String update(MemberDTO member, RedirectAttributes ra ) {
		String sessionId = (String)session.getAttribute("id");
		if(sessionId == null)
			return "redirect:login";
		
		member.setId(sessionId);
		String msg = service.update(member);
		ra.addFlashAttribute("msg", msg);
		
		if(msg.equals("회원 정보 수정 완료")) {
			return "redirect:index";
		}
		
		return "redirect:update?id="+sessionId;
	}
	
	@GetMapping("delete")
	public String delete(String id, Model model) {
		String sessionId = (String)session.getAttribute("id");
		if(sessionId == null)
			return "redirect:login";
		
		if(sessionId.equals(id)) {
			return "member/delete";
		}
		 
		return "forward:memberInfo";
	}
	
	@PostMapping("delete")
	public String delete(MemberDTO member, Model model ) {
		String sessionId = (String)session.getAttribute("id");
		if(sessionId == null)
			return "redirect:login";
		
		member.setId(sessionId);
		String msg = service.delete(member);
		model.addAttribute("msg", msg);
		
		if(msg.equals("회원 정보 삭제 완료")) {
			session.invalidate();
			return "redirect:index";
		}
		
		return "member/delete";
	}
}




