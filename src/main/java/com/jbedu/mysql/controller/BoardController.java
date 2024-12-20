package com.jbedu.mysql.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jbedu.mysql.dao.BoardDao;
import com.jbedu.mysql.dto.BoardDto;

@Controller
public class BoardController {
	@RequestMapping(value="write_form")
	public String write_form() {
		return "write_form";
	}
	
	@RequestMapping(value="writeOk")
	public String writeOk(HttpServletRequest request, Model model) {
		
		String bname = request.getParameter("bname");
		String btitle = request.getParameter("btitle");
		String bcontent = request.getParameter("bcontent");
		
		BoardDao boardDao = new BoardDao();
		boardDao.boardWrite(bname, btitle, bcontent);
		
		return "redirect:boardList"; // 게시판 리스트로 요청을 다시들어가서 값을 실어서 리다이렉트
	}
	
	
	@RequestMapping(value="/boardList")
	public String boardList(HttpServletRequest request, Model model) {
		
		//모든 글 목록 가져와서 넘겨주면 
		//가져오기
		BoardDao boardDao = new BoardDao();
		ArrayList<BoardDto> bDtos = boardDao.boardList(); // 모든 글 목록 -> 왜 모든글 목록인지 이해 필요
		
		model.addAttribute("bDtos", bDtos); 		
		
		return "boardList";  // 패킹해서 넘김
	}
	
}
