package edu.kh.todolist1.controller;

import java.io.IOException;
import java.util.List;

import edu.kh.todolist1.dto.Todo;
import edu.kh.todolist1.service.TodoListService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// "/main" 요청을 매핑하여 처리하는 서블릿
@WebServlet("/main")
public class MainServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			
			TodoListService service = new TodoListService();
			
			List<Todo> todoList = service.allview();
			
			// request scope 객체 값을 추가
			req.setAttribute("todoList", todoList);
			
			
			// 메인 페이지 응답을 담당하는 jsp에 요청 위임
			String path = "/WEB-INF/views/main.jsp";
			req.getRequestDispatcher(path).forward(req, resp);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}