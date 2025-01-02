package com.project.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.BoardDTO;
import com.project.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

	private final BoardService boardService;
	
	@GetMapping()
	public ResponseEntity<?> user() {
		return ResponseEntity.ok("text response type2 sample");
	}	
	
	@GetMapping("/board")
	public ResponseEntity<?> getBoards(Long id, String username) {

		if (id == null && username == null) {
			log.info("getBoards: All");
			return ResponseEntity.ok(boardService.getBoards());
		}
		
		List<BoardDTO> list = new ArrayList<>();

		if (id != null) {
			log.info("getBoards: " + id);
			list.add(boardService.getBoard(id));
		}
		else if (username != null) {
			log.info("getBoards: " + username);
			list = boardService.getBoard(username);
		}
		return ResponseEntity.ok(list);
	}
}