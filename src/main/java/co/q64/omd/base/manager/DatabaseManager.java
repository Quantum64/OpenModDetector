package co.q64.omd.base.manager;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.omd.base.annotation.Console;
import co.q64.omd.base.database.ConnectionPool;
import co.q64.omd.base.database.ConnectionPoolFactory;
import co.q64.omd.base.util.Sender;

@Singleton
public class DatabaseManager {
	protected @Inject ConnectionPoolFactory connectionPoolFactory;
	protected @Inject ConfigManager configManager;
	
	private boolean useDatabase;
	private ConnectionPool pool;

	protected @Inject DatabaseManager() {}

	@Inject
	public void reload(@Console Sender sender) {
		
	}
}
