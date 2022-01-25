<%@ page import="org.unibl.etf.virtualmuseum.services.TourService"%>
<%@ page import="org.unibl.etf.virtualmuseum.services.ArtifactService"%>
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
	String ytFieldValue = "";
	if (!user.isLoggedIn()) {
		response.sendRedirect("Login.jsp");
	}
	
	else {
		try {
			List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
	        HashMap<String, InputStream> inputStreams = new HashMap<String, InputStream>();
	        for (FileItem item : items) {
	            if (item.isFormField()) {
	                String fieldName = item.getFieldName();
	                String fieldValue = item.getString();
	                 if("tour-id".equals(fieldName)) {
	                	artifactId = fieldValue;
	                	if (artifactId != null) {
	                		Integer artifactIdAsInt = Integer.parseInt(artifactId);
	                		ArtifactService.deleteExistingArtifactsForTour(artifactIdAsInt);
	                		if (!TourService.selectAll().stream().anyMatch(t -> artifactIdAsInt == t.getId()))
	                			response.sendRedirect("Tours.jsp");
	                	}
	                 }
	                 else if ("yt-video-artifact".equals(fieldName))
		                 ytFieldValue = fieldValue;
	                	
	            } else {
	                String fieldName = item.getFieldName();
	                String fileName = FilenameUtils.getName(item.getName());
	                InputStream fileContent = item.getInputStream();
	                inputStreams.put(fileName, fileContent);
	            }
	        }
	        
			File folder = new File("C:\\Users\\njego\\Desktop\\IP\\projektni-zadatak-2022\\VirtualMuseumAdmin\\WebContent\\WEB-INF\\artifacts\\" + artifactId);
			FileUtils.deleteDirectory(folder);
			folder.mkdir();
			if (ytFieldValue != null && !"".equals(ytFieldValue)) {
        		ArtifactService.insertArtifact(ytFieldValue, "ytube", Integer.parseInt(artifactId));
        	}
	        for (HashMap.Entry<String, InputStream> entry : inputStreams.entrySet()) {	        	
	        	try {
					InputStream inputStream = entry.getValue();
	                File file = new File("C:\\Users\\njego\\Desktop\\IP\\projektni-zadatak-2022\\VirtualMuseumAdmin\\WebContent\\WEB-INF\\artifacts\\" + artifactId + "\\" + entry.getKey());
	                if (entry.getKey() == null)
	                	continue;
	                
	                FileOutputStream outputStream = new FileOutputStream(file, false);
	                int read;
	    	        byte[] bytes = new byte[1024 * 1024 * 1024];
	    	        while ((read = inputStream.read(bytes)) != -1) {
	    	            outputStream.write(bytes, 0, read);
	    	        }
	    	        
	    	    	// File name: entry.getKey()
					// Tour ID: te.getId()
					// File input stream: entry.getValue()
					String type = entry.getKey().toLowerCase().contains("png") ? "img" : "vid";
					ArtifactService.insertArtifact(entry.getKey(), type, Integer.parseInt(artifactId));
	    	        
	    	    	outputStream.close();
	    	        inputStream.close();
	    	        
	    	        response.sendRedirect("Artifacts.jsp?tour-id=" + artifactId);
	            } catch (Exception e) {
	            	e.printStackTrace();
	            	assert(true);
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
	</body>
</html>