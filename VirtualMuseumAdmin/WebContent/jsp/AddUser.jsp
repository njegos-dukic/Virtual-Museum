<%@ page import="org.unibl.etf.virtualmuseum.entities.UserEntity"%>
<%@ page import="org.unibl.etf.virtualmuseum.services.UserService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:useBean id="user" scope="session" class="org.unibl.etf.virtualmuseum.beans.UserBean"/>  
 
<%
	UserEntity me = null;

	if (!user.isLoggedIn()) {
		response.sendRedirect("Login.jsp");
	}
	
	else if (request.getParameter("logout") != null) {
		user.logOut();
		session.invalidate();
		response.sendRedirect("Login.jsp");
	}
	
	else {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String rePassword = request.getParameter("re-password");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String isAdmin = request.getParameter("admin");
	    if(username != null && password != null && rePassword != null && firstName != null && lastName != null && email != null) {
	    	if (password.equals(rePassword)) {
		    	me = new UserEntity(0, username, password, firstName, lastName, email, ((isAdmin != null) ? true : false));
		    	UserService.insert(me);
		    	response.sendRedirect("Users.jsp");
	    	}
	    }
	}
%>

<!DOCTYPE html>
<html lang="en">
	<head>
    	<meta charset="UTF-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    	<title>Virtual Museum Admin</title>
    	<link href="../css/Header.css" rel="stylesheet" type="text/css">
    	<link href="../css/AddEdit.css" rel="stylesheet" type="text/css">
    	<link href="../css/Menu.css" rel="stylesheet" type="text/css">
    	<link rel="icon" href="../images/logo.png">
    	<link rel="preconnect" href="https://fonts.googleapis.com">
    	<link rel="preconnect" href="https://fonts.gstatic.com">
    	<link href="https://fonts.googleapis.com/css2?family=Bebas+Neue&display=swap" rel="stylesheet"> 				
	</head>
	
	<body>
		<div class="header-container">
           <div class="header-logo-container">
				<a class="header-logo-container" href="Homepage.jsp">
			    	<img class="header-logo" src="../images/logo.png" />
			    </a>
            </div>

			<div class="header-title">
                VIRTUAL MUSEUM ADMINISTRATION
            </div>

			<div class="header-login-container">
				<form class="header-login-container" action="#" method="post">
	                <div class="header-login-label">
	                    <%= user.getUsername() %> 
	                </div>
	                <input type="hidden" name="logout" value="1" />
		            <input type="image" class="header-login-image" src="../images/login.png" />
                </form>
            </div>
		</div>
		
		<div class="menu-container">
			<a class="menu-item right-margin-3" href="Homepage.jsp">
		        HOME 
		    </a>
			<a class="menu-item right-margin-3" href="Museums.jsp">
		        MUSEUMS
		    </a>
		    <a class="menu-item left-margin-3 right-margin-3" href="Tours.jsp">
		        TOURS
		    </a>
		    <a style="background-color: #8b84bf;" class="menu-item left-margin-3 right-margin-3" href="Users.jsp">
		        USERS
		    </a>
		    <a class="menu-item left-margin-3" href="Logs.jsp">
		        LOGS
		    </a>
		</div>
		
		<div class="museum-edit-container">
	        <form action="#" method="post" class="museum-edit-form">
	            <div class="museum-edit-single-input">
	                <label class="museum-edit-input-label" for="username">Username: </label>
	                <input class="museum-edit-input-field" name="username" type="text" required>
	            </div>
	            <div class="museum-edit-single-input">
	                <label class="museum-edit-input-label" for="password">Password: </label>
	                <input class="museum-edit-input-field" name="password" type="password" required>
	            </div>
	             <div class="museum-edit-single-input">
	                <label class="museum-edit-input-label" for="re-password">Re-Enter Password: </label>
	                <input class="museum-edit-input-field" name="re-password" type="password" required>
	            </div>
	            <div class="museum-edit-single-input">
	                <label class="museum-edit-input-label" for="firstName">First name: </label>
	                <input class="museum-edit-input-field" name="firstName" type="text" required>
	            </div>
	            <div class="museum-edit-single-input">
	                <label class="museum-edit-input-label" for="lastName">Last name: </label>
	                <input class="museum-edit-input-field" name="lastName" type="text" required>
	            </div>
	            <div class="museum-edit-single-input">
	                <label class="museum-edit-input-label" for="email">Email: </label>
	                <input class="museum-edit-input-field" name="email" type="email" required>
	            </div>
	            <div class="museum-edit-single-input">
	                <label class="museum-edit-input-label" for="admin">Admin: </label>
	                <input class="museum-edit-input-field" name="admin" type="checkbox">
	            </div>
	            <div class="museum-edit-single-input margin-botton-4perc">
	                <input class="museum-edit-input-field" type="submit" value="Add User">
	            </div>
	        </form>
	    </div>
	</body>
</html>