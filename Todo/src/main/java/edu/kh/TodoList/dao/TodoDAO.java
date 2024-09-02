package edu.kh.TodoList.dao;

import static edu.kh.TodoList.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Properties;

import edu.kh.TodoList.common.JDBCTemplate;
import edu.kh.TodoList.dto.Todo;

public class TodoDAO {

	Connection conn = null;
	PreparedStatement pstmt = null;
	Statement stmt = null;
	ResultSet rs = null;
	Properties prop;

	public int selectCount() throws SQLException, InvalidPropertiesFormatException, FileNotFoundException, IOException {

		String filePath = JDBCTemplate.class.getResource("/edu/kh/TodoList/sql.xml").getPath();

		prop = new Properties();
		prop.loadFromXML(new FileInputStream(filePath));

		conn = getConnection();

		String sql = prop.getProperty("selectCount");

		stmt = conn.createStatement();

		rs = stmt.executeQuery(sql);

		int result = 0;
		if (rs.next()) {
			result = rs.getInt("MAX(TODO_NO)");
		}

		System.out.println(result);

		rs.close();
		stmt.close();
		conn.close();

		return result + 1;
	}

	public List<Todo> allview()
			throws SQLException, InvalidPropertiesFormatException, FileNotFoundException, IOException {

		List<Todo> todoList = new ArrayList<Todo>();

		try {

			String filePath = JDBCTemplate.class.getResource("/edu/kh/TodoList/sql.xml").getPath();

			prop = new Properties();
			prop.loadFromXML(new FileInputStream(filePath));

			String sql = prop.getProperty("allview");

			conn = getConnection();

			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			while (rs.next()) {

				int todoNo = rs.getInt("TODO_NO");
				String todoTitle = rs.getString("TODO_TITLE");
				String todoCheck = rs.getString("TODO_CHECK");
				String todoDate = rs.getString("TODO_DATE");
				String detail = rs.getString("DETAIL");

				Todo todo = new Todo(todoNo, todoTitle, todoCheck, todoDate, detail);
				todoList.add(todo);

			}
		} finally {
			close(rs);
			close(stmt);
			close(conn);
		}
		return todoList;
	}

	public int addUpdate(String todoTitle, String detail)
			throws SQLException, InvalidPropertiesFormatException, FileNotFoundException, IOException {

		int result = 0;

		try {

			String filePath = JDBCTemplate.class.getResource("/edu/kh/TodoList/sql.xml").getPath();

			prop = new Properties();
			prop.loadFromXML(new FileInputStream(filePath));

			String sql = prop.getProperty("update");

			int count = selectCount();

			System.out.println(count);

			conn = getConnection();

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, count);
			pstmt.setString(2, todoTitle);
			pstmt.setString(3, detail);

			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
			close(conn);
		}
		return result;

	}

	public int addDelete(int deleteNo) {

		int result = 0;
		
		try {
			
			String filePath = JDBCTemplate.class.getResource("/edu/kh/TodoList/sql.xml").getPath();

			prop = new Properties();
			prop.loadFromXML(new FileInputStream(filePath));

			String sql = prop.getProperty("delete");
			
			conn = getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, deleteNo);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(conn);
		}
		
		return result;
	}

	public Todo selectTodo(String index) {

		Todo selectTodo = null;
		
		try {
			
			String filePath = JDBCTemplate.class.getResource("/edu/kh/TodoList/sql.xml").getPath();

			prop = new Properties();
			prop.loadFromXML(new FileInputStream(filePath));

			String sql = prop.getProperty("select");
			
			conn = getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, index);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				int todoNo = rs.getInt("TODO_NO");
				String todoTitle = rs.getString("TODO_TITLE");
				String todoCheck = rs.getString("TODO_CHECK");
				String todoDate = rs.getString("TODO_DATE");
				String detail = rs.getString("DETAIL");
				
				selectTodo = new Todo(todoNo, todoTitle, todoCheck, todoDate, detail);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(conn);
		}
		
		return selectTodo;
	}

	public int complate(String index) {

		int result = 0;
		
		try {
			
			String filePath = JDBCTemplate.class.getResource("/edu/kh/TodoList/sql.xml").getPath();

			prop = new Properties();
			prop.loadFromXML(new FileInputStream(filePath));

			Todo todo = selectTodo(index);
			
			String sql = "";
			if(todo.getTodoComplate().equals("O")) {
				sql = prop.getProperty("complateO");
			}
			
			else if(todo.getTodoComplate().equals("X")) {
				sql = prop.getProperty("complateX");
			}
			else sql = prop.getProperty("complateX");
			conn = getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, index);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(conn);
		}
		
		return result;
	}

	public int detailUpdate(String index) {

		int result = 0;
		
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
