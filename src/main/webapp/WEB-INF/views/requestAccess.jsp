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
<script src="<c:url value="/resources/js/common.js" />"></script>
<title>Access Private Boards</title>
</head>

<body>
	<br>
	<br>

	<c:if test="${requestList.size() == 0}">
		<div>
			<div class="col-md-12">
				<h1>
					<strong><c:out value="No more private boards are hidden." /></strong>
				</h1>
			</div>
		</div>
	</c:if>

	<c:if test="${requestList.size() >0}">
		<div class="row">
			<div class="col-md-10">
				<table id="projectDetails"
					class="table table-striped table-bordered" cellspacing="0"
					width="100%" style="padding: 5px 5px 5px 5px;">
					<thead>
						<tr style="font-weight: bold">
							<th style="width: 20%">Owner</th>
							<th style="width: 25%">Board Title</th>
							<th style="width: 40%">Board Description</th>
							<th style="width: 20%">Request Status</th>
						</tr>
					</thead>
					<tbody>

						<c:forEach var="dataRow" items="${requestList}">
							<tr id="${dataRow.boardId}">
								<td><c:out value="${dataRow.ownerId}" /></td>
								<td><c:out value="${dataRow.boardName}" /></td>
								<td><c:out value="${dataRow.boardDescription}" /></td>

								<td><c:choose>
										<c:when test='${dataRow.status == ""}'>
											<div class="buttons">
												<input type="button" value="Request Access"
													class="btn btn-primary"
													onclick="createRequest('/snippetshare/requestAccess', {boardOwner:'${dataRow.ownerId}', boardId:'${dataRow.boardId}'}, 'post');">
											</div>
										</c:when>
										<c:otherwise>
											<c:out value="${dataRow.status}" />
										</c:otherwise>
									</c:choose></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

			</div>
		</div>
	</c:if>


</body>
</html>