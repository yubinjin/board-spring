package com.care.dbQuiz.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.care.dbQuiz.dto.MemberDTO;

@Repository
public interface MemberRepository {

	MemberDTO findById(String id);
	void register(MemberDTO member);
	List<MemberDTO> findAll();
	void update(MemberDTO member);
	void delete(String id);
}
