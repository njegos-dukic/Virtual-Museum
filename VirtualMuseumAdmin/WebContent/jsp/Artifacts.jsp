<%@ page import="org.unibl.etf.virtualmuseum.services.TourService"%>
<%@ page import="org.unibl.etf.virtualmuseum.entities.TourEntity"%>
<%@ page import="org.unibl.etf.virtualmuseum.beans.UserBean"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.text.DateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:useBean id="user" scope="session" class="org.unibl.etf.virtualmuseum.beans.UserBean">
	<jsp:setProperty name="user" property= "username" value=""/> 
	<jsp:setProperty name="user" property= "password" value=""/> 
</jsp:useBean>  
	
	
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
		
		<div class="content-container">
			<div class="content-custom-table-header">
		    	<div style="width: 17%;" class="content-custom-table-column right-margin-2">
		        	MUSEUM
		        </div>
		        <div style="width: 17%;" class="content-custom-table-column left-right-margin-2">
		        	TOUR NAME
		        </div>
		        <div style="width: 17%;" class="content-custom-table-column left-right-margin-2">
		        	START DATE
		        </div>
		        <div style="width: 17%;" class="content-custom-table-column left-right-margin-2">
		        	START TIME
		        </div>
		        <div style="width: 17%;" class="content-custom-table-column left-right-margin-2">
		        	DURATION
		        </div>
		        <div style="width: 5%;" class="content-custom-table-column left-right-margin-2">
		        	ARTIFACTS
		        </div>
		        <div style="width: 5%;" class="content-custom-table-column left-right-margin-2">
		        	EDIT
		        </div>
		        <div style="width: 5%;" class="content-custom-table-column left-margin-2">
		        	REMOVE
		        </div>
			</div>
			<% for (TourEntity te : TourService.selectAll()) { 
        			Date date = new Date(te.getStartDateTime().getTime()); 
        			DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        			DateFormat timeFormatter = new SimpleDateFormat("HH:mm");
        	%>
				<div class="content-custom-table-record-container">
			    	<div style="width: 17%;" class="content-custom-table-column content-custom-table-data-column-color right-margin-2">
			        	<%= te.getMuseumName() %>
			        </div>
			        <div style="width: 17%;" class="content-custom-table-column content-custom-table-data-column-color left-right-margin-2">
			        	<%= te.getName() %>
			        </div>
			        <div style="width: 17%;" class="content-custom-table-column content-custom-table-data-column-color left-right-margin-2">
			        	<%= (dateFormatter.format(date)) %>
			        </div>
			        <div style="width: 17%;" class="content-custom-table-column content-custom-table-data-column-color left-right-margin-2">
			        	<%= (timeFormatter.format(date)) %> 
			        </div>
			        <div style="width: 17%;" class="content-custom-table-column content-custom-table-data-column-color left-right-margin-2">
			        	<%= te.getDuration() + " H" %>
			        </div>
			        <a href="#" target="_blank" style="width: 5%; justify-content: center; overflow: hidden;" class="content-custom-table-column content-custom-table-data-column-color left-right-margin-2" > 	
				 		<img style="height: min(calc(8px + 1.5vw), 16px);" src="../images/artifact.png" />
			        </a>
			        <div style="width: 5%; justify-content: center; overflow: hidden; height: min(calc(8px + 1.5vw), 16px);" class="content-custom-table-column content-custom-table-data-column-color left-right-margin-2">
			        	<form style="height: min(calc(8px + 1.5vw), 16px);" action="EditMuseum.jsp" method=post>
			        		<input type="hidden" name="current-tid" value="<%= te.getId() %>">
			        		<input type="hidden" name="current-mid" value="<%= te.getMuseumId() %>">
			        		<input type="hidden" name="current-mname" value="<%= te.getMuseumName() %>">
			        		<input type="hidden" name="current-tname" value="<%= te.getName() %>">
			        		<input type="hidden" name="current-start" value="<%= te.getStartDateTime() %>">
			        		<input type="hidden" name="current-duration" value="<%= te.getDuration() %>">
			        		<input style="height: min(calc(8px + 1.5vw), 16px);" src="../images/edit.png" type="image">
			        	</form>
			        </div>
			        <div style="width: 5%; justify-content: center; overflow: hidden; height: min(calc(8px + 1.5vw), 16px);" class="content-custom-table-column content-custom-table-data-column-color left-margin-2">
			        	<form action="#" method="post">
			        		<input type="hidden" name="delete-id" value="<%= te.getId() %>" >
			        		<input style="height: min(calc(8px + 1.5vw), 16px);" src="../images/remove.png" type="image">
			        	</form>
			        </div>
			    </div>
			<% } %>
		</div>
	</body>
</html>