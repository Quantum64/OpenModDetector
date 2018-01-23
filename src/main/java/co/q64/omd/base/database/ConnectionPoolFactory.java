package co.q64.omd.base.database;

public interface ConnectionPoolFactory {
	public ConnectionPool create(String url, String username, String password);
}
