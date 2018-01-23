package co.q64.omd.spigot.config;

import java.util.List;
import java.util.Optional;

import org.bukkit.configuration.ConfigurationSection;

import com.google.auto.factory.AutoFactory;

import co.q64.omd.base.config.Configuration;

@AutoFactory
public class SpigotConfiguration implements Configuration {
	private ConfigurationSection config;

	protected SpigotConfiguration(ConfigurationSection config) {
		this.config = config;
	}

	@Override
	public Optional<Configuration> getSection(String key) {
		ConfigurationSection section = config.getConfigurationSection("key");
		if (section == null) {
			return Optional.empty();
		}
		return Optional.of(new SpigotConfiguration(section));
	}

	@Override
	public int getInteger(String key, int def) {
		return config.getInt(key, def);
	}

	@Override
	public String getString(String key, String def) {
		return config.getString(key, def);
	}

	@Override
	public List<String> getStringList(String key) {
		return config.getStringList(key);
	}

	@Override
	public boolean getBoolean(String key, boolean def) {
		return config.getBoolean(key, def);
	}

	@Override
	public float getFloat(String key, float def) {
		return Double.valueOf(config.getDouble(key, def)).floatValue();
	}
}
