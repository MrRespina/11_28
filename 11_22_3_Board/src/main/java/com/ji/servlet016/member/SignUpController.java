package com.ji.servlet016.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ji.servlet016.main.DateManager;


@WebServlet("/SignUpController")
public class SignUpController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		memberDAO.loginCheck(request);
		DateManager.getCurYear(request);
		request.setAttribute("content", "member/signUp.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		memberDAO.signUp(request);
		memberDAO.loginCheck(request);
		request.setAttribute("content", "home.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
	}

}
