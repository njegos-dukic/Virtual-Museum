package org.unibl.etf.virtualbankui.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.unibl.etf.virtualbankui.entities.CardEntity;
import org.unibl.etf.virtualbankui.services.utils.ConnectionPool;
import org.unibl.etf.virtualbankui.services.utils.ServiceUtil;

public class CardService {
	
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_SELECT_ALL = "SELECT * FROM VirtualMuseum.CARD";
	private static final String SQL_SELECT_ONE = "SELECT * FROM VirtualMuseum.CARD WHERE cardNumber = ? AND cvv = ?";
	private static final String SQL_UPDATE = "UPDATE VirtualMuseum.CARD SET isEnabled = ? WHERE cardNumber = ?";
	
	public static ArrayList<CardEntity> selectAll(){
		ArrayList<CardEntity> retVal = new ArrayList<CardEntity>();
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = ServiceUtil.prepareStatement(connection,
					SQL_SELECT_ALL, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()){
				retVal.add(new CardEntity(rs.getString("firstName"), rs.getString("lastName"), rs.getString("cardNumber"), rs.getString("CARDTYPE"), rs.getString("expirationDate"), rs.getString("cvv"), rs.getDouble("balance"), rs.getBoolean("isEnabled")));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return retVal;
	}
	
	public static CardEntity selectOneByCardNumberAndCvv(String cardNumber, String cvv){
		CardEntity retVal = null;
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = { cardNumber, cvv };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = ServiceUtil.prepareStatement(connection,
					SQL_SELECT_ONE, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				retVal = new CardEntity(rs.getString("firstName"), rs.getString("lastName"), rs.getString("cardNumber"), rs.getString("CARDTYPE"), rs.getString("expirationDate"), rs.getString("cvv"), rs.getDouble("balance"), rs.getBoolean("isEnabled"));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return retVal;
	}
	

	public static boolean toggleEnabled(CardEntity card) {
		boolean retVal = false;
		Connection connection = null;
		Object values[] = { card.getIsEnabled(), card.getCardNumber() };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = ServiceUtil.prepareStatement(connection, SQL_UPDATE, false, values);
			int affectedRows = pstmt.executeUpdate();
			if (affectedRows == 0)
				retVal = false;
			else
				retVal = true;
			pstmt.close();
		} catch (SQLException e) {
			retVal = false;
		} finally {
			connectionPool.checkIn(connection);
		}
		return retVal;
	}
}
