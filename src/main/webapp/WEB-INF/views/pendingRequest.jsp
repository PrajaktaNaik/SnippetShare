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
<title>View Requests</title>
</head>

<body>
	<br>
	<br>

	<c:if test="${requestList.size() == 0}">
		<div>
			<div class="col-md-12">
				<h1>
					<strong><c:out value="No more requests are pending." /></strong>
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
								<th style="width: 30%">Board Title</th>
								<th style="width: 40%">Board Description</th>
								<th style="width: 25%">Requester User</th>
								<th style="width: 20%">Approve/ Reject</th>
							</tr>
					</thead>
					<tbody>

						<c:forEach var="dataRow" items="${requestList}">
							<tr id="${dataRow.id}">
								<td><c:out value="${dataRow.boardName}" /></td>
								<td><c:out value="${dataRow.boardDescription}" /></td>
								<td><c:out value="${dataRow.requesterId}" /></td>
								<td>
									<div class="buttons">
									
										<input type="button" value="Approve"
													class="btn btn-primary"
													onclick="createRequest('/snippetshare/processPendingRequests', {requestId:'${dataRow.id}', boardId:'${dataRow.boardId}', requesterId:'${dataRow.requesterId }', mode:'ACCEPTED'}, 'post');">
										<input type="button" value="Reject"
													class="btn btn-danger"
													onclick="createRequest('/snippetshare/processPendingRequests', {requestId:'${dataRow.id}', boardId:'${dataRow.boardId}', requesterId:'${dataRow.requesterId }', mode:'REJECTED'}, 'post');">
									</div>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

			</div>
		</div>
	</c:if>

</body>
</html>