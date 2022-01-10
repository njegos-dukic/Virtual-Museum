<%@ page import="org.unibl.etf.virtualmuseum.entities.MuseumEntity"%>
<%@ page import="org.unibl.etf.virtualmuseum.services.MuseumService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:useBean id="user" scope="session" class="org.unibl.etf.virtualmuseum.beans.UserBean"/>  
 
<%
	MuseumEntity me = null;

	if (!user.isLoggedIn()) {
		response.sendRedirect("Login.jsp");
	}
	
	else if (request.getParameter("logout") != null) {
		user.logOut();
		session.invalidate();
		response.sendRedirect("Login.jsp");
	}
	
	else {
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String city = request.getParameter("city");
		String country = request.getParameter("country");
		String type = request.getParameter("type");
		String maps = request.getParameter("maps");
	    if(name != null && address != null && phone != null && city != null && country != null && type != null && maps != null) {
	    	me = new MuseumEntity(0, name, address, phone, city, country, type, maps);
	    	MuseumService.insert(me);
	    	response.sendRedirect("Museums.jsp");
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
    	<link rel="icon" href="../images/logo.png">
    	<link rel="preconnect" href="https://fonts.googleapis.com">
    	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    	<link href="https://fonts.googleapis.com/css2?family=Bebas+Neue&display=swap" rel="stylesheet"> 				
	
		<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	  
		<script>
	        $(document).ready(function() {  
	            $.get("https://restcountries.com/v3.1/region/europe", function(data) {
	                data.forEach(country => $("#countries-select").append("<option data-alpha2code=\"" + country.cca2 + "\" value=\"" + country.name.common + "\">" + country.name.common + "</option>"))
	            }) 
	            
	            $('#countries-select').change(function() {
	            	   $('#cities-select').html('');
	                   $(this).find(":selected").each(function () {
	                       var endpoint = "http://battuta.medunes.net/api/region/" + $(this).data('alpha2code') + "/all/?key=e1efcc9e9bac7b924ccb5a74af63540f&callback=parseResponse";
	                    $.getScript(endpoint);
	                });
	            });
	        });
	        
	        function parseResponse(data) {
	            data.forEach(region => $.getScript("http://battuta.medunes.net/api/city/" + region.country +"/search/?region=" + region.region + "&key=e1efcc9e9bac7b924ccb5a74af63540f&callback=parseCityResponse"))
	        }
	        
	        function parseCityResponse(data) {
	            data.forEach(city => { $("#cities-select").append("<option value=\"" + city.city + "\">" + city.city + "</option>"); })
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
	        <form action="#" method="post" class="museum-edit-form">
	            <div class="museum-edit-single-input">
	                <label class="museum-edit-input-label" for="name">Museum name: </label>
	                <input class="museum-edit-input-field" name="name" type="text" required>
	            </div>
	            <div class="museum-edit-single-input">
	                <label class="museum-edit-input-label" for="address">Museum address: </label>
	                <input class="museum-edit-input-field" name="address" type="text" required>
	            </div>
	            <div class="museum-edit-single-input">
	                <label class="museum-edit-input-label" for="phone">Museum phone: </label>
	                <input class="museum-edit-input-field" name="phone" type="text" required>
	            </div>
	            <div class="museum-edit-single-input">
	                <label class="museum-edit-input-label" for="country">Museum country: </label>
	                <select id="countries-select" class="museum-edit-input-field" name="country" required>
	                
	                </select>
	            </div>
	            <div class="museum-edit-single-input">
	                <label class="museum-edit-input-label" for="city">Museum city: </label>
	                <select id="cities-select" class="museum-edit-input-field" name="city" required>
	                
	                </select>
	            </div>
	            <div class="museum-edit-single-input">
	                <label class="museum-edit-input-label" for="type">Museum type: </label>
	                <input class="museum-edit-input-field" name="type" type="text" required>
	            </div>
	            <div class="museum-edit-single-input">
	                <label class="museum-edit-input-label" for="maps">Museum location: </label>
	                <input class="museum-edit-input-field" name=maps type="text" required>
	            </div>
	            <div class="museum-edit-single-input margin-botton-4perc">
	                <input class="museum-edit-input-field" type="submit" value="Add Museum">
	            </div>
	        </form>
	    </div>
	</body>
</html>