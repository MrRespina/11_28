package com.ji.project001;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

// 							DB서버 연결					SQL										SQL명령문
//			JAVA			JDBC							.java										.java
//			JSP			ConnectionPool				META-INF/context.mxl		.java
//			Spring			MyBatis						asdf.xml								fdsa.xml

//		MyBatis(ver3.x : MyBatis, ver2.x : iBatis)
//		DB ORM(Object Relationship Mapping) framework : DB 데이터를 POJO를 통해 자동 매핑

//	mybatis.jar
//	ojdbc.jar

public class ConnectionMain {
	
	public static void main(String[] args) {
		
		// conn + pstmt + rs = SqlSession
		SqlSession  ss = null;
		try {
			
			InputStream is = Resources.getResourceAsStream("asdf.xml");
			SqlSessionFactoryBuilder ssfb = new SqlSessionFactoryBuilder();
			SqlSessionFactory ssf = ssfb.build(is);
			ss = ssf.openSession();
			System.out.println("성공했니?");
			
		}catch (Exception e) {
			
			e.printStackTrace();
			
		}
		ss.close();
		
	}
	
}
