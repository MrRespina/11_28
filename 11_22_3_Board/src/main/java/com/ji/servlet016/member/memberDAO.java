package com.ji.servlet016.member;

import java.io.File;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ji.servlet013.dbmanager.JiDBManager;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class memberDAO {
	
	public static boolean loginCheck(HttpServletRequest req) {
	
		// 로그인된 상태면 Member 자바빈이 채워질 것.
		Member m = (Member) req.getSession().getAttribute("loginMember");
		if(m!=null) {
			//성공
			req.setAttribute("log", "member/welcome.jsp");
			return true;
		}else {
			req.setAttribute("log", "member/login.jsp");
			return false;
		}
			
	}
	
	public static void signUp(HttpServletRequest req) {
		
		// 파일 업로드 부분을 일단 먼저 시도!
		String path = null;
		// 사진 넣을 때 : MultipartRequest 필요 > cos.jar
		MultipartRequest mr = null;
		
		try {
			
			path = req.getServletContext().getRealPath("image");	// webapp의 img 폴더
			System.out.println(path);
			mr = new MultipartRequest(req, path, 20*1024*1024,"UTF-8",new DefaultFileRenamePolicy());
			
		} catch (Exception e) {	// 업로드 실패
			
			e.printStackTrace();
			req.setAttribute("r", "회원 가입 실패(사진파일용량)");
			return;	// 사진 업로드에 실패하면 아래 작성할 DB 작업 실행 X(강제종료)
			
		}
		
		// catch에 안걸림 > 파일 업로드 성공
		// 성공 시에는 계속 진행 (insert)
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			//	JSP Model 2 DB 연결
			//		META-INF		: context.xml
			//		WEB-INF	> lib	: ojdbc8.jar , DB Manager(만들어놓은);
			
			conn = JiDBManager.connect("jiPool");
						
			String id = mr.getParameter("m_id");	// input 의 name
			String pw = mr.getParameter("m_pw");
			String name = mr.getParameter("m_name");
			String phone = mr.getParameter("m_phone");
			
			String y = mr.getParameter("m_y");
			int m = Integer.parseInt(mr.getParameter("m_m"));
			int d = Integer.parseInt(mr.getParameter("m_d"));
			String birthday = String.format("%s%02d%02d", y,m,d);
			
			String photo = mr.getFilesystemName("m_photo");
			photo = URLEncoder.encode(photo,"UTF-8").replace("+", " ");
			
			String sql = "INSERT INTO nov22_member VALUES(?,?,?,?,to_date(?,'YYYYMMDD'),?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.setString(3, name);
			pstmt.setString(4, phone);
			pstmt.setString(5, birthday);
			pstmt.setString(6, photo);
			
			if(pstmt.executeUpdate()==1) {
				
				req.setAttribute("r", "회원가입 성공");
				
			}
			
			
		} catch (Exception e) {
			// DB 문제로 회원가입 실패시, 사진 파일은 업로드가 되어있는 상태임.
			// 사진 파일을 지워야함.(*파일명은 한글처리가 되어있지 않아야 함)
			e.printStackTrace();
			File f = new File(path + "/"+mr.getFilesystemName("m_photo"));
			f.delete();
			req.setAttribute("r", "회원가입 실패 - DB 문제");
		} finally {
			JiDBManager.close(conn, pstmt, null);
		}
		
	}
	
	public static void login(HttpServletRequest req,HttpServletResponse res) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			String sql = "SELECT * FROM nov22_member WHERE m_id=?";
			conn = JiDBManager.connect("jiPool");
			req.setCharacterEncoding("UTF-8");
			
			// login.jsp의 id,pw는 l_id,l_pw로 사용했으므로 그것을 받아옴.
			String id = req.getParameter("l_id");
			String pw = req.getParameter("l_pw");
			
			// SELECT * FROm nov22_member; // 회원이 1000명이면 그거 다~
			// SELECT * FROm nov22_member WHERE m_id=? and m_pw=?; // SQL Injection으로 공격받을 수 있음.(보안상 취약점을 이용해 Database가 비정상적인 동작을 하게함)

			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				String dbPw = rs.getString("m_pw");
				if(dbPw.equals(pw)) {
					
					Member m = new Member(rs.getString("m_id"),dbPw,rs.getString("m_name")
							,rs.getString("m_phone"),rs.getDate("m_birth"),rs.getString("m_photo"));
					// loginMemeber 라는 세션 만들어서 담기 > loginCheck method에서 get으로 사용 중
					req.getSession().setAttribute("loginMember",m);
					req.getSession().setMaxInactiveInterval(600);
					
					Cookie c = new Cookie("lastLoginId",id);
					c.setMaxAge(60*60*24);
					
					res.addCookie(c);
					req.setAttribute("r", "로그인 성공");
					
				} else {
					
					req.setAttribute("r", "로그인 실패 - PW오류");
					
				}
				
			} else {
				
				req.setAttribute("r", "로그인 실패 - 미가입ID");
				
			}
			
		}catch (Exception e) {
			
			e.printStackTrace();
			req.setAttribute("r", "로그인 실패 - DB서버");
			
		} finally {
			
			JiDBManager.close(conn, pstmt, rs);
			
		}
		
		
	}

	public static void logout(HttpServletRequest req) {
		// 세션 끊기 : 다른 세션도 같이 끊겨서 비추.
		// req.getSession().setMaxInactiveInterval(-1);
		
		// loginMember에 대한 session만 null 처리 해야함.
		req.getSession().setAttribute("loginMember", null);
		
	}
	
	public static void delete(HttpServletRequest req) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM NOV22_MEMBER WHERE m_id=?";
		
		try {
			
			conn = JiDBManager.connect("jiPool");
			
			Member m = (Member) req.getSession().getAttribute("loginMember");
			// id만 가져오는 이유 : 삭제를 PK(m_id) 찾아서 할 거기 때문.
			String m_id = m.getM_id();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m_id);
			
			if(pstmt.executeUpdate()==1) {
				
				req.setAttribute("r", "탈퇴 성공");
				String m_photo = m.getM_photo();
				m_photo = URLDecoder.decode(m_photo,"UTF-8");
				String path = req.getServletContext().getRealPath("image");
				File f = new File(path + "/" + m_photo);
				f.delete();
				
			} else {
				
				req.setAttribute("r", "탈퇴 실패 - 없는 아이디");
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			req.setAttribute("r", "탈퇴 실패 - DB 오류");
			
		} finally {
			
			JiDBManager.close(conn, pstmt, null);
			
		}
		
	}

	public static void update(HttpServletRequest req) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		MultipartRequest mr = null;
		
		try {
			
			conn = JiDBManager.connect("jiPool");			
			Member mem = (Member) req.getSession().getAttribute("loginMember");
			
			// MultipartRequest에서 사용할 path,파일 정보 설정
			try {
				
				String path = req.getServletContext().getRealPath("image");
				System.out.println(path);
				mr = new MultipartRequest(req, path, 20*1024*1024,"UTF-8");
				
			} catch (Exception e) {		
				
				e.printStackTrace();
				req.setAttribute("r", "사진 용량 초과");
				return;
				
			}
			
			// 수정에 필요한 것들 미리 찾아오기. (현재 세션 내에 있는 m_id,m_photo)
			String id = mem.getM_id();
			String old_photo = mem.getM_photo();
			
			// 인트로 받아서 String 포맷으로 변환해줘야 2월 > 02월 식으로 출력이 가능. String형으로 하면 불가하다.
			int y = Integer.parseInt(mr.getParameter("m_y"));
			int m = Integer.parseInt(mr.getParameter("m_m"));
			int d = Integer.parseInt(mr.getParameter("m_d"));
			
			String birth = String.format("%04d%02d%02d", y,m,d);
			String new_photo = mr.getFilesystemName("m_photo");		
			new_photo = URLEncoder.encode(new_photo,"UTF-8").replace("+", " ");
			
			// 폼에서 입력받은 값으로 다시 넣어주기
			String sql = "UPDATE nov22_member set m_pw=?,m_name=?,m_phone=?,m_birth=to_date(?,'YYYYMMDD'),m_photo=? WHERE m_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mr.getParameter("m_pw"));
			pstmt.setString(2, mr.getParameter("m_name"));
			pstmt.setString(3, mr.getParameter("m_phone"));	
			pstmt.setString(4, birth);
			pstmt.setString(5, new_photo);
			pstmt.setString(6, id);
			
			if(pstmt.executeUpdate() == 1) {
				
				// 만약에 똑같은 사진을 넣었을 때 ...
				if(mr.getFilesystemName("m_photo").equals(mem.getM_photo())) {
					
					// 그냥 성공이라고 하고 원래 있던 사진은 내버려둠.(삭제하지 않음.)
					req.setAttribute("r", "수정 성공.(같은 사진)");
					
				} else {
					
					// 다른 사진을 넣고 성공 시, 예전에 있던 사진을 삭제해준다. (위에서 미리 이전의 m_photo를 빼놓았다.)
					String m_photo = mem.getM_photo();
					m_photo = URLDecoder.decode(m_photo,"UTF-8");
					String path = req.getServletContext().getRealPath("image");
					File f = new File(path + "/" + old_photo);
					f.delete();
					req.setAttribute("r", "수정 성공!");
					
				}
				
				
				
			}else {
				
				req.setAttribute("r", "수정 실패 - ID오류");
				
			}
			
		}catch (Exception e) {
			
			e.printStackTrace();
			req.setAttribute("r", "수정 실패 - DB오류");
			
		} finally {
			
			JiDBManager.close(conn, pstmt, null);
			
		}
		
	}
	
}
