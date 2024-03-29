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
		String id = request.getParameter("current-id");
	    String name = request.getParameter("current-name");
		String address = request.getParameter("current-address");
		String phone = request.getParameter("current-phone");
		String city = request.getParameter("current-city");
		String country = request.getParameter("current-country");
		String type = request.getParameter("current-type");
		String lat = request.getParameter("current-lat");
		String lng = request.getParameter("current-lng");
	    if(id != null && name != null && address != null && phone != null && city != null && country != null && type != null && lat != null && lng != null) {
	    	me = new MuseumEntity(Integer.parseInt(id), name, address, phone, city, country, type, Double.parseDouble(lat), Double.parseDouble(lng));
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
			var batutaApiKey = "6f828c1760732603d2bcf250899ca120"; // e1efcc9e9bac7b924ccb5a74af63540f
        
	        $(document).ready(function() {  
				var currentCountry = <%= "\"" + (me == null ? "" : me.getCountry()) + "\"" %>
	            $.get("https://restcountries.com/v3.1/region/europe", function(data) {
	            	data.forEach(country => { $("#countries-select").append("<option data-alpha2code=\"" + country.cca2 + "\" value=\"" + country.name.common + "\"" + (currentCountry == country.name.common ? " selected >" : ">") + country.name.common + "</option>"); })
	            }) 
	            
	            
	            $('#countries-select').change(function() {
	            	   $('#cities-select').html('');
	                   $(this).find(":selected").each(function () {
	                       var endpoint = "http://battuta.medunes.net/api/region/" + $(this).data('alpha2code') + "/all/?key=" + batutaApiKey + "&callback=parseResponse";
	                    $.getScript(endpoint);
	                });
	            });
	        });
	        
	        function parseResponse(data) {
	            data.forEach(region => $.getScript("http://battuta.medunes.net/api/city/" + region.country +"/search/?region=" + region.region + "&key=" + batutaApiKey + "&callback=parseCityResponse"))
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
		
		<div class="museum-edit-container">
	        <form action="Museums.jsp" method="post" class="museum-edit-form">
	        	<input name="id" type="hidden" value=<%= (me == null ? "" : me.getId()) %>>
	            <div class="museum-edit-single-input">
	                <label class="museum-edit-input-label" for="name">Museum name: </label>
	                <input class="museum-edit-input-field" name="name" type="text" value="<%= (me == null ? "" : me.getName()) %>" required>
	            </div>
	            <div class="museum-edit-single-input">
	                <label class="museum-edit-input-label" for="address">Museum address: </label>
	                <input class="museum-edit-input-field" name="address" type="text" value="<%= (me == null ? "" : me.getAddress()) %>" required>
	            </div>
	            <div class="museum-edit-single-input">
	                <label class="museum-edit-input-label" for="phone">Museum phone: </label>
	                <input class="museum-edit-input-field" name="phone" type="text" value="<%= (me == null ? "" : me.getPhone()) %>" required>
	            </div>
	            <div class="museum-edit-single-input">
	                <label class="museum-edit-input-label" for="country">Museum country: </label>
	                <select id="countries-select" class="museum-edit-input-field" name="country" required>
	                
	                </select>
	            </div>
	            <div class="museum-edit-single-input">
	                <label class="museum-edit-input-label" for="city">Museum city: </label>
	                <select id="cities-select" class="museum-edit-input-field" name="city" required>
	                	<option value="<%= (me == null ? "" : me.getCity()) %>"> <%= (me == null ? "" : me.getCity()) %> </option>
	                </select>
	            </div>
	            <div class="museum-edit-single-input">
	                <label class="museum-edit-input-label" for="type">Museum type: </label>
	                <input class="museum-edit-input-field" name="type" type="text" value="<%= (me == null ? "" : me.getType()) %>" required>
	            </div>
	            <input type="hidden" name=lat value="<%= me.getLat() %>">
	            <input type="hidden" name=lng value="<%= me.getLng() %>">
	            <div class="museum-edit-single-input margin-botton-4perc">
	                <input class="museum-edit-input-field" type="submit" value="Update Museum">
	            </div>
	        </form>
	    </div>
	</body>
</html>