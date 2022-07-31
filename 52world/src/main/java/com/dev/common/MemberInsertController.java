package com.dev.common;

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
		String pw = req.getParameter("password");
		String nm = req.getParameter("name");
		String ml = req.getParameter("email");

		MemberVO vo = new MemberVO();
		vo.setId(id);
		vo.setName(nm);
		vo.setPassword(pw);
		vo.setEmail(ml);

		service.addMember(vo);

		// 요청처리 결과 뷰 페이지 전송. vo를 memeber라는 값에 넣음
		req.setAttribute("member", vo);


		// 위에 대신에 Utils 클래스만든걸로 사용함
		//일단은 인덱스로 가게 해둠
		Utils.forward(req, resp, "../index.jsp");
	}

}
