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
<link href="<c:url value="/resources/css/boards.css" />" rel="stylesheet">
<script src="<c:url value="/resources/js/common.js" />" ></script>
<title>Search Boards</title>
<script type="text/javascript">
	$(function() {
		$("#header").load("header");

		// $("#footer").load("footer.ejs"); 
	});
</script>
</head>

<body>
<div id="header" class="header"></div>
	<div class="container">
		<br>
		<br>
		<div>
			<table>
			<tr>
			<td>
				<select class="form-control" style="width: 100%" id="searchCategory"name="searchCategory">
				<option value="">Select Category to Search</option> 
				<c:forEach items="${Categories}" var="Category">
					<option value="${Category.categoryName}"  ${Category.categoryName == searchValue ? 'selected="selected"' : ''}>${Category.categoryName}</option>
				</c:forEach>
				</select> 
			
			 </td>
			<td >
				<input  type="button" value="Cat Search" class="btn btn-warning" style="margin-left:15px;" onclick="searchBoards('CATEGORY');">
			</td>
			
			<td >
				<div style="margin-left:35px;">
				<select class="form-control" style="width: 100%" id="searchUser"  name="searchUser"  value="${searchValue}" placeHolder = "Select User to Search" >
				<option value="">Select User to Search</option>
				<c:forEach items="${Users}" var="userId">
					<option value="${userId}" ${userId == searchValue ? 'selected="selected"' : ''}>${userId}</option>
				</c:forEach>
				</select>
				</div>
			 </td>
			<td>
				<input type="button" value="User Search" class="btn btn-warning" style="margin-left:15px;" onclick="searchBoards('USER');">
				
			</td>
			</tr>
			
			</table>
			
			<br>
			<br>

			
			 
			<c:if test="${searchType == 'USER'}">
				<input type="button" value="Ask Private Board Access" class="btn btn-warning" onclick="createRequest('/snippetshare/viewAccess', {boardOwner:'${searchValue}'}, 'post');">
			</c:if>
		</div>

		<div>
			<div class="col-md-12" style="margin-left:-35px">
				<h3>
					<c:if test="${searchValue != ''}">
						<strong>${welcomeMsg}</strong>
					</c:if>
					
					
				</h3>
			</div>
		</div>
		
		<br>
		<c:if test="${publicBoards.size() > 0 }">
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

					<div class="col-sm-4" style="width:25%;">
						<div class="tile green">
							<h3 class="title">
								<a style="color:white" href="/snippetshare/showSnippets/<c:out value="${e.boardId }"></c:out>">
									<c:out value="${e.boardName }"></c:out>
								</a>
							</h3>
							<p>
								<c:out value="${e.description }"></c:out>
							</p>
							<div class="lbl">Board Owner:<c:out value="${e.ownerId }"></c:out></div>
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
		</c:if>
		
		<c:if test="${privateBoards.size() > 0 }">
		<div>
			<span class="label label-primary">Private Boards</span><br> <br>
			<%
				int prev = 0;
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

					<div class="col-sm-4" style="width:25%;">
						<div class="tile purple">
							<h3 class="title">
								<a style="color:white" href="/snippetshare/showSnippets/<c:out value="${e.boardId }"></c:out>">
									<c:out value="${e.boardName }"></c:out>
								</a>
							</h3>
							<p>
								<c:out value="${e.description }"></c:out>
							</p>
							<div class="lbl">Board Owner:<c:out value="${e.ownerId }"></c:out></div>
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
		</c:if>
	</div>

</body>

<script type="text/javascript">

	function searchBoards(mode){
		var combo;
		if(mode == "CATEGORY"){
			combo = document.getElementById('searchCategory');
			var temp =combo.value; 
			if(temp == null || temp == ""){
				alert("Please select a category to search for.")
			}else{
				createRequest("/snippetshare/searchBoards",{"type":"CATEGORY", "value":temp}, "post");
			}
		}else if(mode == "USER"){
			combo = document.getElementById('searchUser');
			var temp =combo.value; 
			if(temp == null || temp == ""){
				alert("Please select a user to search for.")
			}else{
				createRequest("/snippetshare/searchBoards",{"type":"USER", "value":temp}, "post");
			}
		}
	}
</script>

</html>