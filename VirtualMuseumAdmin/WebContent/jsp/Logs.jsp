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
	if (!user.isLoggedIn()) {
		response.sendRedirect("Login.jsp");
	}
	
	else if (request.getParameter("logout") != null) {
		user.logOut();
		session.invalidate();
		response.sendRedirect("Login.jsp");
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
		    <a class="menu-item left-margin-3 right-margin-3" href="Users.jsp">
		        USERS
		    </a>
		    <a style="background-color: #8b84bf;" class="menu-item left-margin-3" href="Logs.jsp">
		        LOGS
		    </a>
		</div>
		
		<div class="content-container">
			<hr>
			<hr>
			<%
				try (Stream<Path> paths = Files.walk(Paths.get("C:\\Users\\njego\\Desktop\\IP\\projektni-zadatak-2022\\VirtualMuseumAdmin\\WebContent\\WEB-INF\\logs"))) {
				    List<File> files = paths 
					        				.filter(Files::isRegularFile)
					        				.map(f -> new File(f.toString()))
					        				.collect(Collectors.toList());
			
				    for (File file : files) {
				    	byte[] fileContent = FileUtils.readFileToByteArray(file);
						String encodedString = Base64.getEncoder().encodeToString(fileContent);
					%>
							<div style="display: flex; align-items: center; justify-content: center;">
								<a href="DownloadFile.jsp?log=<%= file.getName() %>" target="_blank"><%= file.getName() %></a>
							</div>
							<hr>
							<hr>
			

					<% }
				    } catch (Exception e) { e.printStackTrace(); } %>
		</div>
	</body>
</html>