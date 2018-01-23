package co.q64.omd.external.database;

import java.sql.Connection;
import java.sql.SQLException;

import com.google.auto.factory.AutoFactory;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import co.q64.omd.base.database.ConnectionPool;
import co.q64.omd.base.database.ConnectionPoolFactory;

@AutoFactory(implementing = ConnectionPoolFactory.class)
public class HikariConnectionPool implements ConnectionPool {
	private HikariDataSource dataSource;

	protected HikariConnectionPool(String url, String username, String password) {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl(url);
		config.setUsername(username);
		config.setPassword(password);
		this.dataSource = new HikariDataSource(config);
	}

	@Override
	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
}
