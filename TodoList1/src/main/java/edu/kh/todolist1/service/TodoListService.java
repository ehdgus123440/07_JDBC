package edu.kh.todolist1.service;

import java.sql.Connection;
import java.util.List;

import edu.kh.todolist1.common.JDBCTemplate;
import edu.kh.todolist1.dao.TodoListDAO;
import edu.kh.todolist1.dto.Todo;

public class TodoListService {

	TodoListDAO dao = null;
	
	public TodoListService() {
	
		dao = new TodoListDAO();
	}	
	
	public List<Todo> allview() throws Exception{
		
		List<Todo> TodoList = dao.getTodolist();
		
		
		
		
		return TodoList;
	}

}
