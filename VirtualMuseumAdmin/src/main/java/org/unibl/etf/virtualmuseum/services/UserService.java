package org.unibl.etf.virtualmuseum.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.unibl.etf.virtualmuseum.entities.UserEntity;
import org.unibl.etf.virtualmuseum.services.utils.ConnectionPool;
import org.unibl.etf.virtualmuseum.services.utils.ServiceUtil;

public class UserService {

	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_SELECT_ALL = "SELECT id, username, firstName, lastName, email, isAdmin, isApproved, isBlocked, isPasswordReset FROM VirtualMuseum.USER";
	private static final String SQL_INSERT = "INSERT INTO VirtualMuseum.USER (username, password, firstName, lastName, email, isAdmin) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String SQL_UPDATE = "UPDATE VirtualMuseum.USER SET username = ?, firstName = ?, lastName = ?, email = ? WHERE id = ?";
	private static final String SQL_TOGGLE_ADMIN = "UPDATE VirtualMuseum.USER SET isAdmin = NOT isAdmin WHERE id = ?";
	private static final String SQL_TOGGLE_APPROVED = "UPDATE VirtualMuseum.USER SET isApproved = NOT isApproved WHERE id = ?";
	private static final String SQL_TOGGLE_BLOCKED = "UPDATE VirtualMuseum.USER SET isBlocked = NOT isBlocked WHERE id = ?";
	private static final String SQL_TOGGLE_PASSWORD_RESET = "UPDATE VirtualMuseum.USER SET isPasswordReset = NOT isPasswordReset WHERE id = ?";
	private static final String SQL_DELETE = "DELETE FROM VirtualMuseum.USER WHERE id = ?";
	
	public static ArrayList<UserEntity> selectAll(){
		ArrayList<UserEntity> retVal = new ArrayList<UserEntity>();
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = ServiceUtil.prepareStatement(connection,SQL_SELECT_ALL, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()){
				retVal.add(new UserEntity(rs.getInt("id"), rs.getString("username"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("email"), rs.getBoolean("isAdmin"), rs.getBoolean("isApproved"), rs.getBoolean("isBlocked"), rs.getBoolean("isPasswordReset")));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return retVal;
	}
	
	public static boolean insert(UserEntity user) {
		boolean retVal = false;
		Connection connection = null;
		ResultSet generatedKeys = null;
		Object values[] = { user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getEmail(), (user.isAdmin() ? 1 : 0) };
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
				user.setId(generatedKeys.getInt(1));
			pstmt.close();
		} catch (SQLException e) {
			retVal = false;
		} finally {
			connectionPool.checkIn(connection);
		}
		return retVal;
	}

	public static boolean update(UserEntity user) {
		boolean retVal = false;
		Connection connection = null;
		Object values[] = { user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getId() };
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
	
	public static boolean toggleAdmin(int userId) {
		boolean retVal = false;
		Connection connection = null;
		Object values[] = { userId };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = ServiceUtil.prepareStatement(connection, SQL_TOGGLE_ADMIN, false, values);
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

	public static boolean toggleApproved(int userId) {
		boolean retVal = false;
		Connection connection = null;
		Object values[] = { userId };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = ServiceUtil.prepareStatement(connection, SQL_TOGGLE_APPROVED, false, values);
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

	public static boolean toggleBlocked(int userId) {
		boolean retVal = false;
		Connection connection = null;
		Object values[] = { userId };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = ServiceUtil.prepareStatement(connection, SQL_TOGGLE_BLOCKED, false, values);
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
	
	public static boolean toggleReset(int userId) {
		boolean retVal = false;
		Connection connection = null;
		Object values[] = { userId };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = ServiceUtil.prepareStatement(connection, SQL_TOGGLE_PASSWORD_RESET, false, values);
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
	
	public static boolean delete(int userId) {
		boolean retVal = false;
		Connection connection = null;
		Object values[] = { userId };
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
