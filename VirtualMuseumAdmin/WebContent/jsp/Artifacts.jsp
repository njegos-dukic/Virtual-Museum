<%@ page import="org.unibl.etf.virtualmuseum.services.TourService"%>
<%@ page import="org.unibl.etf.virtualmuseum.entities.TourEntity"%>
<%@ page import="org.unibl.etf.virtualmuseum.beans.UserBean"%>
<%@ page import="java.util.stream.Stream"%>
<%@ page import="java.util.stream.Collectors"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Base64" %>
<%@ page import="java.io.File"%>
<%@ page import="java.nio.file.Paths"%>
<%@ page import="java.nio.file.Path"%>
<%@ page import="java.nio.file.Files"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.text.DateFormat"%>
<%@ page import="java.sql.Timestamp"%>
<%@ page import="java.util.HashMap" %>
<%@ page import="org.apache.commons.fileupload.FileUploadException" %>
<%@ page import="org.apache.commons.fileupload.disk.DiskFileItemFactory" %>
<%@ page import="org.apache.commons.io.FilenameUtils" %>
<%@ page import="org.apache.commons.fileupload.servlet.ServletFileUpload" %>
<%@ page import="org.apache.commons.fileupload.FileItem" %>
<%@ page import="org.apache.commons.io.FileUtils" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="java.io.FileOutputStream" %>
<%@ page import="java.util.ArrayList" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:useBean id="user" scope="session" class="org.unibl.etf.virtualmuseum.beans.UserBean">
	<jsp:setProperty name="user" property= "username" value=""/> 
	<jsp:setProperty name="user" property= "password" value=""/> 
</jsp:useBean>

<%
	String artifactId = "";
	if (!user.isLoggedIn()) {
		response.sendRedirect("Login.jsp");
	}
	
	else if (request.getParameter("logout") != null) {
		user.logOut();
		session.invalidate();
		response.sendRedirect("Login.jsp");
	}

	else {
		artifactId = request.getParameter("tour-id");
		if (artifactId == null)
			response.sendRedirect("Tours.jsp");
	}
%>
	
	
<!DOCTYPE html>
<html lang="en">
	<head>
    	<meta charset="UTF-8">
    	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    	<title>Virtual Museum Admin</title>
    	<link href="../css/Header.css" rel="stylesheet" type="text/css">
    	<link href="../css/AddEdit.css" rel="stylesheet" type="text/css">
    	<link href="../css/Menu.css" rel="stylesheet" type="text/css">
    	<link rel="icon" href="../images/logo.png">
    	<link rel="preconnect" href="https://fonts.googleapis.com">
    	<link rel="preconnect" href="https://fonts.gstatic.com">
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
		
		<div class="menu-container">
			<a class="menu-item right-margin-3" href="Homepage.jsp">
		        HOME 
		    </a>
			<a class="menu-item right-margin-3" href="Museums.jsp">
		        MUSEUMS
		    </a>
		    <a style="background-color: #8b84bf;" class="menu-item left-margin-3 right-margin-3" href="Tours.jsp">
		        TOURS
		    </a>
		    <a class="menu-item left-margin-3 right-margin-3" href="Users.jsp">
		        USERS
		    </a>
		    <a class="menu-item left-margin-3" href="Logs.jsp">
		        LOGS
		    </a>
		</div>
		
		<div class="content-container">
			<hr>
			<hr>
			<%
			try (Stream<Path> paths = Files.walk(Paths.get("C:\\Users\\njego\\Desktop\\IP\\projektni-zadatak-2022\\VirtualMuseumAdmin\\WebContent\\WEB-INF\\artifacts\\" + artifactId))) {
				    List<File> files = paths 
					        				.filter(Files::isRegularFile)
					        				.map(f -> new File(f.toString()))
					        				.collect(Collectors.toList());
			
				    for (File file : files) {
				    	byte[] fileContent = FileUtils.readFileToByteArray(file);
						String encodedString = Base64.getEncoder().encodeToString(fileContent);
						
						if (file.getName().toLowerCase().endsWith("mp4")) {
						%> 
							<div style="display: flex; align-items: center; justify-content: center;">
								<video style="width: 40%; margin-right: 40px;" controls autoplay src="data:video/mp4;base64, <%= encodedString %>">
								</video>
								<p><%= file.getName() %></p>
							</div>
							<hr>
							<hr>
						
						<% } else { %>
							<div style="display: flex; align-items: center; justify-content: center;">
								<img style="justify-self: center; width: 40%; margin-right: 40px;" src="data:image/png;base64, <%= encodedString %>">
								<p><%= file.getName() %></p>
							</div>
							<hr>
							<hr>
					<%	}
				    }
				} 
			%>
			
			<div class="museum-edit-container">
				<form id="add-tour" action="UpdateArtifact.jsp" method="post" class="museum-edit-form" enctype="multipart/form-data">
					<input type="hidden" name="tour-id" value="<%= artifactId %>">
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
	                	<input class="museum-edit-input-field" type="submit" onclick="return beforeSubmit()" value="UPDATE ARTIFACTS">
	            	</div>
				</form>
			</div>
			
			
		</div>
	</body>
</html>