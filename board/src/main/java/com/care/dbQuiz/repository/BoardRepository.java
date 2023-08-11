package com.care.dbQuiz.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.care.dbQuiz.dto.BoardDTO;

@Repository
public interface BoardRepository {

	void write(BoardDTO board);

	List<BoardDTO> board();
	
	BoardDTO view(int bNumber);

	void vCountInc(int bNumber);

	void removeProc(int bNumber);

	BoardDTO modify(int bNumber);

	int modifyProc(BoardDTO board);
}
