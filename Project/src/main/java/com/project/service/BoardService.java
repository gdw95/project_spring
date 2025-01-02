package com.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.project.dto.BoardDTO;
import com.project.entity.Board;
import com.project.repository.BoardRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {

	private final BoardRepository boardRepo;

	private List<BoardDTO> toDTO(List<Board> list) {
		List<BoardDTO> ret = new ArrayList<>();
		for(Board b : list) {
			ret.add(b.getDTO());
		}
		return ret;
	}
	
	public List<BoardDTO> getBoards() {
		List<Board> list = boardRepo.findAll();
		return toDTO(list);
	}

	public BoardDTO getBoard(Long id) {
		return boardRepo.findById(id).get().getDTO();
	}
	
	public List<BoardDTO> getBoard(String username) {
		List<Board> list = boardRepo.findByUsername(username);
		return toDTO(list);
	}
}