<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>사용자 목록 조회</title>
  <link rel="stylesheet" href="/resources/css/selectAll.css">
  <link rel="stylesheet" href="/resources/css/main.css">
</head>
<body>


  <h1>사용자 목록 조회</h1>
   <c:if test="${not empty param.searchId}" >
      <h3>"${param.searchId}" 검색 결과</h3>
    </c:if>
    <form action="/search">
      ID 검색 : <input 
                  type="text" 
                  name="searchId" 
                  placeholder="포함되는 아이디 검색"
                  value="${param.searchId}"> 
  
      <button>검색</button>
    </form>
    <hr>
  
  
    <table border="1">
      <tr>
        <th>회원번호</th>
        <th>아이디</th>
        <%-- <th>비밀번호</th> --%>
        <th>이름</th>
        <%-- <th>등록일</th> --%>
      </tr>
      <c:if test="${empty user}" >
          <tr>
            <th colspan="5">조회 결과가 없습니다</th>
          </tr>
        </c:if>
  
  
      <%-- 조회 결과가 있을 경우 --%>
        <c:if test="${not empty user}">
          <c:forEach items="${user}" var="user">
            <tr>
              <th>${user.userNo}</th>
              <td>
                <a href="/selectUser?userNo=${user.userNo}">${user.userId}</a>
              </td>
              <%-- <td>${user.userPw}</td> --%>
              <td>${user.userName}</td>
              <%-- <td>${user.enrollDate}</td> --%>
            </tr>
          </c:forEach>
        </c:if>
  
  <%-- session에 message가 존재하는 경우 --%>
  <c:if test="${!empty sessionScope.message}" >
    <script>
      alert("${sessionScope.message}");
    </script>
  
  <%-- session에 존재하는 message 제거 --%>
  <c:remove var="message" scope="session" />
  </c:if>




  </table>
</body>
</html>