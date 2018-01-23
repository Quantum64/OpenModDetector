package co.q64.omd.base.manager;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.omd.base.database.ConnectionPoolFactory;

@Singleton
public class ConfigManager {
	protected @Inject ConnectionPoolFactory connectionPoolFactory;
	
	protected @Inject ConfigManager() {}
}
