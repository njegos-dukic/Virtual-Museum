package org.unibl.etf.virtualbankui.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.unibl.etf.virtualbankui.entities.TransactionEntity;
import org.unibl.etf.virtualbankui.services.utils.ConnectionPool;
import org.unibl.etf.virtualbankui.services.utils.ServiceUtil;

public class TransactionService {

	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_SELECT_ALL_BY_CARD_NUMBER = "SELECT "
			+ " 	  vmuser.firstName AS firstName, \r\n"
			+ "	      vmuser.lastName AS lastName,\r\n"
			+ "       museum.name AS museum,\r\n"
			+ "       tour.name AS tour,\r\n"
			+ "       tour.price AS price,\r\n"
			+ "       transact.transactionInfo AS info\r\n"
			+ "FROM VirtualMuseum.TRANSACTION transact\r\n"
			+ "INNER JOIN VirtualMuseum.USER vmuser ON transact.userId = vmuser.id\r\n"
			+ "INNER JOIN VirtualMuseum.TOUR tour ON transact.tourId = tour.id\r\n"
			+ "INNER JOIN VirtualMuseum.MUSEUM museum ON tour.museumId = museum.id\r\n"
			+ "WHERE transact.cardNumber = ?";

	public static ArrayList<TransactionEntity> selectAllByCardNumber(String cardNumber){
		ArrayList<TransactionEntity> retVal = new ArrayList<TransactionEntity>();
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = { cardNumber };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = ServiceUtil.prepareStatement(connection,
					SQL_SELECT_ALL_BY_CARD_NUMBER, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				retVal.add(new TransactionEntity(rs.getString("firstName"), rs.getString("lastName"), rs.getString("museum"), rs.getString("tour"), rs.getDouble("price"), rs.getString("info"))); 
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return retVal;
	}
}
