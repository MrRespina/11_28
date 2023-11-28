package com.ji.project001;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DeleteMain {

	public static void main(String[] args) {

		SqlSession ss = null;

		try {

			InputStream is = Resources.getResourceAsStream("asdf.xml");
			SqlSessionFactoryBuilder ssfb = new SqlSessionFactoryBuilder();
			SqlSessionFactory ssf = ssfb.build(is);
			ss = ssf.openSession();

			// 값 넣기
			Scanner k = new Scanner(System.in);
			System.out.print("삭제할 과일 이름 : ");
			String n = k.nextLine();
			
			Fruit f = new Fruit();
			f.setF_name(n);

			if (ss.delete("fruits.deleteFruits",f)==1) {
				System.out.println(n+" 삭제 성공");
				ss.commit();
			} else {
				System.out.println("삭제 실패");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		ss.close();

	}

}
