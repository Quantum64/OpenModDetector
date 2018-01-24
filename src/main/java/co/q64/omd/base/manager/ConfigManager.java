package co.q64.omd.base.manager;

import java.io.File;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.omd.base.annotation.Console;
import co.q64.omd.base.config.Configuration;
import co.q64.omd.base.config.ConfigurationLoader;
import co.q64.omd.base.config.MockConfiguration;
import co.q64.omd.base.config.Reloadable;
import co.q64.omd.base.util.Color;
import co.q64.omd.base.util.Logger;
import co.q64.omd.base.util.PluginFacade;
import co.q64.omd.base.util.Sender;
import lombok.Getter;

@Singleton
public class ConfigManager implements Reloadable {
	private static final String FILE_NAME = "config";

	protected @Inject ConfigurationLoader configLoader;
	protected @Inject PluginFacade pluginFacade;
	protected @Inject MockConfiguration mockConfig;
	protected @Inject Logger logger;

	private @Getter Configuration config;

	protected @Inject ConfigManager() {}

	@Inject
	@Override
	public void reload(@Console Sender sender) {
		String fileName = FILE_NAME + configLoader.getFileExtension();
		pluginFacade.saveResource(fileName);
		File file = new File(pluginFacade.getDataFolder(), fileName);
		if (!file.exists()) {
			logger.error("Could not save the default config! Using default config values!");
			sender.sendMessage(Color.RED + "Could not load config! Check console for details.");
			config = mockConfig;
			return;
		}
		Optional<Configuration> opt = configLoader.load(file);
		if (!opt.isPresent()) {
			config = mockConfig;
			sender.sendMessage(Color.RED + "Could not load config! Check console for details.");
			return;
		}
		config = opt.get();
	}
}
