package com.ji.project001;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MainController {

	public static Scanner s = new Scanner(System.in);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SqlSession ss = createSession();
		boolean b = true;

		while (b == true) {

			System.out.println("==========");
			System.out.println("1. 모든 DB정보 확인");
			System.out.println("2. DB에 입력");
			System.out.println("3. DB에서 삭제");
			System.out.println("4. 종료");
			System.out.print("입력 : ");
			String menu = s.nextLine();
			switch (menu) {
			case "1":
				selectDB(ss);
				break;
			case "2":
				insertDB(ss);
				break;
			case "3":
				deleteDB(ss);
				break;
			case "4":
				s.close();
				b = false;
				break;
			}

		}

	}

	public static SqlSession createSession() {

		// conn + pstmt + rs = SqlSession
		SqlSession ss = null;
		try {

			InputStream is = Resources.getResourceAsStream("asdf.xml");
			SqlSessionFactoryBuilder ssfb = new SqlSessionFactoryBuilder();
			SqlSessionFactory ssf = ssfb.build(is);
			ss = ssf.openSession();
			System.out.println("세션 생성 성공");
			return ss;

		} catch (Exception e) {

			System.out.println("세션 생성 실패");
			e.printStackTrace();
			return null;

		}

	}

	public static void selectDB(SqlSession ss) {

		List<Fruit> fruits = ss.selectList("fruits.getAllFruits");
		System.out.println("==========");
		for (Fruit f : fruits) {

			System.out.println(f.getF_name());
			System.out.println(f.getF_price());
			System.out.println("==========");

		}
		System.out.println();
	}

	public static void insertDB(SqlSession ss) {

		System.out.print("과일 이름 : ");
		String n = s.nextLine();

		System.out.print("가격 : ");
		BigDecimal b = s.nextBigDecimal();
		
		Fruit f = new Fruit(n, b);

		if (ss.insert("fruits.insertFruits", f) == 1) {
			System.out.println(n + " 입력 성공");
			s.reset();
			ss.commit();
		} else {
			System.out.println(n + " 입력 실패");
		}
		System.out.println("==========");

	}
	
	public static void deleteDB(SqlSession ss) {

		System.out.print("삭제할 과일 이름 : ");
		String n = s.nextLine();
		
		Fruit f = new Fruit();
		f.setF_name(n);

		if (ss.delete("fruits.deleteFruits",f)==1) {
			System.out.println(n+" 삭제 성공");
			s.reset();
			ss.commit();
		} else {
			System.out.println(n+" 삭제 실패");
		}
		System.out.println("==========");
		
	}

}
