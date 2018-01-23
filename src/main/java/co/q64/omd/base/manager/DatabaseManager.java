package co.q64.omd.base.manager;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.omd.base.annotation.Console;
import co.q64.omd.base.database.ConnectionPool;
import co.q64.omd.base.database.ConnectionPoolFactory;
import co.q64.omd.base.database.DatabaseConfigWrapper;
import co.q64.omd.base.database.DatabaseConfigWrapperFactory;
import co.q64.omd.base.util.Color;
import co.q64.omd.base.util.Logger;
import co.q64.omd.base.util.Sender;
import lombok.Getter;

@Singleton
public class DatabaseManager {
	protected @Inject ConnectionPoolFactory connectionPoolFactory;
	protected @Inject DatabaseConfigWrapperFactory configFactory;
	protected @Inject Logger logger;

	private @Getter boolean useDatabase;
	private ConnectionPool pool;

	protected @Inject DatabaseManager() {}

	@Inject
	public void reload(@Console Sender sender) {
		DatabaseConfigWrapper config = configFactory.create();
		useDatabase = config.useDatabase();
		if (useDatabase) {
			String url = "jdbc:mysql://" + config.getHost() + ":" + config.getPort() + "/" + config.getDatabase();
			pool = connectionPoolFactory.create(url, config.getUsername(), config.getPassword());
			try {
				pool.getConnection();
			} catch (Exception e) {
				logger.error("Could not connect to database!", e);
				sender.sendMessage(Color.RED + "Could not connect to database! Some features will be disabled.");
				useDatabase = false;
			}
		}
	}
}
