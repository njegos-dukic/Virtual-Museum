package org.unibl.etf.virtualbankui.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.unibl.etf.virtualbankui.beans.UserBean;
import org.unibl.etf.virtualbankui.entities.CardEntity;
import org.unibl.etf.virtualbankui.services.CardService;

@WebServlet("/")
public class Controller extends HttpServlet {

	private static final long serialVersionUID = 150713280275512L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String address = "/WEB-INF/jsp/Login.jsp";
		
		String action = request.getParameter("action");
		String cardNumber = request.getParameter("card-number");
		String cvv = request.getParameter("cvv");
				
		HttpSession session = request.getSession();
		UserBean userBean = new UserBean();
		
		if ("login".equals(action)) {
			if (cardNumber != null && cvv != null) {
				if (userBean.login(cardNumber, cvv)) {
					session.setAttribute("userBean", userBean);
					address = "/WEB-INF/jsp/Card.jsp";
				}
			}
		}
		
		else if ("toggle-enabled".equals(action)) {
			userBean = (UserBean) session.getAttribute("userBean");
			if (userBean != null) {
				if (userBean.isValidated()) {
					String enab = request.getParameter("enab");
					Boolean enabled = "1".equals(enab) ? true : false;
					CardEntity card = userBean.getCard();
					card.setIsEnabled(enabled);
					if (card != null) {
						CardService.toggleEnabled(card);
					}
					address = "/WEB-INF/jsp/Card.jsp#";
				}
				else {
					address = "/WEB-INF/jsp/Login.jsp";
				}
			}
		}
		
		else if ("logout".equals(action)) {
			userBean.logout();
			session.invalidate();
			address = "/WEB-INF/jsp/Login.jsp";
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

