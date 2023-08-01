<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>商品管理</title>
</head>
<body>
	<h1>商品更新</h1>
	<form action="/itemsystem/ItemServlet" method="post">
	<table border="1">
		<tr>
			<th>コード</th>
			<td>
				${item.code}
				<input type="hidden" name="code" value="${item.code}" />
			</td>
		</tr>
		<tr>
			<th>カテゴリーコード</th>
			<td>
				<input type="text" name="categoryCode" value="${item.categoryCode}" />
			</td>
		</tr>
		<tr>
			<th>商品名</th>
			<td>
				<input type="text" name="name" value="${item.name}" />
			</td>
		</tr>
		<tr>
			<th>価格</th>
			<td>
				<input type="text" name="price" value="${item.price}" />
			</td>
		</tr>
	</table>
	<input type="hidden" name="action" value="update" /> 
	<input type="submit" value="登録" />
	</form>
</body>
</html>