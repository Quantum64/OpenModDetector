package co.q64.omd.base.util;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.omd.base.annotation.Console;
import co.q64.omd.base.config.Configuration;
import co.q64.omd.base.config.Reloadable;
import co.q64.omd.base.manager.ConfigManager;

@Singleton
public class ChatUtil implements Reloadable {
	protected @Inject ConfigManager configManager;

	private String prefix;
	private String color;
	private String accent;

	protected @Inject ChatUtil() {}

	@Inject
	@Override
	public void reload(@Console Sender sender) {
		this.color = Color.GRAY.toString();
		this.accent = Color.GREEN.toString();
		this.prefix = accent + "Mod Detector" + Color.DARK_GRAY + " » " + color;
		Optional<Configuration> opt = configManager.getConfig().getSection("chat");
		if (!opt.isPresent()) {
			return;
		}
		Configuration config = opt.get();
		this.color = config.getString("chat-color", color);
		this.accent = config.getString("accent-color", accent);
		this.prefix = config.getString("chat-prefix", prefix);
	}

	public String prefix() {
		return prefix;
	}

	public String color() {
		return color;
	}

	public String accent() {
		return accent;
	}
}
