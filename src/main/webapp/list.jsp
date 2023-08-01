<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>商品システム ─ itesystem</title>
</head>
<body>
	<h1>商品一覧</h1>
	<table border="1">
		<tr>
			<th>コード</th>
			<th>カテゴリーコード</th>
			<th>商品名</th>
			<th>価格</th>
		</tr>
		<c:forEach items="${items}" var="item">
		<tr>
			<td>${item.code}</td>
			<td>${item.categoryCode}</td>
			<td>${item.name}</td>
			<td>${item.price} 円</td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>