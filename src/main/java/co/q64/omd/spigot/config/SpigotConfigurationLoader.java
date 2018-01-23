package co.q64.omd.spigot.config;

import java.io.File;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import co.q64.omd.base.config.Configuration;
import co.q64.omd.base.config.ConfigurationLoader;
import co.q64.omd.base.util.Logger;

@Singleton
public class SpigotConfigurationLoader implements ConfigurationLoader {
	protected @Inject SpigotConfigurationFactory configFactory;
	protected @Inject Logger logger;

	protected @Inject SpigotConfigurationLoader() {}

	@Override
	public Optional<Configuration> load(File file) {
		try {
			ConfigurationSection section = YamlConfiguration.loadConfiguration(file);
			return Optional.of(configFactory.create(section));
		} catch (Exception e) {
			logger.error("Failed to load configuration!", e);
			return Optional.empty();
		}
	}

	@Override
	public String getFileExtension() {
		return ".yml";
	}
}
