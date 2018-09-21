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
	}
	.right{
		text-alighn: center;
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



<h1>This is show</h1>
<h1>Task: Buy Lunch </h1>
<div class="left">
	<p>Creator: </p>
	<p>Assignee: </p>
	<p>Priority: </p>
<c:if test="${isCreator==1}">
	<form action="/tasks/${task.getId()}/edit">
		<button type="submit">Edit</button>
	</form>
</c:if>
</div>

<div class="right">
	<p>${task.getCreator().getName()}</p>
	<p>${task.getAssignee().getName()} </p>
	<p>
		<c:if test="${task.getPriority()==1}">LOW</c:if>
		<c:if test="${task.getPriority()==2}">MED</c:if>
		<c:if test="${task.getPriority()==3}">HIGH</c:if>
	 </p>
<c:if test="${isCreator==1}">
	<form action="/tasks/${task.getId()}/delete" method="post">
		<button type="submit">Delete</button>
	</form>
</c:if>
</div>
<br>

<div class="left"></div>

<div class="right">
<c:if test="${isAssignee==1}">
	<form action="/tasks/1/complete" method="post">
		<button type="submit">Complete</button>
	</form>	
</c:if>
</div>
</body>
</html>