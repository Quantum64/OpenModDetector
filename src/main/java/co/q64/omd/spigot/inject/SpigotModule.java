package co.q64.omd.spigot.inject;

import org.bukkit.Server;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import dagger.Module;
import dagger.Provides;

@Module
public class SpigotModule {
	private JavaPlugin plugin;

	public SpigotModule(JavaPlugin plugin) {
		this.plugin = plugin;
	}

	protected @Provides JavaPlugin getJavaPlugin() {
		return plugin;
	}

	protected @Provides Plugin getPlugin() {
		return plugin;
	}

	protected @Provides Server getServer() {
		return plugin.getServer();
	}

	protected @Provides PluginManager getPluginManager() {
		return plugin.getServer().getPluginManager();
	}
}
