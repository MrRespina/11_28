package com.ji.project001;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SelectMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SqlSession ss = null;
		
		try {
			
			InputStream is = Resources.getResourceAsStream("asdf.xml");
			SqlSessionFactoryBuilder ssfb = new SqlSessionFactoryBuilder();
			SqlSessionFactory ssf = ssfb.build(is);
			ss = ssf.openSession();
			
			// 값 받아오기
			List<Fruit> fruits = ss.selectList("fruits.getAllFruits");
			
			for (Fruit f : fruits) {
				
				System.out.println(f.getF_name());
				System.out.println(f.getF_price());
				System.out.println("==========");
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		ss.close();
		
	}

}
