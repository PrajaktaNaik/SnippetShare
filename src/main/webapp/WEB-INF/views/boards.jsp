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
<title>Boards</title>
<script type="text/javascript">
	$('#myModal').on('shown.bs.modal', function() {
		$('#myInput').focus()
	})

	$('#editModal').on('shown.bs.modal', function() {
		$('#myInput').focus()
	})

	$('#pendingModal').on('shown.bs.modal', function() {
		$('#myInput').focus()
	})
</script>
</head>

<body>
	<div class="container">

		<div>
			<div class="col-md-12">
				<h1>
					<strong><input
						data-toggle="modal" data-target="#myModal" type="button"
						value="Create" class="btn btn-warning"></strong>&nbsp;&nbsp; <strong><input
						data-toggle="modal" data-target="#pendingModal" type="button"
						value="Pending Requests" class="btn btn-warning"></strong>
				</h1>
			</div>
		</div>
		
		<br><br>
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
							<div>
								<strong><input data-toggle="modal"
									data-target="#editModal" type="button"
									onclick='doUpdate("${e.boardId}", "${e.boardName}", "${e.description}", "${e.categoryId}", "${e.type}");'
									value="Edit" class="btn btn-warning"></strong> <strong>
									<input type="button" value="Delete" class="btn btn-warning" onclick="createRequest('/snippetshare/deleteBoard', {boardId:'${e.boardId}'}, 'post');">
								</strong>
							</div>
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

					<div class="col-sm-4">
						<div class="tile purple">
							<h3 class="title">
								<c:out value="${e.boardName }"></c:out>
							</h3>
							<p>
								<c:out value="${e.description }"></c:out>
							</p>
							<div>
								<strong><input data-toggle="modal"
									data-target="#editModal" type="button"
									onclick='doUpdate("${e.boardId}", "${e.boardName}", "${e.description}", "${e.categoryId}", "${e.type}");'
									value="Edit" class="btn btn-warning"></strong> <strong>
									<input type="button" value="Delete" class="btn btn-warning" onclick="createRequest('/snippetshare/deleteBoard', {boardId:'${e.boardId}'}, 'post');">
								</strong>
							</div>
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

	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Create Board</h4>
				</div>
				<div class="modal-body">
					<form id="createBoardForm" method="post" action="createBoard">
						<div class="row">
							<div class="col-xs-8">
								<label class="control-label">Board Title</label> <input
									type="text" class="form-control" id="boardName" name="boardName" placeholder="*Title" />
							</div>

							<div class="col-xs-4 selectContainer">
								<label class="control-label">Privacy</label> <select
									class="form-control" onchange="changeSharedWithAccess('ADD');"
									id="privacy" name="privacy">
									<c:forEach items="${boardTypes}" var="boardType">
										<option value="${boardType}">${boardType}</option>
									</c:forEach>
								</select>
							</div>
							<div class="col-xs-8">
								<label class="control-label">Description</label> <input
									type="text" class="form-control" id="boardDescription"
									name="boardDescription" />
							</div>

							<div class="col-xs-4 selectContainer">
								<label class="control-label">Category</label> <select
									class="form-control" id="category" name="category">
									<c:forEach items="${Categories}" var="Category">
										<option value="${Category.categoryName}">${Category.categoryName}</option>
									</c:forEach>
								</select>
							</div>

							<div class="col-xs-8">
								<label class="control-label">Invite People</label> <select
									class="form-control" id="userList" name="userList"
									disabled="true" onchange="changeValue('ADD');">
									<option value=""></option>
									<c:forEach items="${Users}" var="userId">
										<option value="${userId}">${userId}</option>
									</c:forEach>
								</select> <br>
								<textarea class="form-control" id="sharedWith" name="sharedWith"
									readonly="readonly"></textarea>
							</div>

						</div>

						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
							<input type="button" onclick="validate('ADD');"
								class="btn btn-primary" value="Create Board"></input>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="editModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Edit Board</h4>
				</div>
				<div class="modal-body">
					<form id="editBoardForm" method="post" action="editBoard">
						<input type="hidden" id="boardId2" name="boardId2" value="">
						<div class="row">
							<div class="col-xs-8">
								<label class="control-label">Board Title</label> <input
									type="text" class="form-control" id="boardName2"
									name="boardName2" placeholder="*Title" />
							</div>

							<div class="col-xs-4 selectContainer">
								<label class="control-label">Privacy</label> <select
									class="form-control" id="privacy2" name="privacy2"
									onchange="changeSharedWithAccess('EDIT');">
									<c:forEach items="${boardTypes}" var="boardType">
										<option value="${boardType}">${boardType}</option>
									</c:forEach>
								</select>
							</div>
							<div class="col-xs-8">
								<label class="control-label">Description</label> <input
									type="text" class="form-control" id="boardDescription2"
									name="boardDescription2" />
							</div>

							<div class="col-xs-4 selectContainer">
								<label class="control-label">Category</label> <select
									class="form-control" id="category2" name="category2">
									<c:forEach items="${Categories}" var="Category">
										<option value="${Category.categoryName}">${Category.categoryName}</option>
									</c:forEach>
								</select>
							</div>

							<div class="col-xs-8">
								<label class="control-label">Invite People</label> <select
									class="form-control" id="userList2" name="userList2"
									onchange="changeValue('EDIT');">
									<option value=""></option>
									<c:forEach items="${Users}" var="userId">
										<option value="${userId}">${userId}</option>
									</c:forEach>
								</select> <br>
								<textarea class="form-control" id="sharedWith2"
									name="sharedWith2" readonly="readonly"></textarea>
								<!--  <input type="text"  class="form-control" name="sharedWith" /> -->
							</div>

						</div>


						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
							<input type="button" onclick="validate('EDIT');"
								class="btn btn-primary" value="Edit Board"></input>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="pendingModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width: 60%">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Pending requests</h4>
				</div>
				<div class="modal-body">
					<form id="editBoardForm" method="post" action="editBoard">
						<input type="hidden" id="boardId2" name="boardId2" value="">
						<div class="row">

							<table id="projectDetails"
								class="table table-striped table-bordered" cellspacing="0"
								width="100%" style="padding: 5px 5px 5px 5px;">
								<thead>
									<tr style="font-weight: bold">
										<th style="width: 30%">Board Title</th>
										<th style="width: 40%">Requester User</th>
										<th style="width: 20%">Approve/ Reject</th>
									</tr>
								</thead>
								<tbody>

									<tr id="rowId">
										<td id="title"></td>
										<td id="User"></td>
										<td>
											<div class="buttons">
												<button class="btn btn-primary">Approve</button>

												<a style="color: white;">
													<button class="btn btn-danger">Reject</button>
												</a>

											</div>
										</td>
									</tr>

									<%-- 									<c:forEach var="dataRow" items="${project.data}">
										<tr id="${dataRow.dataId}">
											<c:forEach var="attribute" items="${dataRow.attributeValues}">
												<td id="${attribute.key}"><c:out
														value="${attribute.value}" /></td>
											</c:forEach>
											<td>
												<div class="buttons">
													<button class="btn btn-primary"
														onclick="editRecord('${dataRow.dataId}')">Edit</button>

													<a style="color: white;"
														href="deleteData?dataId=${dataRow.dataId}&projectId=${project.projectId}&tenantId=${project.tenantId}">
														<button class="btn btn-danger">Delete</button>
													</a>

												</div>
											</td>
										</tr>
									</c:forEach> --%>
								</tbody>
							</table>

							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Close</button>
							</div>
					</form>
				</div>
			</div>
		</div>
	</div>

