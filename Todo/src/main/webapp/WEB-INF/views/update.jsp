<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>할 일 추가</title>
</head>
<body>
<form action="/addUpdate">
  할 일 제목 : <input type="text" name="todoTitle">
  상세 내용 : <input type="text" name="detail">
  <button>입력</button></form>
</body>
</html>