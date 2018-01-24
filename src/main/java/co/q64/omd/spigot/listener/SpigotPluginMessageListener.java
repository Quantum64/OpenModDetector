package co.q64.omd.spigot.listener;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import co.q64.omd.base.manager.ModManager;
import co.q64.omd.spigot.util.SpigotPlayerSenderFactory;

@Singleton
public class SpigotPluginMessageListener implements PluginMessageListener {
	protected @Inject ModManager modManager;
	protected @Inject SpigotPlayerSenderFactory factory;

	protected @Inject SpigotPluginMessageListener() {}

	@Override
	public void onPluginMessageReceived(String channel, Player player, byte[] message) {
		modManager.onPluginMessage(factory.create(player), channel, message);
	}
}
