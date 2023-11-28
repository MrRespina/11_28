package com.ji.servlet016.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ji.servlet016.member.MemberDeleteController;
import com.ji.servlet016.member.memberDAO;


@WebServlet("/BoardPageController")
public class BoardPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		memberDAO.loginCheck(request);
		int p = Integer.parseInt(request.getParameter("p"));
		BoardDAO.getBdao().getBoardMsg(p, request);
		request.setAttribute("content", "board/board.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
