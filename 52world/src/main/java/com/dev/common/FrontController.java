package com.dev.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





public class FrontController extends HttpServlet {

	String enc;

	Map<String, Controller> mappings;

	@Override
	public void init(ServletConfig config) throws ServletException {

		enc = config.getInitParameter("charset");
		// 키와 벨류 형식으로 넣음
		mappings = new HashMap<>();
		mappings.put("/memberInsert.do", new MemberInsertController());
	
		//ajax
		mappings.put("/checkIdAjax.do",new CheckIdAjaxController());
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding(enc);

		String uri = req.getRequestURI();
//		System.out.println(uri); //   /mvcProj/memberList.do

		String contextPath = req.getContextPath();
//		System.out.println(contextPath); //     /mvcProj

		String path = uri.substring(contextPath.length());
		System.out.println(path); // /memberList.do

		/*
		 * key Insert.do List.do value InController LiController
		 * 
		 * mappings.get에서 키값(ex /memberList.do)이 들어가면 거기에 따른 value(LiController)가 cntr로
		 * 들어감 그것의 execute의 값들이 실행됨
		 */

		Controller cntrl = mappings.get(path);
		cntrl.execute(req, resp);

	}
}
