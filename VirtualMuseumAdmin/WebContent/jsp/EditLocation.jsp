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

	String museumId = request.getParameter("id");
	String lat = request.getParameter("lat");
	String lng = request.getParameter("lng");
	
	if (museumId == null || lat == null || lng == null)
		response.sendRedirect("Museums.jsp");
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
	
		<style>
			html,
			body {
			display: flex;
				align-items: center;
				justify-content: center;
				flex-direction: column; 
				height: 100%;
			  	width: calc(100% - 20px);
			  	margin: 0px;
			 	padding-left: 10px;
			  	padding-right: 10px;
			}
			
			#googleMap {
				border-radius: 10px;
			  	height: 90%;
			  	width: 100%;
			  	margin-top: 20px;
			}
			
			form {
				height: 10%;
				margin-top: 2.5%;
				margin-bottom: 2.5%;
				font-family: 'Bebas Neue', cursive;
			}
			
		</style>
 		
 		<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCQNUv-fC8_xG55L7HC-sK9siqGA_1YbsM"></script>
 
		<script>
			var map;

			var selectedLat = <%= lat %>
			var selectedLng = <%= lng %>
			var myCenter = new google.maps.LatLng(selectedLat, selectedLng);
			var marker;
			var infowindow;
			
			function initialize() {
			  var mapProp = {
			    center: myCenter,
			    zoom: 5,
			    mapTypeId: google.maps.MapTypeId.ROADMAP
			  };
			
			  map = new google.maps.Map(document.getElementById("googleMap"), mapProp);
			  
			  placeMarker(myCenter);
		
			  google.maps.event.addListener(map, 'click', function(event) {
			    placeMarker(event.latLng);
			  });
			}
			
			function placeMarker(location) {
				selectedLat = location.lat()
				selectedLng = location.lng()
				document.getElementById('lat-input').value = selectedLat;
				document.getElementById('lng-input').value = selectedLng;
			  if (!marker || !marker.setPosition) {
			    marker = new google.maps.Marker({
			      position: location,
			      map: map,
			    });
			  } else {
			    marker.setPosition(location);
			  }
			  if (!!infowindow && !!infowindow.close) {
			    infowindow.close();
			  }
			  infowindow = new google.maps.InfoWindow({
			    content: 'Latitude: ' + location.lat() + '<br>Longitude: ' + location.lng()
			  });
			  infowindow.open(map, marker);
			}
		</script>
	</head>
	
	<body onload="initialize()">
		<div id="googleMap"></div>
		<form method="post" action="Museums.jsp">
			<input type="hidden" name="change-location" value="1">
			<input type="hidden" name="location-id" value="<%= museumId %>">
			<input id="lat-input" type="hidden" name="lat">
			<input id="lng-input" type="hidden" name="lng">
			<input type="submit" value="UPDATE LOCATION">
		</form>
	</body>
</html>
