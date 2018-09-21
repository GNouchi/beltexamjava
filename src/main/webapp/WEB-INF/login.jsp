<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<style type="text/css">
	.container
		{
		display:inline-block;
		vertical-align: top;
		margin-left : 200px;
		border: 1px solid black;
		padding:20px;
		padding-top:0px;
		}
	.indent	
		{
		margin-left:200px;
		display: inline-block;		
		}
	.logout
		{
		vertical-align: top;
		display:inline-block;
		text-indent:50%;		
		}
	h1
		{
		margin-top: 5px;
		}
	.btn
		{
		margin-top: 5px;
		margin-left:78% ;
		}
	.dumb1{
		width:47%;
		display:inline-block;
		}
	</style>
</head>
<body>
<a href="/">Login</a>
<a href="/tasks">Tasks Dashboard</a>
<a href="/tasks/new">Create</a>
<a href="/logout">Logout</a>

<h1> Login & Registration</h1>
<h1 class="indent"> Welcome!</h1>   
 <br>
   
<div class="container">
    <h1>Register!</h1>
    <p><form:errors path="user.*"/></p>    
    <form:form method="POST" action="/registration" modelAttribute="user">
    <table>
        <tbody>
        <tr>
            <td>
                <form:label path="name">Name:</form:label>
            </td>
            <td>
                <form:input type="text" path="name"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="email">Email:</form:label>
            </td>
            <td>
                <form:input type="email" path="email"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="password">Password:</form:label>
            </td>
            <td>
                <form:password path="password"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="passwordConfirmation">Password confirm:</form:label>
            </td>
            <td>
                <form:password path="passwordConfirmation"/>
            </td>
        </tr>
    </tbody>
</table>
<button class="btn" type="submit">Register</button>
    </form:form>


</div>    
<div class="container">
    <h1>Login!</h1>
    <p><c:out value="${error}" /></p>
    <form method="post" action="/login">
        <table>
            <tbody>                
                <tr>
                    <td>
                        <label type="email" for="email">Email</label>
                    </td>
                    <td>
                        <input type="text" id="email" name="email"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="password">Password</label>
                    </td>
                    <td>
                        <input type="password" id="password" name="password"/>
                    </td>
                </tr>
            </tbody>
            </table>
        <button class="btn" type="submit">Login</button>
    </form>    
</div>
    
</body>
</html>