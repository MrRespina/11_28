package com.ji.project001;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Scanner;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class InsertMain {
	
	public static void main(String[] args) {
		
SqlSession ss = null;
		
		try {
			
			InputStream is = Resources.getResourceAsStream("asdf.xml");
			SqlSessionFactoryBuilder ssfb = new SqlSessionFactoryBuilder();
			SqlSessionFactory ssf = ssfb.build(is);
			ss = ssf.openSession();
			
			// 값 넣기
			Scanner k = new Scanner(System.in);
			System.out.print("과일 이름 : ");
			String n = k.nextLine();
			
			System.out.print("가격 : ");
			BigDecimal b = k.nextBigDecimal();
			
			Fruit f = new Fruit(n,b);
			
			if(ss.insert("fruits.insertFruits",f) ==1) {
				System.out.println("입력 성공");
				ss.commit();
			}else {
				System.out.println("입력 실패");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		ss.close();
		
	}
	
}
