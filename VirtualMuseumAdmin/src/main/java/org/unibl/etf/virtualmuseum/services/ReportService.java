package org.unibl.etf.virtualmuseum.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.unibl.etf.virtualmuseum.entities.LogEntity;
import org.unibl.etf.virtualmuseum.entities.ReportEntity;
import org.unibl.etf.virtualmuseum.services.utils.ConnectionPool;
import org.unibl.etf.virtualmuseum.services.utils.ServiceUtil;

public class ReportService {

	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_SELECT_ALL_LOGINS = "SELECT DATE(l.dateTime) AS 'date', HOUR(l.dateTime) AS 'hour', FLOOR(COUNT(*)/2) AS 'count' FROM VirtualMuseum.LOGS l WHERE l.type = 'LOGIN' GROUP BY HOUR(l.dateTime), DAY(l.dateTime) LIMIT 24";
	private static final String SQL_SELECT_ALL = "SELECT * FROM VirtualMuseum.LOGS ORDER BY dateTime DESC";
	
	public static ArrayList<ReportEntity> selectAll(){
		ArrayList<ReportEntity> retVal = new ArrayList<ReportEntity>();
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = ServiceUtil.prepareStatement(connection,
					SQL_SELECT_ALL_LOGINS, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()){
				retVal.add(new ReportEntity(rs.getString("date"), rs.getString("hour"), rs.getString("count")));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return retVal;
	}
	
	public static List<String> selectAllLogs(){
		ArrayList<LogEntity> retVal = new ArrayList<LogEntity>();
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = ServiceUtil.prepareStatement(connection,
					SQL_SELECT_ALL, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()){
				retVal.add(new LogEntity(rs.getString("type"), rs.getString("info"), rs.getString("dateTime")));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return retVal.stream().map(l -> l.toString()).collect(Collectors.toList());
	}
}
