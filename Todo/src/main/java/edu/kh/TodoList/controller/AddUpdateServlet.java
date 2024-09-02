package edu.kh.TodoList.controller;

import java.io.IOException;
import java.sql.SQLException;

import edu.kh.TodoList.dao.TodoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/addUpdate")
public class AddUpdateServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String TodoTitle = req.getParameter("todoTitle");
		String detail = req.getParameter("detail");
		
		System.out.println(TodoTitle + detail);
		
		TodoDAO dao = new TodoDAO();
		
		try {
			int result = dao.addUpdate(TodoTitle, detail);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		resp.sendRedirect("/");
	
	}
}
