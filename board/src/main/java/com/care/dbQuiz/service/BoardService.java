package com.care.dbQuiz.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.care.dbQuiz.dto.BoardDTO;
import com.care.dbQuiz.dto.MemberDTO;
import com.care.dbQuiz.repository.BoardRepository;
import com.care.dbQuiz.repository.MemberRepository;

@Service
public class BoardService {
	@Autowired private BoardRepository repository;
	@Autowired private HttpSession session;
	@Autowired private MemberRepository memRepo;
	
	
	public String write(MultipartHttpServletRequest multi) {
		String writer = multi.getParameter("writer");
		String title = multi.getParameter("title");
		String content = multi.getParameter("content");
		MultipartFile mFile = multi.getFile("fileName");
		String id = (String)session.getAttribute("id");
		
		
		if(writer == null || writer.isEmpty())
			return "작성자를 입력하세요";
		if(title == null || title.isEmpty())
			return "제목을 입력하세요";
		
		BoardDTO board = new BoardDTO();
		board.setWriter(writer);
		board.setTitle(title);
		board.setContent(content);
		board.setvCount(0);
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		board.setcTime(sdf.format(date));
		String fileName = "";
		
		if(mFile != null && mFile.getSize() != 0) {
			fileName = mFile.getOriginalFilename();
			String path = "C:\\springs15\\upload\\" + id;
			File file = new File(path);
			if(file.exists() == false)
				file.mkdir();
			
			sdf = new SimpleDateFormat("yyyyMMddHHmmss-");
			Calendar cal = Calendar.getInstance();
			fileName = sdf.format(cal.getTime()) + fileName;
			
			path = path + "\\" + fileName;
			file = new File(path);
			
			try {
				mFile.transferTo(file);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		board.setFileName(fileName);
		repository.write(board);
		return "게시글 작성 완료";
	}

	public List<BoardDTO> board() {
		return repository.board();
	}
	
	
	public BoardDTO view(String bn) {
		if(bn == null || bn.isEmpty())
			return null;
		
		int bNumber = 0;
		try {
			bNumber = Integer.parseInt(bn);
		} catch (Exception e) {
			return null;
		}
		
		return repository.view(bNumber);
	}

	public void vCountInc(String bn) {
		repository.vCountInc(Integer.parseInt(bn));
	}

	
	
	
	public String removeProc(String bn, String fileName, String pw, String confirm) {
		if(bn == null || bn.isEmpty())
			return "";
		
		if(fileName == null)
			return "";
		
		if(pw == null || pw.isEmpty())
			return "비밀번호를 입력하세요";
		
		if(pw.equals(confirm) == false)
			return "비밀번호를 확인 후 다시 입력하세요.";
		
		int bNumber = 0;
		try {
			bNumber = Integer.parseInt(bn);
		} catch (Exception e) {
			return "";
		}
		String id = (String)session.getAttribute("id");
		MemberDTO result = memRepo.findById(id);
		if(result != null && result.getPw().equals(pw)) {
			repository.removeProc(bNumber);
			
			String path = "C:\\springs15\\upload\\" + id;
			path = path + "\\" + fileName;
			File file = new File(path);
			
			System.out.println(file);
			System.out.println(path);
			
			if(file.exists() == true) {
				file.delete(); // 파일삭제
				System.out.println(file);
			}
			
			return "게시글 삭제 완료";
		}
		return "비밀번호를 확인 후 다시 시도하세요";
	}

	public BoardDTO modify(String bn) {
		if(bn == null || bn.isEmpty())
			return null;
		
		int bNumber = 0;
		try {
			bNumber = Integer.parseInt(bn);
		} catch (Exception e) {
			return null;
		}
		
		return repository.modify(bNumber);
	}

	public String modifyProc(BoardDTO board) {
		if(board.getTitle() == null || board.getTitle().isEmpty())
			return "제목을 입력하세요.";
		
		int rowCount = repository.modifyProc(board);
		if(rowCount == 0) {
			return "게시글 수정 실패";
		}
		return "게시글 수정 완료";
	}

}

