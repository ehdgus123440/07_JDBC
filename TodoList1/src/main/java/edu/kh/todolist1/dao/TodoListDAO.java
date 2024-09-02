package edu.kh.todolist1.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.todolist1.common.JDBCTemplate;
import edu.kh.todolist1.dto.Todo;

public class TodoListDAO {
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	private Properties prop;
	
	public TodoListDAO() {

		try {
			
			String filePath = 
					JDBCTemplate.class
					.getResource("/edu/kh/todolist1/sql.xml").getPath();
			
			// 지정된 경로의 XML파일 내용을 읽어와
			// Properties 객체에 K:V 세팅
			prop = new Properties();
			prop.loadFromXML(new FileInputStream(filePath));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	public List<Todo> getTodolist() throws SQLException {

		List<Todo> TodoList = null;
		
		String sql = prop.getProperty("allview");
		
		conn = JDBCTemplate.getConnection();
		
		stmt = conn.createStatement();
		
		rs = stmt.executeQuery(sql);
		
		while(rs.next()) {
			
			String todoNo = rs.getString("TODO_NO");
			String title = rs.getString("TODO_TITLE");
			String complate = rs.getString("TODO_CHECK");
			String date = rs.getString("TODO_DATE");
			String detail = rs.getString("DETAIL");
			
			Todo todo = new Todo(todoNo, title, complate, date, detail);
			
			TodoList.add(todo);
			
		}
		
		
		return TodoList;
	}
	
	
	
	
}
