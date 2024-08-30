package edu.kh.jdbc.controller;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;

import edu.kh.jdbc.dto.User;
import edu.kh.jdbc.service.UserService;
import edu.kh.jdbc.service.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/selectAll")
public class selectAllServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
			
		UserService service = new UserServiceImpl();
		try {
			List<User> userlist = service.select();
			
			req.setAttribute("user", userlist);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		String path = "/WEB-INF/views/selectAll.jsp";
		req.getRequestDispatcher(path).forward(req, resp);

	}
}
