package edu.kh.jdbc.dao;

import java.sql.Connection;
import java.util.List;

import edu.kh.jdbc.dto.User;

public interface UserDao {

	/**
	 * 사용자 등록
	 * @param conn
	 * @param user
	 * @return
	 * @throws Exception
	 */
	int insertUser(Connection conn, User user) throws Exception;

	/**
	 * 아이디 중복 여부 확인
	 * @param conn
	 * @param userId
	 * @return result
	 * @throws Exception
	 */
	int idCheck(Connection conn, String userId) throws Exception;

	/**
	 * 로그인
	 * @param conn
	 * @param userId
	 * @param userPw
	 * @return loginUser
	 * @throws Exception
	 */
	User login(Connection conn, String userId, String userPw) throws Exception;

	List<User> select(Connection conn) throws Exception;

	List<User> userSearch(Connection conn, String searchId) throws Exception;

	List<User> selectNo(Connection conn, String searchNo) throws Exception;

	int deleteUser(Connection conn, int userNo) throws Exception;

	int updateUser(Connection conn, User user) throws Exception;


}
