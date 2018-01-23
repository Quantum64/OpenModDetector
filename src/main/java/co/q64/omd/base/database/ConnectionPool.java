package co.q64.omd.base.database;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionPool {
	public Connection getConnection() throws SQLException;
}
