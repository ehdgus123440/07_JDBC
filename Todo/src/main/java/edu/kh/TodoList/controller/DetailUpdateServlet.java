package edu.kh.TodoList.controller;

import java.io.IOException;

import edu.kh.TodoList.dao.TodoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/detailUpdate")
public class DetailUpdateServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String index = req.getParameter("todoNo");
		System.out.println(index);
	
		TodoDAO dao = new TodoDAO();
		
		int result = dao.detailUpdate(index);
		
		resp.sendRedirect("/todo/detail?todoNo="+index);
	
	}
}
