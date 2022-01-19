<%@ page import="org.unibl.etf.virtualmuseum.entities.TourEntity"%>
<%@ page import="org.unibl.etf.virtualmuseum.services.TourService"%>
<%@ page import="org.unibl.etf.virtualmuseum.entities.MuseumEntity"%>
<%@ page import="org.unibl.etf.virtualmuseum.services.MuseumService"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.text.DateFormat"%>
<%@ page import="java.sql.Timestamp"%>
<%@ page import="java.util.List" %>
<%@ page import="java.io.File" %>
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
		/* 
	    String mid = request.getParameter("museum");
		String tname = request.getParameter("name");
		String startDate = request.getParameter("date");
		String startTime = request.getParameter("time");
		String duration = request.getParameter("duration");
	
		
		// image-artifacts
		// video-artifacts
		// yt-video-artifacts
		TourEntity te = null;
	    if(mid != null && tname != null && startDate != null && startTime != null && duration != null) {
	    	te = new TourEntity(Integer.parseInt(mid), tname, Timestamp.valueOf(startDate + " " + startTime), duration);
	    	TourService.insert(te);
	    	response.sendRedirect("Tours.jsp");
	    } */
	    try {
	    	int tourId = -1;
	        List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
	        HashMap<String, InputStream> inputStreams = new HashMap<String, InputStream>();
	        TourEntity te = new TourEntity();
	        String dateString = "";
	        String timeString = "";
	        for (FileItem item : items) {
	            if (item.isFormField()) {
	                String fieldName = item.getFieldName();
	                String fieldValue = item.getString();
	                if ("museum".equals(fieldName))
	                	te.setMuseumId(Integer.parseInt(fieldValue));
	                else if ("name".equals(fieldName))
	                	te.setName(fieldValue);
	                else if ("date".equals(fieldName))
	                	dateString = fieldValue;
	                else if ("time".equals(fieldName))
	                	timeString = fieldValue;
	                else if ("duration".equals(fieldName))
	                	te.setDuration(Double.parseDouble(fieldValue));
	                else if ("price".equals(fieldName))
	                	te.setPrice(Double.parseDouble(fieldValue));
	            } else {
	                String fieldName = item.getFieldName();
	                String fileName = FilenameUtils.getName(item.getName());
	                InputStream fileContent = item.getInputStream();
	                inputStreams.put(fileName, fileContent);
	            }
	        }
	        
	        te.setStartDateTime(Timestamp.valueOf(dateString + " " + timeString + ":00"));
	        boolean success = TourService.insert(te);
	        
	        if (success) {
				File folder = new File("C:\\Users\\njego\\Desktop\\IP\\projektni-zadatak-2022\\VirtualMuseumAdmin\\WebContent\\WEB-INF\\artifacts\\" + te.getId());
				FileUtils.deleteDirectory(folder);
				folder.mkdir();
				
		        for (HashMap.Entry<String, InputStream> entry : inputStreams.entrySet()) {	        	
		        	try {
						InputStream inputStream = entry.getValue();
		                File file = new File("C:\\Users\\njego\\Desktop\\IP\\projektni-zadatak-2022\\VirtualMuseumAdmin\\WebContent\\WEB-INF\\artifacts\\" + te.getId() + "\\" + entry.getKey());
		                FileOutputStream outputStream = new FileOutputStream(file, false);
		                int read;
		    	        byte[] bytes = new byte[1024 * 1024 * 1024];
		    	        while ((read = inputStream.read(bytes)) != -1) {
		    	            outputStream.write(bytes, 0, read);
		    	        }
		    	        outputStream.close();
		    	        inputStream.close();
		            } catch (Exception e) {
		            	assert(true);
		            }
		        }
	        }
	        
	    } catch (FileUploadException e) {
	      	assert(true);
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
		
		<div class="museum-edit-container">
	        <form id="add-tour" action="#" method="post" class="museum-edit-form" enctype="multipart/form-data">
	        	<div class="museum-edit-single-input">
	                <label class="museum-edit-input-label" for="museum">Museum: </label>
	                <select id="museum-select" class="museum-edit-input-field" name="museum" required>
		                <% for(MuseumEntity me : MuseumService.selectAll()) { %>
		                	<option value="<%= me.getId() %>"> <%= me.getName() %> </option>
		                <% } %>
	                </select>
	            </div>
	            <div class="museum-edit-single-input">
	                <label class="museum-edit-input-label" for="name">Tour name: </label>
	                <input class="museum-edit-input-field" name="name" type="text" required>
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
	                <label class="museum-edit-input-label" for="price">Price (EUR): </label>
	                <input class="museum-edit-input-field" name="price" type="number" step=".01" required>
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