<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<link href="<c:url value="/resources/css/boards.css" />"
	rel="stylesheet">
<title>Search Boards</title>
</head>

<body>
	<div class="container">

		<div>
			<div class="col-md-12">
				<h1>
					<strong>${welcomeMsg}</strong>
				</h1>
			</div>
		</div>

		<div class="row">
			<span class="label label-primary">Public Boards</span><br> <br>
			<%
				int prev = 0;
			%>

			<c:forEach items="${publicBoards}" var="e" varStatus="myIndex">
				<%
					if ((prev) % 3 == 0) {
				%>
				<div class="row">
					<%
						prev++;
							} else {
								prev++;
							}
					%>

					<div class="col-sm-4">
						<div class="tile green">
							<h3 class="title">
								<a
									href="/snippetshare/showSnippets/<c:out value="${e.boardId }"></c:out>"><c:out
										value="${e.boardName }"></c:out></a>
							</h3>
							<p>
								<c:out value="${e.description }"></c:out>
							</p>
						</div>
					</div>

					<%
						if (prev % 3 == 0) {
					%>
				</div>
				<%
					}
				%>
			</c:forEach>
		</div>

		<div>
			<span class="label label-primary">Private Boards</span><br> <br>
			<%
				prev = 0;
			%>

			<c:forEach items="${privateBoards}" var="e" varStatus="myIndex">
				<%
					if ((prev) % 3 == 0) {
				%>
				<div class="row">
					<%
						prev++;
							} else {
								prev++;
							}
					%>

					<div class="col-sm-4">
						<div class="tile purple">
							<h3 class="title">
								<c:out value="${e.boardName }"></c:out>
							</h3>
							<p>
								<c:out value="${e.description }"></c:out>
							</p>
						</div>
					</div>
					<%
						if (prev % 3 == 0) {
					%>
				</div>
				<%
					}
				%>
			</c:forEach>
		</div>
	</div>

</body>
</html>