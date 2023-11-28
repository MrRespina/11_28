package com.ji.servlet016.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.ji.servlet013.dbmanager.JiDBManager;
import com.ji.servlet016.member.Member;

public class BoardDAO {

	private int allBoardCount;

	private static final BoardDAO BDAO = new BoardDAO();

	public BoardDAO() {
	}

	public static BoardDAO getBdao() {
		return BDAO;
	}

	public void countAllBoard() {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM nov27_board";

		try {

			conn = JiDBManager.connect("jiPool");
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			rs.next();
			allBoardCount = rs.getInt("count(*)"); // 멤버 변수에 해당 전체 게시글 수를 담음.
			System.out.println(allBoardCount); // 체크용

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			JiDBManager.close(conn, pstmt, rs);

		}

	}

	// 검색어 입력 > 해당하는 게시글 갯수 가져오기
	private int countSearchMsg(String search) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			conn = JiDBManager.connect("jiPool");
			String sql = "SELECT COUNT(*) FROM nov22_member,nov27_board WHERE b_writer = m_id and b_text like '%'||?||'%'";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, search);

			rs = pstmt.executeQuery();
			rs.next();

			return rs.getInt("count(*)");

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {

			JiDBManager.close(conn, pstmt, rs);

		}

	}

	// 검색어 값 가져오기, 세션에 등록
	public void searchBoardMsg(HttpServletRequest req) {
		// 새로운 요청이 일어났을 때(페이지 전환) 에도 검색어가 살아있어야 됨.
		String search = req.getParameter("search"); // <input name="search">
		req.getSession().setAttribute("search", search);

	}

	// 게시판에 처음 접근 or 검색어 입력 안 했을 때
	public void clearSearch(HttpServletRequest req) {
		req.getSession().setAttribute("search", null);
	}

	//
	public void getBoardMsg(int pageNo, HttpServletRequest req) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			conn = JiDBManager.connect("jiPool");

			int boardCount = allBoardCount; // 게시글 전체개수
			String search = (String) req.getSession().getAttribute("search"); // 검색어 가져오기

			if (search == null) { // 검색어 없음 > 게시글 전체 조회

				search = "";

			} else {

				boardCount = countSearchMsg(search); // 위 메소드 가져옴.

			}

			int boardPerPage = 3;
			int pageCount = (int) Math.ceil(boardCount / (double) boardPerPage); // 소수점 올림
			req.setAttribute("pageCount", pageCount);
			req.setAttribute("pageNo", pageNo);

			// 페이지별 첫번째, 마지막 게시글
			int start = boardPerPage * (pageNo - 1) + 1;
			int end = (pageNo == pageCount) ? boardCount : (start + boardPerPage - 1);

			String sql = "SELECT * FROM " + "(SELECT rownum as rn, b_no,b_writer,b_when,b_text,m_photo FROM "
					+ "nov22_member, nov27_board WHERE m_id = b_writer and b_text like '%'||?||'%' ORDER BY b_when DESC) WHERE rn >=? and rn <=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, search);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);

			rs = pstmt.executeQuery();
			ArrayList<Board> bdal = new ArrayList<Board>();
			Board board = null;

			while (rs.next()) {
				board = new Board(rs.getInt("b_no"), rs.getString("b_writer"), rs.getDate("b_when"),
						rs.getString("b_text"), rs.getString("m_photo"));
				bdal.add(board);

			}
			req.setAttribute("boards", bdal); // Attribute에 추가해서 보내줌.

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JiDBManager.close(conn, pstmt, rs);
		}

	}

	public void insertBoard(HttpServletRequest req) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			req.setCharacterEncoding("UTF-8");
			conn = JiDBManager.connect("jiPool");
			String sql = "INSERT INTO nov27_board VALUES(nov27_board_seq.nextval,?,sysdate,?)";
			Member m = (Member) req.getSession().getAttribute("loginMember");

			String b_writer = m.getM_id();
			String b_text = req.getParameter("b_text").replace("\r\n", "<br>");

			/*
			 * String formerToken = (String) req.getSession().getAttribute("formerToken");
			 * System.out.println("formerToken : " + formerToken);
			 * 
			 * String token = req.getParameter("token"); // board.jsp의 요청
			 * System.out.println("token : " + token);
			 */

		//	if (!token.equals(formerToken)) {

				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, b_writer);
				pstmt.setString(2, b_text);

				if (pstmt.executeUpdate() == 1) {
					req.setAttribute("r", "글쓰기 성공!");
					//req.getSession().setAttribute("formerToken", token);
					allBoardCount++;
				} else {
					req.setAttribute("r", "글쓰기 실패,DB에러");
				}

				/*
				 * } else { req.setAttribute("r", "새로고침 문제!"); }
				 */

		} catch (Exception e) {

			req.setAttribute("r", "글쓰기 실패,DB연결");

		} finally {

			JiDBManager.close(conn, pstmt, null);

		}

	}
	
	public void deleteBoard(HttpServletRequest req) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = JiDBManager.connect("jiPool");
			
			int no = Integer.parseInt(req.getParameter("b_no"));		
			
			String sql = "DELETE FROM nov27_board WHERE b_no=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			if(pstmt.executeUpdate()==1) {
				req.setAttribute("r", "삭제 성공");
				allBoardCount--;
			} else {
				req.setAttribute("r", "삭제 실패");
			}		
			
		}catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("r", "삭제 실패/DB에러");
		} finally {
			JiDBManager.close(conn, pstmt, null);
		}
		
	}

	public void updateBoard(HttpServletRequest req) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = JiDBManager.connect("jiPool");
			req.setCharacterEncoding("UTF-8");
			
			int no = Integer.parseInt(req.getParameter("b_no"));
			String txt = req.getParameter("b_text");
			
			String sql = "UPDATE nov27_board SET B_WHEN = sysdate,B_TEXT = ? WHERE b_no = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, txt);
			pstmt.setInt(2, no);
			
			if(pstmt.executeUpdate()==1) {
				req.setAttribute("r", "수정 성공");
			} else {
				req.setAttribute("r", "수정 실패");
			}		
			
		}catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("r", "수정 실패/DB에러");
		} finally {
			JiDBManager.close(conn, pstmt, null);
		}
		
	}
}
