package org.unibl.etf.virtualmuseum.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.unibl.etf.virtualmuseum.entities.ArtifactEntity;
import org.unibl.etf.virtualmuseum.services.utils.ConnectionPool;
import org.unibl.etf.virtualmuseum.services.utils.ServiceUtil;

public class ArtifactService {
	
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	
	private static final String SQL_SELECT_ALL_YT_BY_TOUR_ID = "SELECT * FROM ARTIFACT a WHERE a.type LIKE '%ytube%' AND a.tourId = ?";
	private static final String SQL_INSERT_ARTIFACT = "INSERT INTO ARTIFACT (uri, type, tourId) VALUES (?, ?, ?)";
	private static final String SQL_DELETE_ARTIFACT_BY_TOUR = "DELETE FROM ARTIFACT WHERE tourId = ?";
	
	public static ArrayList<ArtifactEntity> selectAllYtByTourId(int tourId){
		ArrayList<ArtifactEntity> retVal = new ArrayList<ArtifactEntity>();
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = { tourId };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = ServiceUtil.prepareStatement(connection,
					SQL_SELECT_ALL_YT_BY_TOUR_ID, false, values);
			rs = pstmt.executeQuery();			
			
			while (rs.next()){
				retVal.add(new ArtifactEntity(rs.getInt("id"), rs.getString("uri"), rs.getString("type"), rs.getInt("tourId")));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return retVal;
	}
	
	public static boolean insertArtifact(String uri, String type, Integer tourId) {
		
		boolean retVal = false;
		Connection connection = null;
		Object values[] = { uri, type, tourId };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = ServiceUtil.prepareStatement(connection, SQL_INSERT_ARTIFACT, true, values);
			int affectedRows = pstmt. executeUpdate();
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
	
	public static boolean deleteExistingArtifactsForTour(Integer tourId) {
		boolean retVal = false;
		Connection connection = null;
		Object values[] = { tourId };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = ServiceUtil.prepareStatement(connection, SQL_DELETE_ARTIFACT_BY_TOUR, false, values);
			int affectedRows = pstmt.executeUpdate();
			affectedRows += pstmt.executeUpdate();
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
