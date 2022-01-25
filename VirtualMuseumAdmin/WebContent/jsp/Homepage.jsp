<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="org.unibl.etf.virtualmuseum.services.ReportService"%>
<%@ page import="org.unibl.etf.virtualmuseum.entities.ReportEntity"%>
<%@ page import="org.unibl.etf.virtualmuseum.services.UserService"%>
<jsp:useBean id="user" scope="session" class="org.unibl.etf.virtualmuseum.beans.UserBean">
	<jsp:setProperty name="user" property= "username" value=""/> 
	<jsp:setProperty name="user" property= "password" value=""/> 
</jsp:useBean>  
<%  
	if (request.getParameter("username") != null && request.getParameter("password") != null) {
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		user.login();
	}
	
	if (request.getParameter("adminToken") != null) {
		user.setUsername("");
		user.setPassword("");
		user.setAdminToken(request.getParameter("adminToken"));
		user.login();
	}
	
	if (!user.isLoggedIn()) {
		response.sendRedirect("Login.jsp");
	}
	
	if (request.getParameter("logout") != null) {
		user.logOut();
		session.invalidate();
		response.sendRedirect("Login.jsp");
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
    	<link href="../css/Menu.css" rel="stylesheet" type="text/css">
    	<link rel="icon" href="../images/logo.png">
    	<link rel="preconnect" href="https://fonts.googleapis.com">
    	<link rel="preconnect" href="https://fonts.gstatic.com">
    	<link href="https://fonts.googleapis.com/css2?family=Bebas+Neue&display=swap" rel="stylesheet"> 				
	
		<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
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

		<div class="menu-container" style="width: 100%;">
			<a style="background-color: #8b84bf;" class="menu-item right-margin-3" href="Homepage.jsp">
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
		    <a class="menu-item left-margin-3" href="Logs.jsp">
		        LOGS
		    </a>
		</div>
		
		<div style="width: 100%; margin-top: 15px">
			<div style="background-color: lightgreen; padding: 15px; display: flex; align-items: center; justify-content: flex-start;" class="menu-item right-margin-3">
				<p>Registrovanih korisnika: <%= UserService.selectTotalCount() %></p>
		    </div>
			<div style="background-color: lightgreen; padding: 15px; display: flex; align-items: center; justify-content: flex-start; margin-top: 15px; margin-bottom: 10px" class="menu-item lef-tmargin-3 right-margin-3">
		        <p>Aktivnih korisnika: <%= UserService.selectLoggedInCount() %></p>
		    </div>
		</div>
		
		
		<div style="width: 80%" >
			<canvas id="myChart" ></canvas>
		</div>
		<script>
		const ctx = document.getElementById('myChart').getContext('2d');
		const myChart = new Chart(ctx, {
		    type: 'bar',
		    data: {
		    	
		        labels: [<% for (ReportEntity re : ReportService.selectAll()) { %> <%= "'" + re.getDate() + " at " + re.getHour() + "h', " %> <% } %>],
		        datasets: [{
		            label: '# of Logins',
		            data: [<% for (ReportEntity re : ReportService.selectAll()) { %> <%= re.getCount() + "," %> <% } %>],
		            borderWidth: 1
		        }]
		    },
		    options: {
		        scales: {
		            y: {
		                beginAtZero: true
		            }
		        }
		    }
		});
		</script>

		
	</body>
</html>