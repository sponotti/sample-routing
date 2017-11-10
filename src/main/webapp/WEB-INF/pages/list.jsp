<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
	<h1>Countries</h1>
	
	<c:if test="${not empty lists}">

		<ul>
			<c:forEach var="listValue" items="${lists}">
				<li>Country: ${listValue.name}</li>
				<c:if test="${not empty listValue.currencies}">
					Currencies
					<ul>
						<c:forEach var="curr" items="${listValue.currencies}">
							<li>${curr.name} - ${curr.symbol }</li>
						</c:forEach>
					</ul>
				</c:if>
			</c:forEach>
		</ul>

	</c:if>
</body>
</html>