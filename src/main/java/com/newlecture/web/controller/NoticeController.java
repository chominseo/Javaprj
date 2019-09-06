package com.newlecture.web.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.newlecture.web.dao.NoticeDao;
import com.newlecture.web.entity.NoticeView;

@Controller
@RequestMapping("/notice/")
public class NoticeController {
	
	@Autowired
	private NoticeDao noticeDao;
	
	//입력방법 2 : 스프링을 이용한 입력방법
	@RequestMapping("list")
	public String list(Model model, 
			@RequestParam(name="p", defaultValue="1") Integer page
			) throws ClassNotFoundException, SQLException {
		
		
		List<NoticeView> list = noticeDao.getList();
		
		model.addAttribute("list", list);
		
		//System.out.println("list 요청");
		
		//return "notice/list";
		return "list";
	}
	
	/*
	//입력방법1 : 서블릿 기능을 이용한 입력방법
	@RequestMapping("list")
	public String noticeList(Model model, HttpServletRequest request) throws ClassNotFoundException, SQLException {
		
		int page = 1;
		String p = request.getParameter("p");
		if(p != null && !p.equals(""))
			page = Integer.parseInt(p);
		
		List<NoticeView> list = noticeDao.getList();
		
		model.addAttribute("list", list);
		
		System.out.println("list 요청");
		
		return "notice/list";
		
	}
	*/
}