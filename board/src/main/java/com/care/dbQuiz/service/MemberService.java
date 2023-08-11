package com.care.dbQuiz.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.care.dbQuiz.dto.MemberDTO;
import com.care.dbQuiz.repository.MemberRepository;

@Service
public class MemberService {
	@Autowired
	private MemberRepository repository;

	public String register(MemberDTO member) {
		if (member.getId() == null || member.getId().isEmpty()) {
			return "필수 항목입니다.";
		}

		if (member.getPw() == null || member.getPw().isEmpty()) {
			return "필수 항목입니다.";
		}
		if (member.getUsername() == null || member.getUsername().isEmpty()) {
			return "필수 항목입니다.";
		}

		if (member.getPw().equals(member.getConfirm()) == false) {
			return "두 비밀번호가 일치하지 않습니다.";
		}

		boolean resultPw = member.getPw().matches("[a-zA-Z0-9@$!%*#?&]{4,20}");
		boolean resultName = member.getUsername().matches("[가-힣]{2,5}");
		boolean resultMobile = member.getMobile().matches("\\d{3}\\d{3,4}\\d{4}");
		if (resultPw == false) {
			return "비밀번호는 4~20자의 영문 소문자, 숫자와 특수문자만 사용 가능합니다.";
		}
		if (resultName == false) {
			return "이름은 한글만 사용 가능합니다.";
		}
		if (resultMobile == false) {
			return "휴대폰 형식에 맞춰주시기 바랍니다.";
		}

		MemberDTO result = repository.findById(member.getId());
		if (result == null) {
			repository.register(member);
			return "가입 완료";
		}
		return "이미 가입된 아이디 입니다.";
	}
	
	@Autowired private HttpSession session;
	public String login(MemberDTO member) {
		if (member.getId() == null || member.getId().isEmpty()) {
			return "필수 항목입니다.";
		}

		if (member.getPw() == null || member.getPw().isEmpty()) {
			return "필수 항목입니다.";
		}
		
		boolean resultPw = member.getPw().matches("[a-zA-Z0-9@$!%*#?&]{4,20}");
		if (resultPw == false) {
			return "비밀번호는 4~20자의 영문 소문자, 숫자와 특수문자만 사용 가능합니다.";
		}
		
		MemberDTO result = repository.findById(member.getId());
		if(result != null && result.getPw().equals(member.getPw())) {
			session.setAttribute("id", member.getId());
			return "로그인 성공";
		}
		
		return "아이디 또는 비밀번호를 확인 후 다시 시도하세요";
	}
	public List<MemberDTO> memberInfo() {
		return repository.findAll();
	}
	
	public MemberDTO userInfo(String id) {
		return repository.findById(id);
	}
	public String update(MemberDTO member) {
		if (member.getPw() == null || member.getPw().isEmpty()) {
			return "필수 항목입니다.";
		}
		if (member.getUsername() == null || member.getUsername().isEmpty()) {
			return "필수 항목입니다.";
		}

		if (member.getPw().equals(member.getConfirm()) == false) {
			return "두 비밀번호가 일치하지 않습니다.";
		}
		
		boolean resultName = member.getUsername().matches("[가-힣]{2,5}");
		if (resultName == false) {
			return "이름은 한글만 사용 가능합니다.";
		}
		
		repository.update(member);
		return "회원 정보 수정 완료";
	}
	public String delete(MemberDTO member) {
		if (member.getPw() == null || member.getPw().isEmpty()) {
			return "필수 항목입니다.";
		}
		if (member.getPw().equals(member.getConfirm()) == false) {
			return "두 비밀번호가 일치하지 않습니다.";
		}
		MemberDTO result = repository.findById(member.getId());
		if(result != null && result.getPw().equals(member.getPw())) {
			repository.delete(member.getId());
			return "회원 정보 삭제 완료";
		}
		return "아이디 / 비밀번호를 확인 후 다시 입력하세요.";
	}
	public String overlap(String id) {
		if(id == null || id.isEmpty())
			return "아이디를 입력하세요.";
		
		MemberDTO result = repository.findById(id);
		if(result == null)
			return "사용 가능한 아이디 입니다.";
		return "이미 사용중인 아이디입니다.";
	}
}















