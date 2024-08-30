package edu.kh.jdbc.dao;

//지정된 클래스의 static 메서드를 모두 얻어와 사용
import static edu.kh.jdbc.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.jdbc.common.JDBCTemplate;
import edu.kh.jdbc.dto.User;

public class UserDaoImpl implements UserDao{

	
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private Properties prop;
	// K,V가 모두 String인 Map, 파일 입출력이 쉬움
	
	// 기본 생성자
	public UserDaoImpl() {
		
		// 객체 생성 시 외부에 존재하는 sql.xml 파일을 읽어와
		// prop에 저장
		
		try {
			String filePath = 
					JDBCTemplate.class
					.getResource("/edu/kh/jdbc/sql/sql.xml").getPath();
			
			// 지정된 경로의 XML파일 내용을 읽어와
			// Properties 객체에 K:V 세팅
			prop = new Properties();
			prop.loadFromXML(new FileInputStream(filePath));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public int insertUser(Connection conn, User user) throws Exception {

		// 1. 결과 저장용 변수 선언
		int result = 0;
		
		try {
			
			// 2. SQL 작성 -> Properties를 이용해 외부 sql.xml 파일에서
			// 			     읽어온 sql 이용
			String sql = prop.getProperty("insertUser");
			
			// 3. PreparedStatement 생성
			pstmt = conn.prepareStatement(sql);
			
			// 4. ?에 알맞은 값 세팅
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getUserPw());
			pstmt.setString(3, user.getUserName());
			
			// 5. SQL(INSERT) 수행(executeUpdate()) 후 
			//	  결과 (삽입된 행의 개수, int) 반환
			
			result = pstmt.executeUpdate();
			
		} finally {
			// 6. 사용한 JDBC 객체 자원 반환(close)
			close(pstmt);
		}
		// 결과 반환
		return result;
	
	}

	@Override
	public int idCheck(Connection conn, String userId) throws SQLException {
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("idCheck");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
 		} finally {
 			close(rs);
			close(pstmt);
		}
		
		return result;
	}

	@Override
	public User login(Connection conn, String userId, String userPw) throws Exception {
		
		// 결과 저장용 변수 선언
		User loginUser = null;
		
		try {
			
			String sql = prop.getProperty("login");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			pstmt.setString(2, userPw);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int userNO = rs.getInt("USER_NO");
				String id = rs.getString("USER_ID");
				String pw = rs.getString("USER_Pw");
				String name = rs.getString("USER_NAME");
				String enrollDate = rs.getString("ENROLL_DATE");
				
				loginUser = new User(userNO, id, pw, name, enrollDate);
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		
		return loginUser;
	}

	@Override
	public List<User> select(Connection conn) throws Exception{
		
		List<User> userlist = new ArrayList<User>();
		
		try {
		
			String sql = prop.getProperty("selectAll");
			
			stmt = conn.createStatement();
					
			rs = stmt.executeQuery(sql);
		
			while(rs.next()) {
				User userInput = new User();
				
				userInput.setUserNo(rs.getInt("회원번호"));
				userInput.setUserId(rs.getString("id"));
				userInput.setUserPw(rs.getString("pw"));
				userInput.setUserName(rs.getString("이름"));
				userInput.setEnrollDate(rs.getString("ENROLL_DATE"));

				userlist.add(userInput);
			}
		} finally {
			close(rs);
			close(stmt);
		}
		
		return userlist;
	}

	@Override
	public List<User> userSearch(Connection conn, String searchId) throws Exception {

		List<User> userList = new ArrayList<User>();

		try {
			
			String sql = prop.getProperty("searchUser");
			
			pstmt = conn.prepareStatement(sql);
					
			pstmt.setString(1, searchId);
			
			rs = pstmt.executeQuery();
		
			while(rs.next()) {
				User userInput = new User();
				
				userInput.setUserNo(rs.getInt("회원번호"));
				userInput.setUserId(rs.getString("id"));
				userInput.setUserPw(rs.getString("pw"));
				userInput.setUserName(rs.getString("이름"));
				userInput.setEnrollDate(rs.getString("ENROLL_DATE"));

				userList.add(userInput);
			}
		} finally {
			close(rs);
			close(stmt);
		}
		
		return userList;
	}

	@Override
	public List<User> selectNo(Connection conn, String searchNo) throws Exception {

		List<User> userList = new ArrayList<User>();

		try {
			
			String sql = prop.getProperty("searchUserNo");
			
			pstmt = conn.prepareStatement(sql);
					
			pstmt.setString(1, searchNo);
			
			rs = pstmt.executeQuery();
		
			while(rs.next()) {
				User userInput = new User();
				
				userInput.setUserNo(rs.getInt("회원번호"));
				userInput.setUserId(rs.getString("id"));
				userInput.setUserPw(rs.getString("pw"));
				userInput.setUserName(rs.getString("이름"));
				userInput.setEnrollDate(rs.getString("ENROLL_DATE"));

				userList.add(userInput);
			}
		} finally {
			close(rs);
			close(stmt);
		}
		
		return userList;
		
	}

	@Override
	public int deleteUser(Connection conn, int userNo) throws Exception {

		int result = 0;
		
		try {
			
			String sql = prop.getProperty("delete");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, --userNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(rs);
			close(pstmt);
		}

		return result;
	}

	@Override
	public int updateUser(Connection conn, User user) throws Exception {
		
		int result = 0;
		
		try {
		
			String sql = prop.getProperty("update");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserPw());
			pstmt.setString(2, user.getUserName());
			pstmt.setInt(3, user.getUserNo());
			
			System.out.println(user.getUserPw());
			
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	
	
}
