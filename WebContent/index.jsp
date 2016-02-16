<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ImproveGroup</title>
</head>
<body>
	<h2>Прайс-лист</h2>	
	<form action="pricelist" method="post">
		<table>
			<tr>
				<td>Категория:</td>
				<td>Наименование:</td>
				<td>Цена от:</td>
				<td>Цена до:</td>
				<td></td>
			</tr>
			<tr>
				<td><input type="text" name="cathegory"></input></td>
				<td><input type="text" name="name"></input></td>
				<td><input type="text" name="priceMin"></input></td>
				<td><input type="text" name="priceMax"></input></td>
				<td><input type="submit" value="Найти"></td>
			</tr>
		</table>
	</form>
	<br>
	<table>
		<tr>
			<td>Категория</td>
			<td>Наименование</td>
			<td>Цена</td>
		</tr>
		<c:forEach var="product" items="${products}">
			<tr>
			    <td>${product.catId}</td>
				<td>${product.name}</td>
				<td>${product.price}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>