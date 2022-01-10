<%@ page import="org.unibl.etf.virtualmuseum.services.MuseumService"%>
<%@ page import="org.unibl.etf.virtualmuseum.entities.MuseumEntity"%>
<%@ page import="org.unibl.etf.virtualmuseum.beans.UserBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:useBean id="user" scope="session" class="org.unibl.etf.virtualmuseum.beans.UserBean">
	<jsp:setProperty name="user" property= "username" value=""/> 
	<jsp:setProperty name="user" property= "password" value=""/> 
</jsp:useBean>  
 
<%
	if (request.getParameter("username") != null && request.getParameter("password") != null) {
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		user.login();
	}

	if (!user.isLoggedIn()) {
		response.sendRedirect("Login.jsp");
	}
	
	if (request.getParameter("logout") != null) {
		user.logOut();
		session.invalidate();
		response.sendRedirect("Login.jsp");
	}
	
	String id = request.getParameter("delete-id");
    if (id != null) {
    	MuseumService.delete(Integer.parseInt(id));
    }
    
    MuseumEntity editedMe = null;
	String editedId = request.getParameter("id");
	String editedName = request.getParameter("name");
	String editedAddress = request.getParameter("address");
	String editedPhone = request.getParameter("phone");
	String editedCity = request.getParameter("city");
	String editedCountry = request.getParameter("country");
	String editedType = request.getParameter("type");
	String editedMaps = request.getParameter("maps");
	if(editedId != null && editedName != null && editedAddress != null && editedPhone != null && editedCity != null && editedCountry != null && editedType != null && editedMaps != null) {
		editedMe = new MuseumEntity(Integer.parseInt(editedId), editedName, editedAddress, editedPhone, editedCity, editedCountry, editedType, editedMaps);
		MuseumService.update(editedMe);
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
			<a style="background-color: #8b84bf;" class="menu-item right-margin-3" href="Museums.jsp">
		        MUSEUMS
		    </a>
		    <a class="menu-item left-margin-3 right-margin-3" href="Tours.jsp">
		        TOURS
		    </a>
		    <a class="menu-item left-margin-3 right-margin-3" href="Users.jsp">
		        USERS
		    </a>
		    <a class="menu-item left-margin-3" href="Logs.jsp">
		        LOGS
		    </a>
		</div>
		
		<a href="AddMuseum.jsp" class="new-museum-container" >Add new Museum</a>	
		
		<div class="content-container">
			<div class="content-custom-table-header">
		    	<div class="content-custom-table-column right-margin-2">
		        	NAME
		        </div>
		        <div class="content-custom-table-column left-right-margin-2">
		        	ADDRESS
		        </div>
				<div class="content-custom-table-column left-right-margin-2">
		       		PHONE
		        </div>
		        <div class="content-custom-table-column left-right-margin-2">
		        	CITY
		        </div>
		        <div class="content-custom-table-column left-right-margin-2">
		        	COUNTRY
		        </div>
		        <div class="content-custom-table-column left-right-margin-2">
			    	TYPE
		        </div>
		        <div style="width: 3.33%;" class="content-custom-table-column left-right-margin-2">
		        	LOCATION
		        </div>
		        <div style="width: 3.33%;" class="content-custom-table-column left-right-margin-2">
		        	EDIT
		        </div>
		        <div style="width: 3.33%;" class="content-custom-table-column left-margin-2">
		        	REMOVE
		        </div>
			</div>

			<% for (MuseumEntity me : MuseumService.selectAll()) { %>
				<div class="content-custom-table-record-container">
			    	<div class="content-custom-table-column content-custom-table-data-column-color right-margin-2">
			        	<%= me.getName() %>
			        </div>
			        <div class="content-custom-table-column content-custom-table-data-column-color left-right-margin-2">
			        	<%= me.getAddress() %>
			        </div>
			        <div class="content-custom-table-column content-custom-table-data-column-color left-right-margin-2">
			        	<%= me.getPhone() %>
			        </div>
			        <div class="content-custom-table-column content-custom-table-data-column-color left-right-margin-2">
			        	<%= me.getCity() %>
			        </div>
			        <div class="content-custom-table-column content-custom-table-data-column-color left-right-margin-2">
			        	<%= me.getCountry() %>
			        </div>
			        <div class="content-custom-table-column content-custom-table-data-column-color left-right-margin-2">
			        	<%= me.getType() %>
			        </div>
			        <a href="<%= me.getMaps() %>" target="_blank" style="width: 3.33%; justify-content: center; overflow: hidden;" class="content-custom-table-column content-custom-table-data-column-color left-right-margin-2" > 	
				 		<img style="height: min(calc(8px + 1.5vw), 16px);" src="../images/location-pin.png" />
			        </a>
			        <div style="width: 3.33%; justify-content: center; overflow: hidden; height: min(calc(8px + 1.5vw), 16px);" class="content-custom-table-column content-custom-table-data-column-color left-right-margin-2">
			        	<form style="height: min(calc(8px + 1.5vw), 16px);" action="EditMuseum.jsp" method=post>
			        		<input type="hidden" name="current-id" value="<%= me.getId() %>">
			        		<input type="hidden" name="current-name" value="<%= me.getName() %>">
			        		<input type="hidden" name="current-address" value="<%= me.getAddress() %>">
			        		<input type="hidden" name="current-phone" value="<%= me.getPhone() %>">
			        		<input type="hidden" name="current-city" value="<%= me.getCity() %>">
			        		<input type="hidden" name="current-country" value="<%= me.getCountry() %>">
			        		<input type="hidden" name="current-type" value="<%= me.getType() %>">
			        		<input type="hidden" name="current-maps" value="<%= me.getMaps() %>">
			        		<input style="height: min(calc(8px + 1.5vw), 16px);" src="../images/edit.png" type="image">
			        	</form>
			        </div>
			        <div style="width: 3.33%; justify-content: center; overflow: hidden; height: min(calc(8px + 1.5vw), 16px);" class="content-custom-table-column content-custom-table-data-column-color left-margin-2">
			        	<form action="#" method="post">
			        		<input type="hidden" name="delete-id" value="<%= me.getId() %>" >
			        		<input style="height: min(calc(8px + 1.5vw), 16px);" src="../images/remove.png" type="image">
			        	</form>
			        </div>
			    </div>
			<% } %>
		</div>
	</body>
</html>