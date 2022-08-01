package com.dev.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.service.MemberService;
import com.dev.vo.MemberVO;

public class MemberInsertController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
	      // 입력. -> 뷰페이지
	      MemberService service = MemberService.getInstance();

	      String id = req.getParameter("id");
	      String password = req.getParameter("password");
	      String name = req.getParameter("name");
	      
	      String year = req.getParameter("year");
	      String month =req.getParameter("month");
	      String day = req.getParameter("day");
	      String birth = year+"/"+month+"/"+day;
	      
	      String gender = req.getParameter("userGender");
	      
	      String emailId = req.getParameter("email_id");
	      String emailDomain = req.getParameter("email_domain");
	      String email = emailId+"@"+emailDomain;
	      
	      String firstPhone = req.getParameter("firstPhone");
	      String secondPhone = req.getParameter("secondPhone");
	      String lastPhone = req.getParameter("lastPhone");
	      String phone = firstPhone+"-"+secondPhone+"-"+lastPhone;
	      
	      
	      MemberVO vo = new MemberVO();
	      vo.setId(id);
	      vo.setPassword(password);
	      vo.setName(name);
	      vo.setBirth(birth);
	      vo.setGender(gender);
	      vo.setEmail(email);
	      vo.setPhone(phone);
	      service.addMember(vo);

	      // 요청처리 결과 뷰 페이지 전송. vo를 memeber라는 값에 넣음
	      req.setAttribute("member", vo);


	      // 위에 대신에 Utils 클래스만든걸로 사용함
	      //일단은 인덱스로 가게 해둠
	      Utils.forward(req, resp, "../index.jsp");
	   }

	}
