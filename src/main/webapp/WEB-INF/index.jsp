<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    
<%@ page isErrorPage="true" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
    <style>
	table, th, td {
	    border: 1px solid black;
	    border-collapse: collapse;
		}
	table{
		min-width: 1200px;
	}
	td{
		padding:5px;
	}
	th{ background:grey;}
	.odd
		{background-color: #F8F8F8;}
	.even
		{background-color:	#D8D8D8;}
    .container{
    	display:inline-block;
    	vertical-align: top;
    	}
    .logout{margin-left: 90%;}
    .dumb2{width: 50px;}
	.btn
		{
		margin-left:1100px;
		}
		
    </style>
</head>
<body>
<a href="/">Login</a>
<a href="/tasks">Tasks Dashboard</a>
<a href="/tasks/new">Create</a>
<a href="/logout">Logout</a>


 <h1>Tasks Dashboard</h1>
<h1> Welcome, ${user.getName()} ID: ${user.getId()}
</h1>
<a href="/orderhigh">High-Low | </a>
<a href="/orderlow">  Low-High</a>

	<table>
	<thead>
	<tr>
		<th>Task</th>
		<th>Creator</th>
		<th>Assignee</th>
		<th>Priority</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${tasks}" var="task">
		<tr>
			<td><a href="/tasks/${task.getId()}"> ${task.getTaskname()}</a></td>
			<td>${task.getCreator().getName()}</td>
			<td>${task.getAssignee().getName()}</td>
			<td>
				<c:if test="${task.getPriority()==1}">LOW</c:if>
				<c:if test="${task.getPriority()==2}">MED</c:if>
				<c:if test="${task.getPriority()==3}">HIGH</c:if>			
			</td>
		</tr>
	</c:forEach>
	</tbody>
	</table>
	<br>
	<form class="btn" action="/tasks/new">
		<button type="submit">Create Task</button>
	</form>
	
	
	
</body>
</html>