package co.q64.omd.base.blocker;

import java.util.Optional;

import com.google.auto.factory.AutoFactory;
import com.google.auto.factory.Provided;

import co.q64.omd.base.config.Configuration;
import co.q64.omd.base.config.MockConfiguration;
import co.q64.omd.base.manager.ConfigManager;

@AutoFactory
public final class BlockerConfig {
	private Configuration config;

	protected BlockerConfig(@Provided ConfigManager manager, @Provided MockConfiguration mock) {
		Optional<Configuration> opt = manager.getConfig().getSection("blockers");
		if (!opt.isPresent()) {
			config = mock;
			return;
		}
		config = opt.get();
	}

	public boolean enabled(String blocker) {
		return config.getBoolean(blocker, false);
	}
}