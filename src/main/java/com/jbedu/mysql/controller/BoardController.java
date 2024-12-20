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

	@RequestMapping(value = "/write_form")
	public String write_form() {
		return "write_form";
	}

	@RequestMapping(value = "/delete_form")
	public String delete_form() {
		return "delete_form";
	}

	@RequestMapping(value = "/writeOk")
	public String writeOk(HttpServletRequest request, Model model) {

		String bname = request.getParameter("bname");
		String btitle = request.getParameter("btitle");
		String bcontent = request.getParameter("bcontent");

		BoardDao boardDao = new BoardDao();
		boardDao.boardWrite(bname, btitle, bcontent);

		return "redirect:boardList";
	}

	@RequestMapping(value = "/boardList")
	public String boardList(HttpServletRequest request, Model model) {

		BoardDao boardDao = new BoardDao();
		ArrayList<BoardDto> bDtos = boardDao.boardList();// 모든 글 목록

		model.addAttribute("bDtos", bDtos);

		return "boardList";
	}

	@RequestMapping(value = "/deleteOk")
	public String deleteOk(HttpServletRequest request, Model model) {
		
		BoardDao boardDao = new BoardDao();
		int deleteFlag = boardDao.boardDelete(request.getParameter("bnum"));
		//글 삭제 성공 deleteFlag = 1, 실패 0
		
		if(deleteFlag != 1) {//존재하지 않는 글번호 삭제 시도->삭제 실패
			
			model.addAttribute("msg", "이미 삭제된 글번호 입니다.");
			model.addAttribute("url", "boardList");
			
			return "alert";
		} 
			
		return "redirect:boardList";
		
		
		
	}

	@RequestMapping(value = "/alert")
	public String alert() {
		return "alert";
	}

}