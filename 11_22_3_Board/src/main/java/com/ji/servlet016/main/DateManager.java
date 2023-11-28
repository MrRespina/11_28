package com.ji.servlet016.main;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

// 여기저기서 날짜를 많이 다룰수도 있기 떄문에 main 패키지 안에 배치
public class DateManager {
	
	// 올해 날짜의 년도
	// signUp.jsp의 select 년도 부분에서 해마다 바꾸지 않고 여기서 받아올 것
	public static void getCurYear(HttpServletRequest req) {
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String curYear = sdf.format(now);
		req.setAttribute("cy", curYear);
	}

}
