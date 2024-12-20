package com.jbedu.mysql.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jbedu.mysql.dao.BoardDao;

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
		
		return "redirect:boardList"; // 게시판 리스트로 리다이렉트
	}
	
}
