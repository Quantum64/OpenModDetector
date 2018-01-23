package co.q64.omd.base.manager;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.omd.base.database.ConnectionPoolFactory;

@Singleton
public class DatabaseManager {
	protected @Inject ConnectionPoolFactory connectionPoolFactory;
	protected @Inject ConfigManager configManager;

	protected @Inject DatabaseManager() {}

	@Inject
	public void reload() {

	}
}
