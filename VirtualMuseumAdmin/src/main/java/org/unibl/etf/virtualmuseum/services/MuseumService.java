package org.unibl.etf.virtualmuseum.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.unibl.etf.virtualmuseum.entities.MuseumEntity;
import org.unibl.etf.virtualmuseum.services.utils.ConnectionPool;
import org.unibl.etf.virtualmuseum.services.utils.ServiceUtil;

public class MuseumService {
	
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_SELECT_ALL = "SELECT * FROM MUSEUM";
	private static final String SQL_SELECT_ONE = "SELECT * FROM MUSEUM WHERE id = ?";
	private static final String SQL_INSERT = "INSERT INTO MUSEUM (name, address, phone, city, country, type, maps) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_UPDATE = "UPDATE MUSEUM SET name = ?, address = ?, phone = ?, city = ?, country = ?, type = ?, maps = ? WHERE id = ?";
	private static final String SQL_DELETE = "DELETE FROM MUSEUM WHERE id = ?";
	
	public static ArrayList<MuseumEntity> selectAll(){
		ArrayList<MuseumEntity> retVal = new ArrayList<MuseumEntity>();
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = ServiceUtil.prepareStatement(connection,
					SQL_SELECT_ALL, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()){
				retVal.add(new MuseumEntity(rs.getInt("id"), rs.getString("name"), rs.getString("address"), rs.getString("phone"), rs.getString("city"), rs.getString("country"), rs.getString("type"), rs.getString("maps")));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return retVal;
	}
	
	public static MuseumEntity selectOne(MuseumEntity museum){
		MuseumEntity retVal = null;
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {museum.getId()};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = ServiceUtil.prepareStatement(connection,
					SQL_SELECT_ONE, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				retVal = new MuseumEntity(rs.getInt("id"), rs.getString("name"), rs.getString("address"), rs.getString("phone"), rs.getString("city"), rs.getString("country"), rs.getString("type"), rs.getString("maps"));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return retVal;
	}
	
	public static MuseumEntity selectOneById(int id){
		MuseumEntity retVal = null;
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {id};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = ServiceUtil.prepareStatement(connection, SQL_SELECT_ONE, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()){
				retVal = new MuseumEntity(rs.getInt("id"), rs.getString("name"), rs.getString("address"), rs.getString("phone"), rs.getString("city"), rs.getString("country"), rs.getString("type"), rs.getString("maps"));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return retVal;
	}

	public static boolean insert(MuseumEntity museum) {
		boolean retVal = false;
		Connection connection = null;
		ResultSet generatedKeys = null;
		Object values[] = { museum.getName(), museum.getAddress(), museum.getPhone(), museum.getCity(), museum.getCountry(), museum.getType(), museum.getMaps() };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = ServiceUtil.prepareStatement(connection, SQL_INSERT, true, values);
			int affectedRows = pstmt. executeUpdate();
			if (affectedRows == 0)
				retVal = false;
			else
				retVal = true;
			generatedKeys = pstmt.getGeneratedKeys();
			if (generatedKeys.next())
				museum.setId(generatedKeys.getInt(1));
			pstmt.close();
		} catch (SQLException e) {
			retVal = false;
		} finally {
			connectionPool.checkIn(connection);
		}
		return retVal;
	}

	public static boolean update(MuseumEntity museum) {
		boolean retVal = false;
		Connection connection = null;
		Object values[] = { museum.getName(), museum.getAddress(), museum.getPhone(), museum.getCity(), museum.getCountry(), museum.getType(), museum.getMaps(), museum.getId()};
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
	
	public static boolean delete(int museumId) {
		boolean retVal = false;
		Connection connection = null;
		Object values[] = { museumId };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = ServiceUtil.prepareStatement(connection, SQL_DELETE, false, values);
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