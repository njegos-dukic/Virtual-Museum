package org.unibl.etf.virtualmuseum.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.unibl.etf.virtualmuseum.entities.TourEntity;
import org.unibl.etf.virtualmuseum.services.utils.ConnectionPool;
import org.unibl.etf.virtualmuseum.services.utils.ServiceUtil;

public class TourService {

	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_SELECT_ALL = "SELECT t.id AS tid, t.name AS tname, t.start, t.duration, t.price, m.id AS mid, m.name AS mname FROM TOUR t INNER JOIN MUSEUM m ON t.museumId = m.id";
	private static final String SQL_INSERT_TOUR = "INSERT INTO TOUR (name, start, duration, price, museumId) VALUES (?, ?, ?, ?, ?)";
	private static final String SQL_UPDATE = "UPDATE TOUR SET museumId = ?, duration = ?, price = ?, start = ?, name = ? WHERE id = ?";
	private static final String SQL_DELETE_TOUR = "DELETE FROM TOUR WHERE id = ?";
	private static final String SQL_DELETE_TICKETS = "DELETE FROM TRANSACTION WHERE tourId = ?";
	
	public static ArrayList<TourEntity> selectAll(){
		ArrayList<TourEntity> retVal = new ArrayList<TourEntity>();
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = ServiceUtil.prepareStatement(connection,
					SQL_SELECT_ALL, false, values);
			rs = pstmt.executeQuery();			
			
			while (rs.next()){
				retVal.add(new TourEntity(rs.getInt("tid"), rs.getInt("mid"), rs.getString("tname"), rs.getString("mname"), rs.getTimestamp("start"), rs.getDouble("duration"), rs.getDouble("price")));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return retVal;
	}
	
	public static boolean delete(int tourId) {
		boolean retVal = false;
		Connection connection = null;
		Object values[] = { tourId };
		try {
			connection = connectionPool.checkOut();
			ArtifactService.deleteExistingArtifactsForTour(tourId);
			PreparedStatement pstmt = ServiceUtil.prepareStatement(connection, SQL_DELETE_TICKETS, false, values);
			int affectedRows = pstmt.executeUpdate();
			affectedRows += pstmt.executeUpdate();
			pstmt = ServiceUtil.prepareStatement(connection, SQL_DELETE_TOUR, false, values);
			affectedRows += pstmt.executeUpdate();
			if (affectedRows == 0)
				retVal = false;
			else
				retVal = true;
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			retVal = false;
		} finally {
			connectionPool.checkIn(connection);
		}
		return retVal;
	}
	
	public static boolean update(TourEntity tour) {
		boolean retVal = false;
		Connection connection = null;
		Object values[] = { tour.getMuseumId(), tour.getDuration(), tour.getPrice(), tour.getStartDateTime(), tour.getName(), tour.getId()};
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
	
	public static boolean insert(TourEntity tour) {
		boolean retVal = false;
		Connection connection = null;
		ResultSet generatedKeys = null;
		Object values[] = { tour.getName(), tour.getStartDateTime(), tour.getDuration(), tour.getPrice(), tour.getMuseumId() };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = ServiceUtil.prepareStatement(connection, SQL_INSERT_TOUR, true, values);
			int affectedRows = pstmt. executeUpdate();
			if (affectedRows == 0)
				retVal = false;
			else
				retVal = true;
			generatedKeys = pstmt.getGeneratedKeys();
			if (generatedKeys.next())
				tour.setId(generatedKeys.getInt(1));
			pstmt.close();
		} catch (SQLException e) {
			retVal = false;
		} finally {
			connectionPool.checkIn(connection);
		}
		return retVal;
	}
}
