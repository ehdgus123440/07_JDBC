package edu.kh.TodoList.controller;

import java.io.IOException;

import edu.kh.TodoList.dao.TodoDAO;
import edu.kh.TodoList.dto.Todo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/todo/detail")
public class TodoDetailServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String index = req.getParameter("todoNo");
		
		TodoDAO dao = new TodoDAO();
		
		Todo selectTodo = dao.selectTodo(index);
		
		req.setAttribute("Todo", selectTodo);
		
		String path = "/WEB-INF/views/detail.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
	
	}

}
