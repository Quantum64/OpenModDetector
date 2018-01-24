package co.q64.omd.spigot.util;

import java.util.UUID;

import org.bukkit.entity.Player;

import com.google.auto.factory.AutoFactory;
import com.google.auto.factory.Provided;

import co.q64.omd.base.util.PlayerSender;
import co.q64.omd.base.util.PluginFacade;
import lombok.Getter;

@AutoFactory
public class SpigotPlayerSender extends SpigotCommandSender implements PlayerSender {
	private PluginFacade plugin;
	private @Getter Player player;

	protected SpigotPlayerSender(@Provided PluginFacade plugin, Player player) {
		super(player);
		this.plugin = plugin;
		this.player = player;
	}

	@Override
	public void kick(String reason) {
		player.kickPlayer(reason);
	}

	@Override
	public UUID getUUID() {
		return player.getUniqueId();
	}

	@Override
	public void sendPluginMessage(String channel, byte[] message) {
		plugin.sendPluginMessage(this, channel, message);
	}
}
