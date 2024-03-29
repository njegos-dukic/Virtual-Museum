<%@ page import="org.unibl.etf.virtualmuseum.services.UserService"%>
<%@ page import="org.unibl.etf.virtualmuseum.entities.UserEntity"%>
<%@ page import="org.unibl.etf.virtualmuseum.beans.UserBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:useBean id="user" scope="session" class="org.unibl.etf.virtualmuseum.beans.UserBean">
	<jsp:setProperty name="user" property= "username" value=""/> 
	<jsp:setProperty name="user" property= "password" value=""/> 
</jsp:useBean>  
 
<%
	if (!user.isLoggedIn()) {
		response.sendRedirect("Login.jsp");
	}
	
	if (request.getParameter("logout") != null) {
		user.logOut();
		session.invalidate();
		response.sendRedirect("Login.jsp");
	}
	
	String id = request.getParameter("delete-id");
	boolean deleteStatus = true;
    if (id != null) {
    	deleteStatus = UserService.delete(Integer.parseInt(id));
    }
    
    String toggleAdmin = request.getParameter("toggle-admin-id");
    if (toggleAdmin != null) {
    	String toggleAdminIsAdmin = request.getParameter("toggle-admin-is-admin");
    	UserService.toggleAdmin(Integer.parseInt(toggleAdmin), "true".equals(toggleAdminIsAdmin));
    }
    
    String toggleApproved = request.getParameter("toggle-approved-id");
    if (toggleApproved != null) {
    	UserService.toggleApproved(Integer.parseInt(toggleApproved));
    }
    
    String toggleBlocked = request.getParameter("toggle-blocked-id");
    if (toggleBlocked != null) {
    	UserService.toggleBlocked(Integer.parseInt(toggleBlocked));
    }
    
    String toggleReset = request.getParameter("toggle-reset-id");
    if (toggleReset != null) {
    	UserService.toggleReset(Integer.parseInt(toggleReset));
    }
    
    UserEntity editedUe = null;
	String editedId = request.getParameter("id");
	String editedUsername = request.getParameter("username");
	String editedFirstName = request.getParameter("firstName");
	String editedLastName = request.getParameter("lastName");
	String editedEmail = request.getParameter("email");
	if(editedId != null && editedUsername != null && editedFirstName != null && editedLastName != null && editedEmail != null) {
		editedUe = new UserEntity(Integer.parseInt(editedId), editedUsername, editedFirstName, editedLastName, editedEmail);
		UserService.update(editedUe);
	}