</body>

<script type="text/javascript">
	function changeSharedWithAccess(mode) {
		var privacy;
		var combo;

		if (mode == "ADD") {
			privacy = document.getElementById("privacy").value;
			combo = document.getElementById("userList");
		} else if (mode == "EDIT") {
			privacy = document.getElementById("privacy2").value;
			combo = document.getElementById("userList2");
		}

		if (privacy == "Private") {
			combo.disabled = false;
		} else if (privacy == "Public") {
			combo.disabled = true;
		}
	}

	function validate(mode) {
		var boardName;
		if (mode == "ADD")
			boardName = document.getElementById("boardName").value;
		else if (mode == "EDIT")
			boardName = document.getElementById("boardName2").value;

		/* alert(email+":"+password+":"+rePassword+":"+firstName+":"+lastName+":"+dob+":"+address1+":"+city+":"+state+":"+zipCode); */
		if (boardName == '') {
			alert("Board title is mandatory.")
		} else {
			if (mode == "ADD")
				document.forms[0].submit();
			else if (mode == "EDIT")
				document.forms[1].submit();
		}
	}

	function changeValue(mode) {
		var component;
		var combo;
		if (mode == "ADD") {
			component = document.getElementById("sharedWith");
			combo = document.getElementById("userList");
		} else if (mode == "EDIT") {
			component = document.getElementById("sharedWith2");
			combo = document.getElementById("userList2");
		}

		var sharedWithOld = component.value;
		var newUser = combo.value;
		var newString = "";

		var contains = sharedWithOld.search(newUser);
		if (contains == -1) {
			if (sharedWithOld.length == 0) {
				newString = newUser;
			} else {
				newString = sharedWithOld + " , " + newUser;
			}
		} else {
			var position = sharedWithOld.search(" , " + newUser)
			if (position != -1) {
				newString = sharedWithOld.replace(" , " + newUser, "");
			} else {
				var newposition = sharedWithOld.search(newUser + " , ")
				if (newposition != -1)
					newString = sharedWithOld.replace(newUser + " , ", "");
				else
					newString = sharedWithOld.replace(newUser, "");
			}
		}
		component.value = newString;
		combo.value = "";
	}

	function doUpdate(boardId, boardName, description, categoryId, type) {
/* 		alert("came here");
		alert("Clicked"+boardId+":"+boardName+":"+description+":"+categoryId+":"+type); 
 */		var idCom = document.getElementById("boardId2");
		var nameCom = document.getElementById("boardName2");
		var descCom = document.getElementById("boardDescription2");
		var catCom = document.getElementById("category2");
		var typeCom = document.getElementById("privacy2");

		if (boardId != null) {
			idCom.value = boardId;
		}

		if (boardName != null) {
			nameCom.value = boardName;
		}

		if (description != null) {
			descCom.value = description;
		}

		if (categoryId != null) {
			catCom.value = categoryId;
		}

		if (type != null) {
			if (type == "Public") {
				document.getElementById("userList2").disabled = true;
			}
			typeCom.value = type;
		}

		$.ajax({
			url : "sharedWith",
			type : "GET",
			data : {
				boardId : boardId
			},
			success : function(data, textStatus, xhr) {
				if (data != "") {
					document.getElementById("sharedWith2").value = data;
				}
			}
		});
		
		 /* alert("Clicked"+boardId+":"+boardName+":"+description+":"+categoryId+":"+type); */ 
	}
</script>
</html>