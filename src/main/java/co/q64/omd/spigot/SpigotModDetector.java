package co.q64.omd.spigot;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.bukkit.plugin.java.JavaPlugin;

import co.q64.omd.OpenModDetector;
import co.q64.omd.base.manager.ConfigManager;
import co.q64.omd.base.manager.DatabaseManager;
import co.q64.omd.spigot.command.SpigotModListCommand;

@Singleton
public class SpigotModDetector implements OpenModDetector {
	protected @Inject JavaPlugin plugin;

	protected @Inject SpigotModListCommand modListCommand;
	@Inject DatabaseManager temp;
	@Inject ConfigManager temp2;

	protected @Inject SpigotModDetector() {}

	@Override
	public void enable() {
		plugin.getCommand("modlist").setExecutor(modListCommand);
	}

	@Override
	public void disable() {

	}
}
