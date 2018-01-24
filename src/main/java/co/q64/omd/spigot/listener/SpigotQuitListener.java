package co.q64.omd.spigot.listener;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import co.q64.omd.base.manager.ModManager;
import co.q64.omd.spigot.util.SpigotPlayerSenderFactory;

@Singleton
public class SpigotQuitListener implements Listener {
	protected @Inject ModManager modManager;
	protected @Inject SpigotPlayerSenderFactory factory;

	protected @Inject SpigotQuitListener() {}

	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerQuit(PlayerQuitEvent event) {
		modManager.onPlayerDisconnect(factory.create(event.getPlayer()));
	}
}
