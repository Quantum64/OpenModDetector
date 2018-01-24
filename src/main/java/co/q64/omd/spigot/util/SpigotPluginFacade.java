package co.q64.omd.spigot.util;

import java.io.File;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.bukkit.Server;
import org.bukkit.plugin.Plugin;

import co.q64.omd.base.util.PlayerSender;
import co.q64.omd.base.util.PluginFacade;

@Singleton
public class SpigotPluginFacade implements PluginFacade {
	protected @Inject Plugin plugin;
	protected @Inject Server server;

	protected @Inject SpigotPluginFacade() {}

	@Override
	public File getDataFolder() {
		return plugin.getDataFolder();
	}

	@Override
	public void saveResource(String name) {
		plugin.saveResource(name, false);
	}

	@Override
	public void schedule(Runnable task, int ticks) {
		server.getScheduler().scheduleSyncDelayedTask(plugin, task, ticks);
	}

	@Override
	public void sendPluginMessage(PlayerSender sender, String channel, byte[] message) {
		if (sender instanceof SpigotPlayerSender) {
			((SpigotPlayerSender) sender).getPlayer().sendPluginMessage(plugin, channel, message);
		}
	}
}
