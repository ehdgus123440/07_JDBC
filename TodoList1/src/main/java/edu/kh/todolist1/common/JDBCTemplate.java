package edu.kh.todolist1.common;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import edu.kh.todolist1.common.JDBCTemplate;

public class JDBCTemplate {
	
	private static Connection conn = null;

	public static Connection getConnection() {
		
		try {
			
			if(conn != null && !conn.isClosed()) {
				return conn; 
			}
			
			
			Properties prop = new Properties();
			
			
			String filePath = 
					JDBCTemplate.class
					.getResource("/edu/kh/todolist1/driver.xml").getPath();
			
			prop.loadFromXML(new FileInputStream(filePath));
			
			
			Class.forName( prop.getProperty("driver") );
			String url      = prop.getProperty("url");
			String userName = prop.getProperty("userName");
			String password = prop.getProperty("password");
			
			conn = DriverManager.getConnection(url, userName, password);
			
			conn.setAutoCommit(false);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	
	// ----------------------------------
	
	/* 트랜잭션 제어 처리 메서드 (commit, rollback) */
	
	/**
	 * 전달 받은 커넥션에서 수행한 SQL을 COMMIT하는 메서드
	 * @param conn
	 */
	public static void commit(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) conn.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 전달 받은 커넥션에서 수행한 SQL을 Rollback하는 메서드
	 * @param conn
	 */
	public static void rollback(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) conn.rollback();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// ---------------------------------------------------
	
	/**
	 * 전달 받은 커넥션을 close(자원 반환)하는 메서드
	 * @param conn
	 */
	public static void close(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 전달 받은 Statement를 close(자원 반환)하는 메서드
	 * + PreparedStatement도 close 처리 가능!!
	 *   왜?? PreparedStatement가 Statement의 자식이기 때문에!!
	 *   (다형성 업캐스팅)
	 * @param stmt
	 */
	public static void close(Statement stmt) {
		try {
			if(stmt != null && !stmt.isClosed()) stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 전달 받은 ResultSet을 close(자원 반환)하는 메서드
	 * @param rs
	 */
	public static void close(ResultSet rs) {
		try {
			if(rs != null && !rs.isClosed()) rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}