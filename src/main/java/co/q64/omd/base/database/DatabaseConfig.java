package co.q64.omd.base.database;

import java.util.Optional;

import com.google.auto.factory.AutoFactory;
import com.google.auto.factory.Provided;

import co.q64.omd.base.config.Configuration;
import co.q64.omd.base.config.MockConfiguration;
import co.q64.omd.base.manager.ConfigManager;

@AutoFactory
public class DatabaseConfig {
	private Configuration config;

	protected DatabaseConfig(@Provided ConfigManager manager, @Provided MockConfiguration mock) {
		Optional<Configuration> opt = manager.getConfig().getSection("database");
		if (!opt.isPresent()) {
			config = mock;
			return;
		}
		config = opt.get();
	}

	public String getUsername() {
		return config.getString("username", "username");
	}

	public String getPassword() {
		return config.getString("password", "password");
	}

	public String getDatabase() {
		return config.getString("database", "ModDetector");
	}

	public String getHost() {
		return config.getString("host", "localhost");
	}

	public int getPort() {
		return config.getInteger("port", 3306);
	}
	
	public boolean useDatabase() {
		return config.getBoolean("use-database", false);
	}
}
