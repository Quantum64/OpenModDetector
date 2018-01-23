package co.q64.omd.base.config;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.omd.base.manager.ConfigManager;
import co.q64.omd.base.manager.DatabaseManager;
import co.q64.omd.base.util.ConsoleEnhancedSenderFactory;
import co.q64.omd.base.util.Sender;

@Singleton
public class ConfigurationReloader {
	protected @Inject ConsoleEnhancedSenderFactory senderFactory;
	protected @Inject ConfigManager configManager;
	protected @Inject DatabaseManager databaseManager;

	protected @Inject ConfigurationReloader() {}

	public void reload(Sender sender) {
		sender = senderFactory.create(sender);
		configManager.reload(sender);
		databaseManager.reload(sender);
	}
}
