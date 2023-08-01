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
			<th>更新</th>
		</tr>
		<c:forEach items="${items}" var="item">
		<tr>
			<td>${item.code}</td>
			<td>${item.categoryCode}</td>
			<td>${item.name}</td>
			<td>${item.price} 円</td>
			<td>
				<form action="/itemsystem/ItemServlet" method="get">
					<input type="hidden" name="action" value="edit" />
					<input type="hidden" name="code" value="${item.code}" />
					<input type="submit" value="更新" />
				</form>
			</td>
		</tr>
		</c:forEach>
	</table>
	<a href="/itemsystem/ItemServlet?action=regist">新規登録</a>
</body>
</html>