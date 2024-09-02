<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>할 일 상세 내용</title>
</head>
<body>
<form action="/complate">
  <table border="1">
    <thead>
      <th>번호</th>
      <th>할 일 제목</th>
      <th>완료 여부</th>
      <th>등록일</th>
    </thead>
    <tbody>
      <tr>
        <td>${Todo.todoNo}</td>
        <td>${Todo.todoTitle}</td>
        <td>${Todo.todoComplate}</td>
        <td>${Todo.todoDate}</td>
      </tr>
      <tr>
        <td>상세 내용</td>
        <td colspan='3'>${Todo.detail}</td>
      </tr>
    </tbody>
  </table>
  
    <input type="hidden" name="todoNo" value="${Todo.todoNo}">
    <button>완료 여부 변경</button>
  </form>
  <form action="/"><button>리스트 보기</button></form>
  <form action="/detailUpdate"><input type="hidden" name="todoNo" value="${Todo.todoNo}"><button>수정하기</button></form>


</body>
</html>