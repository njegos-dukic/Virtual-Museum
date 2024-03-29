<%@ page import="org.unibl.etf.virtualmuseum.entities.UserEntity"%>
<%@ page import="org.unibl.etf.virtualmuseum.services.UserService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<jsp:useBean id="user" scope="session" class="org.unibl.etf.virtualmuseum.beans.UserBean"/>  
 
<%
	UserEntity ue = null;

	if (!user.isLoggedIn()) {
		response.sendRedirect("Login.jsp");
	}
	
	else if (request.getParameter("logout") != null) {
		user.logOut();
		session.invalidate();
		response.sendRedirect("Login.jsp");
	}
	
	else {
		String id = request.getParameter("current-id");
	    String username = request.getParameter("current-username");
		String firstName = request.getParameter("current-firstName");
		String lastName = request.getParameter("current-lastName");
		String email = request.getParameter("current-email");
	    if(id != null && username != null && firstName != null && lastName != null && email != null) {
	    	ue = new UserEntity(Integer.parseInt(id), username, firstName, lastName, email);
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
	        <form action="Users.jsp" method="post" class="museum-edit-form">
	        	<input name="id" type="hidden" value=<%= (ue == null ? "" : ue.getId()) %>>
	            <div class="museum-edit-single-input">
	                <label class="museum-edit-input-label" for="username">Username: </label>
	                <input class="museum-edit-input-field" name="username" type="text" value="<%= (ue == null ? "" : ue.getUsername()) %>" required>
	            </div>
	            <div class="museum-edit-single-input">
	                <label class="museum-edit-input-label" for="firstName">First name: </label>
	                <input class="museum-edit-input-field" name="firstName" type="text" value="<%= (ue == null ? "" : ue.getFirstName()) %>" required>
	            </div>
	            <div class="museum-edit-single-input">
	                <label class="museum-edit-input-label" for="lastName">Last name: </label>
	                <input class="museum-edit-input-field" name="lastName" type="text" value="<%= (ue == null ? "" : ue.getLastName()) %>" required>
	            </div>
	            <div class="museum-edit-single-input">
	                <label class="museum-edit-input-label" for="email">Email: </label>
	                <input class="museum-edit-input-field" name="email" type="email" value="<%= (ue == null ? "" : ue.getEmail()) %>" required>
	            </div>
	            <div class="museum-edit-single-input margin-botton-4perc">
	                <input class="museum-edit-input-field" type="submit" value="Update User">
	            </div>
	        </form>
	    </div>
	</body>
</html>