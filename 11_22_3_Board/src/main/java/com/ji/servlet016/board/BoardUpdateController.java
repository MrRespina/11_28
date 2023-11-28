package com.ji.servlet016.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ji.servlet016.member.memberDAO;


@WebServlet("/BoardUpdateController")
public class BoardUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		memberDAO.loginCheck(request);	
		request.setAttribute("b_no", request.getParameter("b_no"));
		request.setAttribute("b_text", request.getParameter("b_text"));		
		request.setAttribute("content", "board/update.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		memberDAO.loginCheck(request);	
		BoardDAO.getBdao().updateBoard(request);
		TokenManager.make(request);
		BoardDAO.getBdao().clearSearch(request);
		BoardDAO.getBdao().getBoardMsg(1, request);
		
		request.setAttribute("content", "board/board.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
	}
	

}
