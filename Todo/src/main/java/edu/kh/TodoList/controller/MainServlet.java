package edu.kh.TodoList.controller;

import java.io.IOException;
import java.util.List;

import edu.kh.TodoList.dao.TodoDAO;
import edu.kh.TodoList.dto.Todo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/main")
public class MainServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		TodoDAO dao = new TodoDAO();

		try {
			
			List<Todo> todoList = dao.allview();
			
			req.setAttribute("todoList", todoList);

			Todo test = todoList.get(0);
			
			System.out.println(test);
			
			String path = "/WEB-INF/views/main.jsp";
			req.getRequestDispatcher(path).forward(req, resp);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
