<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

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

			<div style="margin-left: 5%;" class="header-title" style="width: 60%;">
                VIRTUAL MUSEUM ADMINISTRATION
            </div>
		</div>
		
		<div class="museum-edit-container">
	        <form action="Homepage.jsp" method="post" class="museum-edit-form">
	            <div class="museum-edit-single-input">
	                <label class="museum-edit-input-label" for="username">Username: </label>
	                <input class="museum-edit-input-field" name="username" type="text" required>
	            </div>
	            <div class="museum-edit-single-input">
	                <label class="museum-edit-input-label" for="password">Password: </label>
	                <input class="museum-edit-input-field" name="password" type="password" required>
	            </div>
	            <div class="museum-edit-single-input margin-botton-4perc">
	                <input class="museum-edit-input-field" type="submit" value="Login">
	            </div>
	        </form>
	    </div>
	</body>
</html>