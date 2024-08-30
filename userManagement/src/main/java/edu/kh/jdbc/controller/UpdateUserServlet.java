package edu.kh.jdbc.controller;

import java.io.IOException;

import edu.kh.jdbc.dto.User;
import edu.kh.jdbc.service.UserService;
import edu.kh.jdbc.service.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/updateUser")
public class UpdateUserServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			String pw = req.getParameter("userPw");
			String name = req.getParameter("userName");
			int userNo = Integer.parseInt(req.getParameter("userNo"));
			
			User user = new User();
			user.setUserPw(pw);
			user.setUserName(name);
			user.setUserNo(userNo);
			
			// 서비스 호출 후 결과 반환
			UserService service = new UserServiceImpl();
			int result = service.updateUser(user);
			
			// 결과에 따라 메시지 지정
			String message = null;
			if(result > 0) message = "수정 성공";
			else           message = "수정 실패";
			
			req.getSession().setAttribute("message", message);
			
			// 사용자 상세 정보 조회 페이지로 리다이렉트
			resp.sendRedirect("/selectUser?userNo=" + user.getUserNo());
			
		} catch (Exception e) {
			e.printStackTrace();
		
		}
	
	
	}
	
}
