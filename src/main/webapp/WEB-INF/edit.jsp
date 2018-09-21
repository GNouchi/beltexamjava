<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    
<%@ page isErrorPage="true" %>    

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style type="text/css">
	.right , .left {
		display: inline-block;
		min-height: 100px;
		vertical-align:top;
		margin-left:10px;
	}
	.master{
		margin-left:100px;
	}
</style>

</head>
<body>
<a href="/">Login</a>
<a href="/tasks">Tasks Dashboard</a>
<a href="/tasks/new">Create</a>
<a href="/tasks/1">Show/Delete</a>
<a href="/tasks/1/edit">Edit</a>
<a href="/logout">Logout</a>


 <h1>Edit :  ${task.getTaskname()}</h1>
 <p>${error}</p>
<div class="master">
<div class="left">
	<p>Task </p>
	<p>Assignee </p>
	<p>Priority </p>
</div>

<div class="right">
	<form:form action="/tasks/${task.getId()}/edit" method="post" modelAttribute="task">
	    <input type="hidden" name="_method" value="put">
	<form:hidden path="id"/>			
	<form:hidden path="creator"/>			
		<p>
			<form:input type="text" path="taskname"/>			
		</p>
		<p>	
			<form:select path="assignee">
				<form:option value="${task.getAssignee()}">${task.getAssignee().getName()}</form:option>
				<c:forEach items="${assignees}" var="user">			
					<form:option value="${user}">${user.getName()}</form:option>
				</c:forEach>
			</form:select>
		</p>
		<p>
			<form:select path="priority" >
				<c:forEach items="${priorities}" var="priority">	
					<c:if test="${priority==1}">
						<form:option value="${priority}">Low</form:option>
					</c:if> 
					<c:if test="${priority==2}">
						<form:option value="${priority}">Med</form:option>
					</c:if> 
					<c:if test="${priority==3}">
						<form:option value="${priority}">High</form:option>
					</c:if> 
				</c:forEach>			
			</form:select>		
		</p>
		
		<button type="submit">Create</button>
	</form:form>

</div>
</div>

</body>
</html>