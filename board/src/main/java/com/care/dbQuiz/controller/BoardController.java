package com.care.dbQuiz.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.care.dbQuiz.dto.BoardDTO;
import com.care.dbQuiz.service.BoardService;

@Controller
public class BoardController {
	@Autowired private BoardService service;
	
	@GetMapping("write")
	public String write() {
		return "board/writeForm";
	}
	@PostMapping("write")
	public String write(MultipartHttpServletRequest multi, Model model) {
		String msg = service.write(multi);
		model.addAttribute("msg", msg);
		if(msg.equals("게시글 작성 완료")) {
			return "redirect:board";
		}
		return "board/writeForm";
	}
	
	@GetMapping("view")
	public String view(Model model, String bn) {
		BoardDTO board = service.view(bn);
		if(board == null)
			return "forward:board";
		
		model.addAttribute("board", board);
		service.vCountInc(bn);
		return "board/viewForm";
	}
	
	@RequestMapping("board")
	public String board(Model model) {
		model.addAttribute("boards", service.board());
		return "board/boardForm";
	}
	
	@RequestMapping("download")
	public void download(String fileName, String writer, HttpServletResponse response) 
			throws IOException {
		if(fileName == null || fileName.isEmpty())
			return;
		if(writer == null || writer.isEmpty())
			return;
		
		String path = "C:\\springs15\\upload\\" + writer;
		path = path + "\\" + fileName;
		File file = new File(path);
		
		if(file.exists() == false)
			return;
		
		fileName = fileName.substring(15);
		
		response.addHeader("content-disposition", "attchment;filename="+URLEncoder.encode(fileName, "UTF-8"));
		
		FileInputStream fis = new FileInputStream(file); 
		FileCopyUtils.copy(fis, response.getOutputStream());
		fis.close();
	}
	@Autowired private HttpSession session;
	public boolean loginCheck() {
		String id = (String)session.getAttribute("id");
		if(id == null)
			return true;
		return false;
	}
	
	@GetMapping("remove")
	public String remove(String bn, String fileName, Model model) {
		if(loginCheck())
			return "redirect:login";
		model.addAttribute("bn", bn);
		model.addAttribute("fileName", fileName);
		return "board/removeForm";
	}
	
	@PostMapping("removeProc")
	public String removeProc(
			String bn, String fileName, String pw, String confirm, RedirectAttributes ra) {
		if(loginCheck())
			return "redirect:login";
		
		System.out.println("controller : " + fileName);
		String msg = service.removeProc(bn, fileName, pw, confirm);
		ra.addFlashAttribute("msg", msg);
		if(msg.equals("게시글 삭제 완료")) {
			return "redirect:board";
		}
		return "redirect:remove?bn="+bn+"&fileName="+fileName;
	}
	
	@GetMapping("modify")
	public String modify(String bn, Model model) {
		if(loginCheck())
			return "redirect:login";
		
		BoardDTO board = service.modify(bn);
		if(board == null)
			return "redirect:board";
		
		String id = (String)session.getAttribute("id");
		if(id.equals(board.getWriter()) == false)
			return "redirect:board";
			
		model.addAttribute("board", board);
		return "board/modifyForm";
	}
	
	@PostMapping("modifyProc")
	public String modifyProc(BoardDTO board, RedirectAttributes ra) {
		if(loginCheck())
			return "redirect:login";
	
		String msg = service.modifyProc(board);
		ra.addFlashAttribute("msg", msg);
		if(msg.equals("게시글 수정 완료")) {
			return "redirect:board";
		}
		
		return "redirect:modify?bn="+board.getbNumber();
	}
}





















