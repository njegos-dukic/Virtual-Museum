<%@ page import="org.unibl.etf.virtualmuseum.entities.MuseumEntity"%>
<%@ page import="org.unibl.etf.virtualmuseum.services.MuseumService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:useBean id="user" scope="session" class="org.unibl.etf.virtualmuseum.beans.UserBean"/>  
 
<%
	if (!user.isLoggedIn()) {
		response.sendRedirect("Login.jsp");
	}
	
	else if (request.getParameter("logout") != null) {
		user.logOut();
		session.invalidate();
		response.sendRedirect("Login.jsp");
	}
	
	else {
		
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
    	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    	<link href="https://fonts.googleapis.com/css2?family=Bebas+Neue&display=swap" rel="stylesheet"> 				
	
		<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	
		<script>
		 		$(document).ready(function () {
		 			$("#remove-selected-images").click(function () {
		 				$("#file-uploader").val([]);
		 			})
		 			
		 			$("#remove-selected-video").click(function () {
		 				$("#video-uploader").val([]);
		 			})
		 		});
		 		
				function beforeSubmit() {
					var uploader = document.getElementById("file-uploader");
					if (uploader.files.length < 5) {
						alert("Upload at least 5 images!");
						return false;
					}
					
					if (uploader.files.length > 10) {
						alert("Upload no more than 10 images!");
						return false;
					}
					
					var videoUploader = document.getElementById("video-uploader");
					var ytVideoUploader = document.getElementById("yt-video-uploader");
					
					if (videoUploader.files.length == 0 && ytVideoUploader.value == "") {
						alert("Attach artifact video or YouTube link!")
						return false;
					}
					
					if (videoUploader.files.length != 0 && ytVideoUploader.value != "") {
						alert("Submit only local video or YouTube link, not both!")
						return false;
					}
					
				    return true;
				}
		</script>
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
		
		<div class="museum-edit-container">
	        <form id="add-tour" action="#" method="post" class="museum-edit-form">
	        	<div class="museum-edit-single-input">
	                <label class="museum-edit-input-label" for="museum">Museum: </label>
	                <select id="museum-select" class="museum-edit-input-field" name="museum" required>
		                <% for(MuseumEntity me : MuseumService.selectAll()) { %>
		                	<option value="<%= me.getId() %>"> <%= me.getName() %> </option>
		                <% } %>
	                </select>
	            </div>
	            <div class="museum-edit-single-input">
	                <label class="museum-edit-input-label" for="date">Tour date: </label>
	                <input class="museum-edit-input-field" name="date" type="date" required>
	            </div>
	            <div class="museum-edit-single-input">
	                <label class="museum-edit-input-label" for="time">Start time: </label>
	                <input class="museum-edit-input-field" name="time" type="time" required>
	            </div>
	            <div class="museum-edit-single-input">
	                <label class="museum-edit-input-label" for="duration">Duration (H): </label>
	                <input class="museum-edit-input-field" name="duration" type="number" step=".5" required>
	            </div>
	            <div class="museum-edit-single-input">
	                <label class="museum-edit-input-label" for="image-artifacts">Image artifacts: </label>
	                <input id="file-uploader" class="museum-edit-input-field" name="image-artifacts" type="file" required accept="image/*" multiple>
	            	<img id="remove-selected-images" class="museum-edit-input-label" style="height: min(calc(8px + 1.2vw), 16px); width: min(calc(8px + 1.2vw), 16px);" src="../images/cancel.png"/>
	            </div>
	            <div class="museum-edit-single-input">
	                <label class="museum-edit-input-label" for="video-artifact">Video artifact: </label>
	                <input id="video-uploader" class="museum-edit-input-field" name="video-artifacts" type="file" accept="video/*">
	            	<img id="remove-selected-video" class="museum-edit-input-label" style="height: min(calc(8px + 1.2vw), 16px); width:  min(calc(8px + 1.2vw), 16px);" src="../images/cancel.png"/>
	            </div>
	             <div class="museum-edit-single-input">
	                <label class="museum-edit-input-label" for="yt-video-artifact">YouTube video artifact: </label>
	                <input id="yt-video-uploader" class="museum-edit-input-field" name="yt-video-artifacts" type="text">
	            </div>
	            <div class="museum-edit-single-input margin-botton-4perc">
	                <input class="museum-edit-input-field" type="submit" onclick="return beforeSubmit()" value="Add Tour">
	            </div>
	        </form>
	    </div>
	</body>
</html>