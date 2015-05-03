<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
		<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>  
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
		<link href="<c:url value="/resources/css/boards.css" />" rel="stylesheet">
		<title>Boards</title>
		<script type="text/javascript">
			$('#myModal').on('shown.bs.modal', function () {
				  $('#myInput').focus()
				})
				
			$('#editModal').on('shown.bs.modal', function () {
				  $('#myInput').focus()
				})
		</script>
	</head>
	
	<body>
		<div class="container">
		
			  <div>
			    <div class="col-md-12">
			      <h1><strong>Your Boards</strong>&nbsp;&nbsp;&nbsp;<strong><input data-toggle="modal" data-target="#myModal" type="button" value="Create Board" class="btn btn-warning"></strong></h1>
			       <h1></h1>
			    </div>
			  </div>
		  
			  <div class="row">
			  	  <span class="label label-primary">Public Boards</span><br><br>
				  <% int prev=0; %>
				  
				  <c:forEach items="${publicBoards}" var="e" varStatus="myIndex">
					<% if((prev)%3==0){ %>
						<div class="row">
					<%prev++;} else{prev++;} %>
					
					<div class="col-sm-4">
				      <div  class="tile green">
				        <h3 class="title"><a href="/snippetshare/showBoard/<c:out value="${e.boardId }"></c:out>"><c:out value="${e.boardName }"></c:out></a></h3>
				        <p><c:out value="${e.description }"></c:out></p>
				        <div>
					        <strong><input data-toggle="modal" data-target="#editModal" type="button" onclick="doUpdate('${e.boardId}', '${e.boardName}', '${e.description}', '${e.categoryId}', '${e.type}');" 
					        			value="Edit" class="btn btn-warning"></strong>
					        <strong>
					        <a href="/snippetshare/deleteBoard?boardId=${e.boardId}"  class="btn btn-warning">Delete</a></strong>
				        </div>
				      </div>
				    </div>
				    
					<% if(prev%3==0){ %>
						</div>
					<%}  %>
		          </c:forEach>
			  </div>
		  
		  	<div>
				<span class="label label-primary">Private Boards</span><br><br>
		       <% prev=0; %>
		   
			   <c:forEach items="${privateBoards}" var="e" varStatus="myIndex">
					<% if((prev)%3==0){ %>
						<div class="row">
					<%prev++;} else{prev++;} %>
					
					<div class="col-sm-4">
				      <div  class="tile purple">
				        <h3 class="title"><c:out value="${e.boardName }"></c:out></h3>
				        <p><c:out value="${e.description }"></c:out></p>
				        <div>
					        <strong><input data-toggle="modal" data-target="#editModal" type="button" onclick="doUpdate('${e.boardId}', '${e.boardName}', '${e.description}', '${e.categoryId}', '${e.type}');" 
					        			value="Edit" class="btn btn-warning"></strong>
					        <strong>
					        <a href="/snippetshare/deleteBoard?boardId=${e.boardId}"  class="btn btn-warning">Delete</a></strong>
				        </div>
				      </div>
				    </div>
					<% if(prev%3==0){ %>
						</div>
					<%}  %>
		       </c:forEach>
			</div>
		</div>
		
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="myModalLabel">Create Board</h4>
		      </div>
		      <div class="modal-body">
		        <form id="createBoardForm" method="post" action="createBoard">
		        		<div class="row">
		            <div class="col-xs-8">
		                <label class="control-label">Board Title</label>
		                <input type="text" class="form-control" id="boardName" name="boardName" placeholder="*Title" />
		            </div>
		
		            <div class="col-xs-4 selectContainer">
		                <label class="control-label">Privacy</label>
		                <select class="form-control" name="privacy">
		                    <c:forEach items="${boardTypes}" var="boardType">
								<option value="${boardType}">${boardType}</option>
							</c:forEach>
		                </select>
		            </div>
		            <div class="col-xs-8">
		                <label class="control-label">Description</label>
		                <input type="text" class="form-control" name="boardDescription" />
		            </div>
		            
		             <div class="col-xs-4 selectContainer">
		                <label class="control-label">Category</label>
		                <select class="form-control" name="category">
		                    <c:forEach items="${Categories}" var="Category">
								<option value="${Category.categoryName}">${Category.categoryName}</option>
							</c:forEach>
		                </select>
		            </div>
		            
		            <div class="col-xs-8">
		                <label class="control-label">Invite People</label>
		               
		                <select class="form-control" id = "userList" name="userList" onchange="changeValue('ADD');">
		                	<option value=""></option>
		                    <c:forEach items="${Users}" var="userId">
								<option value="${userId}">${userId}</option>
							</c:forEach>
		                </select>
		                <br>
		                <textarea class="form-control" id = "sharedWith" name="sharedWith" readonly="readonly"></textarea>
		                <!--  <input type="text"  class="form-control" name="sharedWith" /> -->
		            </div>
		            
		        </div>
		       
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		        <input type="button" onclick="validate('ADD');" class="btn btn-primary" value="Create Board"></input>
		      </div>
		       </form>
		       </div>
		    </div>
		  </div>
		</div>
		
		<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="myModalLabel">Edit Board</h4>
		      </div>
		      <div class="modal-body">
		        <form id="editBoardForm" method="post" action="editBoard">
		        	<input type="hidden" id="boardId2" name="boardId2" value="">
		        		<div class="row">
		            <div class="col-xs-8">
		                <label class="control-label">Board Title</label>
		                <input type="text" class="form-control" id="boardName2" name="boardName2" placeholder="*Title" />
		            </div>
		
		            <div class="col-xs-4 selectContainer">
		                <label class="control-label">Privacy</label>
		                <select class="form-control" id="privacy2" name="privacy2">
		                    <c:forEach items="${boardTypes}" var="boardType">
								<option value="${boardType}">${boardType}</option>
							</c:forEach>
		                </select>
		            </div>
		            <div class="col-xs-8">
		                <label class="control-label">Description</label>
		                <input type="text" class="form-control" id="boardDescription2" name="boardDescription2" />
		            </div>
		            
		             <div class="col-xs-4 selectContainer">
		                <label class="control-label">Category</label>
		                <select class="form-control" id="category2" name="category2">
		                    <c:forEach items="${Categories}" var="Category">
								<option value="${Category.categoryName}">${Category.categoryName}</option>
							</c:forEach>
		                </select>
		            </div>
		            
		            <div class="col-xs-8">
		                <label class="control-label">Invite People</label>
		               
		                <select class="form-control" id = "userList2" name="userList2" onchange="changeValue('EDIT');">
		                	<option value=""></option>
		                    <c:forEach items="${Users}" var="userId">
								<option value="${userId}">${userId}</option>
							</c:forEach>
		                </select>
		                <br>
		                <textarea class="form-control" id = "sharedWith2" name="sharedWith2" readonly="readonly"></textarea>
		                <!--  <input type="text"  class="form-control" name="sharedWith" /> -->
		            </div>
		            
		        </div>
		       
		      
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		        <input type="button" onclick="validate('EDIT');" class="btn btn-primary" value="Edit Board"></input>
		      </div>
		       </form>
		       </div>
		    </div>
		  </div>
		</div>

	</body>
	
	<script type="text/javascript">
		function validate(mode){
			var boardName;
			if(mode == "ADD")
				boardName = document.getElementById("boardName").value;
			else if(mode == "EDIT")
				boardName = document.getElementById("boardName2").value;
			
			/* alert(email+":"+password+":"+rePassword+":"+firstName+":"+lastName+":"+dob+":"+address1+":"+city+":"+state+":"+zipCode); */
			if(boardName == '' ){
				alert("Board title is mandatory.")
			}else{
				if(mode == "ADD")
					document.forms[0].submit();
				else if(mode == "EDIT")
					document.forms[1].submit();
			}
		}
		
		function changeValue(mode){
			var component;
			var combo;
			if(mode == "ADD"){
				component = document.getElementById("sharedWith");
				combo = document.getElementById("userList");
			}else if(mode == "EDIT"){
				component = document.getElementById("sharedWith2");
				combo = document.getElementById("userList2");
			}
			
			var sharedWithOld = component.value;
			var newUser = combo.value;
			var newString = "";
			
			var contains = sharedWithOld.search(newUser);
			if(contains == -1){
				if(sharedWithOld.length == 0){
					newString = newUser;
				}else{
					newString = sharedWithOld+" , "+newUser;	
				}
			}else{
				var position = sharedWithOld.search(" , "+newUser) 
				if(position != -1 ){
					newString = sharedWithOld.replace(" , "+newUser, "");	
				}else{
					var newposition = sharedWithOld.search(newUser+" , ")
					if(newposition != -1)
						newString = sharedWithOld.replace(newUser+" , ", "");
					else
						newString = sharedWithOld.replace(newUser, "");
				}
			}
			component.value = newString;
			combo.value = "";
		}
		
		function doUpdate(boardId, boardName, description, categoryId, type){
			
			var idCom = document.getElementById("boardId2");
			var nameCom = document.getElementById("boardName2");
			var descCom = document.getElementById("boardDescription2");
			var catCom = document.getElementById("category2");
			var typeCom = document.getElementById("privacy2");
			
			if(boardId != null){
				idCom.value = boardId;
			}
			
			if(boardName != null){
				nameCom.value = boardName;
			}
			
			if(description != null){
				descCom.value = description;
			}
			
			if(categoryId != null){
				catCom.value = categoryId;		
			}
					
			if(type != null){
				typeCom.value = type;
			}
			
			$.ajax({
			url: "sharedWith",
			type : "GET",
			data: { boardId: boardId},
			success: function(data,textStatus,xhr){
				if(data != ""){
					document.getElementById("sharedWith2").value = data;
				}			
			}});
			
			alert("Clicked"+boardId+":"+boardName+":"+description+":"+categoryId+":"+type);
		}
	</script>
</html>