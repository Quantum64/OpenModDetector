package co.q64.omd.spigot.listener;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import co.q64.omd.base.manager.ModManager;
import co.q64.omd.spigot.util.SpigotPlayerSenderFactory;

@Singleton
public class SpigotJoinListener implements Listener {
	protected @Inject ModManager modManager;
	protected @Inject SpigotPlayerSenderFactory factory;

	protected @Inject SpigotJoinListener() {}

	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerJoin(PlayerJoinEvent event) {
		modManager.onPlayerJoin(factory.create(event.getPlayer()));
	}
}
