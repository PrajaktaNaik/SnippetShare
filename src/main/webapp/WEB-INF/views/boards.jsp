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
   <c:forEach items="${publicBoards}" var="element" varStatus="myIndex">

	<% if((prev)%3==0){ %>
		<div class="row">
	<%prev++;} else{prev++;} %>
	
	<div class="col-sm-4">
      <div  class="tile green">
        <h3 class="title"><c:out value="${element.boardName }"></c:out></h3>
        <p><c:out value="${element.description }"></c:out></p>
        
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
   <c:forEach items="${privateBoards}" var="element" varStatus="myIndex">

	<% if((prev)%3==0){ %>
		<div class="row">
	<%prev++;} else{prev++;} %>
	
	<div class="col-sm-4">
      <div  class="tile purple">
        <h3 class="title"><c:out value="${element.boardName }"></c:out></h3>
        <p><c:out value="${element.description }"></c:out></p>
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
                <input type="text" class="form-control" name="boardName" />
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
            <div class="col-xs-8">
                <label class="control-label">Invite People</label>
                <input type="text"  class="form-control" name="sharedWith" />
            </div>
             <div class="col-xs-8 selectContainer">
                <label class="control-label">Category</label>
                <select class="form-control" name="category">
                    <c:forEach items="${Categories}" var="Category">
						<option value="${Category.categoryName}">${Category.categoryName}</option>
					</c:forEach>
                </select>
            </div>
        </div>
       
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <input type="submit"  class="btn btn-primary" value="Create Board"></input>
      </div>
       </form>
    </div>
  </div>
</div>
<!-- <button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
  Launch demo modal
</button> -->
</body>
</html>