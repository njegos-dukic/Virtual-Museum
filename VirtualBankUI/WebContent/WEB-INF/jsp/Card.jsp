<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.unibl.etf.virtualbankui.services.TransactionService"%>
<%@ page import="org.unibl.etf.virtualbankui.entities.TransactionEntity"%>
<jsp:useBean id="userBean" type="org.unibl.etf.virtualbankui.beans.UserBean" scope="session"/>

<!DOCTYPE html>
<html lang="en">
    <head>
    	<meta charset="UTF-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    	<title>Virtual Bank</title>
    	<link href="/VirtualBankUI/css/Blue.css" rel="stylesheet" type="text/css">
   		<link rel="icon" href="/VirtualBankUI/images/logo.png">
    </head>
    <body>
        <div id="mainContentArea">
            <div id="contentBox">
                <div id="title">VIRTUAL BANK UI</div>

                <div id="linkGroup">
                </div>
                <div id="blueBox">
                <div class="contentTitle"></div>
                    <div class="pageContent">
                        <form action="?action=toggle-enabled" method="post">
                        	<p>User: <%= userBean.getCard().getFirstName() + " " + userBean.getCard().getLastName() %></p>     
                        	<p>Type: <%= userBean.getCard().getCardType() %> </p>  
                        	<p>Card number: <%= userBean.getCard().getCardNumber() %></p>                      
                        	<p>Balance: <%= userBean.getCard().getBalance() %> EUR</p>                      
                        	
                        	<input type="radio" id="enabled" name="enab" value="1" <%= (userBean.getCard().getIsEnabled()) ? "checked" : "" %>>
							<label for="huey">Enabled</label>
							<input type="radio" id="disabled" name="enab" value="0" <%= (!userBean.getCard().getIsEnabled()) ? "checked" : "" %>>
							<label for="huey">Disabled</label>
							<div style="margin-top: 15px; margin-bottom: 10px;">
							<input type="submit" value="UPDATE CARD">
							</div>
						</form>
						<a href="?action=logout">
							<button>
								LOGOUT
							</button>
						</a>
						<hr>
						<% for (TransactionEntity te : TransactionService.selectAllByCardNumber(userBean.getCard().getCardNumber())) { %>
							<p> <%= te.toString() %> </p>
						<% } %>						
						<hr>
                    </div>
                    <div id="footer">internet-programiranje 2022</div>
                </div>
            </div>
        </div>
    </body>
</html>