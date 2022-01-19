<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="userBean" class="org.unibl.etf.virtualbankui.beans.UserBean" scope="session"/>

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
                <div id="header"></div>
                <div class="contentTitle"></div>
                    <div class="pageContent">
                        <form action="?action=login" method="post">
                            <table>
                                <tr>
                                    <td>Card number:</td><td><input type="text" name="card-number"/></td>
                                </tr>
                                <tr>
                                    <td>CVV:</td><td><input type="password" name="cvv"/></td>
                                </tr>
                                <tr>
                                    <td></td><td><input type="submit" value="Login"/></td>
                                </tr>
                            </table>
                        </form>
                        <hr>
                    </div>
                    <div id="footer">internet-programiranje 2022</div>
                </div>
            </div>
        </div>
    </body>
</html>