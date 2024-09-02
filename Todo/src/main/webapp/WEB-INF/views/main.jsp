<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>main</title>
</head>
<body>
  
  <table border="1">
    <thead>
      <th>번호</th>
      <th>할 일</th>
      <th>실행 여부</th>
      <th>등록 일시</th>
    </thead>
    <tbody>
    <c:forEach var="todolist" items="${todoList}">
      <tr>
        <td>${todolist.todoNo}</td>
        <td><a href="/todo/detail?todoNo=${todolist.todoNo}">${todolist.todoTitle}</a></td>
        <td>${todolist.todoComplate}</td>
        <td>${todolist.todoDate}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
  <form action="/update"><button>추가하기</button></form>
  <form action="/delete"><button>삭제하기</button></form>


</body>
</html>