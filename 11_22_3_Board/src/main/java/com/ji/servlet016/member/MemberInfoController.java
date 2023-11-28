package com.ji.servlet016.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ji.servlet016.main.DateManager;


@WebServlet("/MemberInfoController")
public class MemberInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(memberDAO.loginCheck(request)) {
			
			DateManager.getCurYear(request);
			request.setAttribute("r", "내 정보 창");
			request.setAttribute("content", "member/memberInfo.jsp");
			
		} else {
			
			request.setAttribute("r", "로그인 만료됨!");
			request.setAttribute("content", "home.jsp");
			
		}
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
	}

	// 상세정보 수정
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(memberDAO.loginCheck(request)) {
			memberDAO.update(request);
			memberDAO.logout(request);	
			memberDAO.loginCheck(request);
		}	
		request.setAttribute("content", "home.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
		
	}

}
