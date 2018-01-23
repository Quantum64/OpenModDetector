package co.q64.omd.spigot.inject;

import lombok.AllArgsConstructor;

import org.bukkit.Server;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import dagger.Module;
import dagger.Provides;

@Module
@AllArgsConstructor
public class SpigotModule {
	private JavaPlugin plugin;

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
