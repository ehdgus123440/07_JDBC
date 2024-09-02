package edu.kh.TodoList.controller;

import java.io.IOException;

import edu.kh.TodoList.dao.TodoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/addDelete")
public class AddDeleteServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int deleteNo = Integer.parseInt(req.getParameter("deleteNo"));
		
		TodoDAO dao = new TodoDAO();
		
		int result = dao.addDelete(deleteNo);
		
		resp.sendRedirect("/");
	
	}
	
}