%>

	
<!DOCTYPE html>
<html lang="en">
	<head>
    	<meta charset="UTF-8">
    	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    	<title>Virtual Museum Admin</title>
    	<link href="../css/Header.css" rel="stylesheet" type="text/css">
    	<link href="../css/Menu.css" rel="stylesheet" type="text/css">
    	<link href="../css/Content.css" rel="stylesheet" type="text/css">
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
		
		<a href="AddUser.jsp" class="new-museum-container" >Add new user</a>
		
		<div class="content-container">
			<div class="content-custom-table-header">
		    	<div style="width: 15%;" class="content-custom-table-column right-margin-2">
		        	USERNAME
		        </div>
		        <div style="width: 15%;" class="content-custom-table-column left-right-margin-2">
		        	NAME
		        </div>
				<div style="width: 15%;" class="content-custom-table-column left-right-margin-2">
		       		SURNAME
		        </div>
		        <div style="width: 15%;" class="content-custom-table-column left-right-margin-2">
		        	EMAIL
		        </div>
		        <div style="width: 6.66%;" class="content-custom-table-column left-right-margin-2">
		        	ADMIN
		        </div>
		        <div style="width: 6.66%;" class="content-custom-table-column left-right-margin-2">
			    	APPROVED
		        </div>
		        <div style="width: 6.66%;" class="content-custom-table-column left-right-margin-2">
		        	BLOCKED
		        </div>
		        <div style="width: 6.66%;" class="content-custom-table-column left-right-margin-2">
		        	RESET
		        </div>
		        <div style="width: 6.66%;" class="content-custom-table-column left-right-margin-2">
		        	EDIT
		        </div>
		        <div style="width: 6.66%;" class="content-custom-table-column left-right-margin-2">
		        	REMOVE
		        </div>
			</div>	

			<% for (UserEntity ue : UserService.selectAll()) { %>
				<div class="content-custom-table-record-container">
			    	<div style="width: 15%;" class="content-custom-table-column content-custom-table-data-column-color right-margin-2">
			        	<%= ue.getUsername() %>
			        </div>
			        <div style="width: 15%;" class="content-custom-table-column content-custom-table-data-column-color left-right-margin-2">
			        	<%= ue.getFirstName() %>
			        </div>
			        <div style="width: 15%;" class="content-custom-table-column content-custom-table-data-column-color left-right-margin-2">
			        	<%= ue.getLastName() %>
			        </div>
			        <div style="width: 15%;" class="content-custom-table-column content-custom-table-data-column-color left-right-margin-2">
			        	<%= ue.getEmail() %>
			        </div>
			        <div style="width: 6.66%; justify-content: center; " class="content-custom-table-column content-custom-table-data-column-color left-right-margin-2">
			        	<form style="height: min(calc(8px + 1.5vw), 16px);" action="#" method=post>
			        		<input type="hidden" name="toggle-admin-id" value="<%= ue.getId() %>" />
			        		<input type="hidden" name="toggle-admin-is-admin" value="<%= ue.isAdmin() ? "true" : "false" %>" />
			        		<input type="image" style="height: min(calc(8px + 1.5vw), 16px);" src="../images/<%= ue.isAdmin() ? "check.png" : "cancel.png" %>" />
			        	</form>
			        </div>
			        <div style="width: 6.66%; justify-content: center; " class="content-custom-table-column content-custom-table-data-column-color left-right-margin-2">
			        	<form style="height: min(calc(8px + 1.5vw), 16px);" action="#" method=post>
			        		<input type="hidden" name="toggle-approved-id" value="<%= ue.getId() %>" />
			        		<input type="image" style="height: min(calc(8px + 1.5vw), 16px);" src="../images/<%= ue.isApproved() ? "check.png" : "cancel.png" %>" />
			        	</form>
			        </div>
			        <div style="width: 6.66%; justify-content: center; " class="content-custom-table-column content-custom-table-data-column-color left-right-margin-2">
			        	<form style="height: min(calc(8px + 1.5vw), 16px);" action="#" method=post>
			        		<input type="hidden" name="toggle-blocked-id" value="<%= ue.getId() %>" />
			        		<input type="image" style="height: min(calc(8px + 1.5vw), 16px);" src="../images/<%= ue.isBlocked() ? "check.png" : "cancel.png" %>" />
			        	</form>
			        </div>
			        <div style="width: 6.66%; justify-content: center; " class="content-custom-table-column content-custom-table-data-column-color left-right-margin-2">
			        	<form style="height: min(calc(8px + 1.5vw), 16px);" action="#" method=post>
			        		<input type="hidden" name="toggle-reset-id" value="<%= ue.getId() %>" />
			        		<input type="image" style="height: min(calc(8px + 1.5vw), 16px);" src="../images/<%= ue.isPasswordReset() ? "unlocked.png" : "locked.png" %>" />
			        	</form>
			        </div>
			        <div style="width: 6.66%; justify-content: center; overflow: hidden; height: min(calc(8px + 1.5vw), 16px);" class="content-custom-table-column content-custom-table-data-column-color left-right-margin-2">
			        	<form style="height: min(calc(8px + 1.5vw), 16px);" action="EditUser.jsp" method=post>
			        		<input type="hidden" name="current-id" value="<%= ue.getId() %>">
			        		<input type="hidden" name="current-username" value="<%= ue.getUsername() %>">
			        		<input type="hidden" name="current-firstName" value="<%= ue.getFirstName() %>">
			        		<input type="hidden" name="current-lastName" value="<%= ue.getLastName() %>">
			        		<input type="hidden" name="current-email" value="<%= ue.getEmail() %>">
			        		<input style="height: min(calc(8px + 1.5vw), 16px);" src="../images/edit.png" type="image">
			        	</form>
			        </div>
			        <div style="width: 6.66%; justify-content: center; overflow: hidden; height: min(calc(8px + 1.5vw), 16px);" class="content-custom-table-column content-custom-table-data-column-color left-margin-2">
			        	<form action="#" method="post">
			        		<input type="hidden" name="delete-id" value="<%= ue.getId() %>" >
			        		<input style="height: min(calc(8px + 1.5vw), 16px);" src="../images/remove.png" type="image">
			        	</form>
			        </div>
			    </div>
			<% } %>
		</div>
		<% if(!deleteStatus) { %>
			<script>alert("Can't delete user!")</script>
		<% } %>	
	</body>
</html>