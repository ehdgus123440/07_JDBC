<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>User 관리 프로젝트</title>
  <link rel="stylesheet" href="/resources/css/main.css">
</head>
<body>

<c:if test="${ip ne '192.168.10.30'}">
  <h1>해당 ip는 접속할 수 없습니다</h1>
  <h2>해당 ip는 접속할 수 없습니다</h2>
  <h3>해당 ip는 접속할 수 없습니다</h3>
  <h4>해당 ip는 접속할 수 없습니다</h4>
  <h5>해당 ip는 접속할 수 없습니다</h5>
  <h6>해당 ip는 접속할 수 없습니다</h6>
</c:if>

<c:if test="${ip eq '192.168.10.30'}">
  <%-- 로그인이 안 되어 있는 경우 
    == session scope에 "loginUser"가 없는 경우
  --%>
  <c:if test="${empty sessionScope.loginUser}">
    <h1>Login</h1>
    <form action="/login" method="post">
      <div>
        ID : <input type="text" name="userId">
      </div>
      <div>
        PW : <input type="text" name="userPw">
      </div>
      <div>
        <button>로그인</button>
        <a href="/signUp">사용자 등록</a>
      </div>
    </form>
  </c:if>
  
  <%-- 로그인 상태인 경우 --%>
   <c:if test="${not empty sessionScope.loginUser}" >
    <h1>${loginUser.userName}님</h1>
    <ul>
      <li>userNo : ${loginUser.userNo}</li>
      <li>userId : ${loginUser.userId}</li>
      <li>userName : ${loginUser.userName}</li>
      <li>enrollDate : ${loginUser.enrollDate}</li>
    </ul>
  
    <button id="logout">Logout</button>
  
    <hr>
  
    <h3>메뉴</h3>
    <ul>
      <li><a href="/selectAll">사용자 목록 조회</a></li>
    </ul>
  </c:if>
  
  
  <%-- session에 message가 존재하는 경우 --%>
  <c:if test="${!empty sessionScope.message}" >
    <script>
      alert("${sessionScope.message}");
    </script>
  
  <%-- session에 존재하는 message 제거 --%>
  <c:remove var="message" scope="session" />
  </c:if>
  
</c:if>
<script src="\resources\js\main.js"></script>
</body>
</html>