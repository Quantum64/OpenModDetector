package co.q64.omd.base.manager;

import java.io.File;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.omd.base.annotation.Console;
import co.q64.omd.base.config.Configuration;
import co.q64.omd.base.config.ConfigurationLoader;
import co.q64.omd.base.config.MockConfigurationFactory;
import co.q64.omd.base.util.Logger;
import co.q64.omd.base.util.PluginFacade;
import co.q64.omd.base.util.Sender;

@Singleton
public class ConfigManager {
	private static final String FILE_NAME = "config";

	protected @Inject ConfigurationLoader configLoader;
	protected @Inject PluginFacade pluginFacade;
	protected @Inject MockConfigurationFactory mockConfigFactory;
	protected @Inject Logger logger;

	private Configuration config;

	protected @Inject ConfigManager() {}

	@Inject
	public void reload(@Console Sender sender) {
		String fileName = FILE_NAME + configLoader.getFileExtension();
		pluginFacade.saveResource(fileName);
		File file = new File(pluginFacade.getDataFolder(), fileName);
		if (!file.exists()) {
			logger.error("Could not save the default config! Using default config values!");
			config = mockConfigFactory.create();
			return;
		}
		Optional<Configuration> opt = configLoader.load(file);
		if (!opt.isPresent()) {
			config = mockConfigFactory.create();
			return;
		}
		config = opt.get();
	}
}
