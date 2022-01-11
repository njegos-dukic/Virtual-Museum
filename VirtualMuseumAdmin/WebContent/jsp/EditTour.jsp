<%@ page import="org.unibl.etf.virtualmuseum.entities.TourEntity"%>
<%@ page import="org.unibl.etf.virtualmuseum.services.TourService"%>
<%@ page import="org.unibl.etf.virtualmuseum.entities.MuseumEntity"%>
<%@ page import="org.unibl.etf.virtualmuseum.services.MuseumService"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.text.DateFormat"%>
<%@ page import="java.sql.Timestamp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:useBean id="user" scope="session" class="org.unibl.etf.virtualmuseum.beans.UserBean"/>  

<%
	TourEntity te = null;
	
	if (!user.isLoggedIn()) {
		response.sendRedirect("Login.jsp");
	}
	
	else if (request.getParameter("logout") != null) {
		user.logOut();
		session.invalidate();
		response.sendRedirect("Login.jsp");
	}
	
	else {
		String tid = request.getParameter("current-tid");
	    String mid = request.getParameter("current-mid");
		String tname = request.getParameter("current-tname");
		String startDate = request.getParameter("current-date");
		String startTime = request.getParameter("current-time");
		String duration = request.getParameter("current-duration");

	    if(tid != null && mid != null && tname != null && startDate != null && startTime != null && duration != null) {
	    	te = new TourEntity(Integer.parseInt(tid), Integer.parseInt(mid), tname, Timestamp.valueOf(startDate + " " + startTime), Double.parseDouble(duration));
	    }
	}
	
	if (te == null) {
		response.sendRedirect("Tours.jsp");
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
		<%
			Date date = new Date(te.getStartDateTime().getTime()); 
      		DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        	DateFormat timeFormatter = new SimpleDateFormat("HH:mm");	
        	DateFormat sqlDateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        	DateFormat timeFormatterWithSeconds = new SimpleDateFormat("HH:mm:ss");
        %>
		<div class="museum-edit-container">
	        <form id="add-tour" action="Tours.jsp" method="post" class="museum-edit-form">
	        	<input name="tid" type="hidden" value=<%= te.getId() %>>
	        	<div class="museum-edit-single-input">
	                <label class="museum-edit-input-label" for="mid">Museum: </label>
	                <select id="museum-select" class="museum-edit-input-field" name="mid" required>
		                <% for(MuseumEntity me : MuseumService.selectAll()) { %>
		                	<option value="<%= me.getId() %>" <% if (me.getId() == te.getMuseumId()) { %> selected <% } %>> <%= me.getName() %> </option>
		                <% } %>
	                </select>
	            </div>
	            <div class="museum-edit-single-input">
	                <label class="museum-edit-input-label" for="tname">Tour name: </label>
	                <input class="museum-edit-input-field" name="tname" type="text" value="<%= te.getName() %>" required>
	            </div>
	            <div class="museum-edit-single-input">
	                <label class="museum-edit-input-label" for="date">Tour date: </label>
	                <input class="museum-edit-input-field" name="date" type="date" value="<%= sqlDateFormatter.format(date) %>" required>
	            </div>
	            <div class="museum-edit-single-input">
	                <label class="museum-edit-input-label" for="time">Start time: </label>
	                <input class="museum-edit-input-field" name="time" type="time" value="<%= timeFormatterWithSeconds.format(date) %>" required>
	            </div>
	            <div class="museum-edit-single-input">
	                <label class="museum-edit-input-label" for="duration">Duration (H): </label>
	                <input class="museum-edit-input-field" name="duration" type="number" step=".5" value="<%= te.getDuration() %>" required>
	            </div>
	            <div class="museum-edit-single-input margin-botton-4perc">
	                <input class="museum-edit-input-field" type="submit" value="Update Tour">
	            </div>
	        </form>
	    </div>
	</body>
</html>