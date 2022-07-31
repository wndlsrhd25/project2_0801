package com.dev.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.service.MemberService;

public class CheckIdAjaxController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/json;charset=utf-8");
		
		String id = req.getParameter("id");
		
		MemberService service = MemberService.getInstance();
		boolean isCheckedId = service.doubleIdCheck(id);
		
		//{"retCode":"Success"}
				try {
					if(isCheckedId) {
						//중복o
					resp.getWriter().print("{\"retCode\":\"Success\"}");
					}else {
						//중복x
					resp.getWriter().print("{\"retCode\":\"Fail\"}");
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
		
		
	}

}
