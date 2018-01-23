package co.q64.omd.spigot.util;

import java.io.File;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.bukkit.plugin.Plugin;

import co.q64.omd.base.util.PluginFacade;

@Singleton
public class SpigotPluginFacade implements PluginFacade {
	protected @Inject Plugin plugin;

	protected @Inject SpigotPluginFacade() {}

	@Override
	public File getDataFolder() {
		return plugin.getDataFolder();
	}

	@Override
	public void saveResource(String name) {
		plugin.saveResource(name, false);
	}
}
