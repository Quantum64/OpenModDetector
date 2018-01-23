package co.q64.omd.spigot.util;

import org.bukkit.entity.Player;

import com.google.auto.factory.AutoFactory;

import co.q64.omd.base.util.PlayerSender;

@AutoFactory
public class SpigotPlayerSender extends SpigotCommandSender implements PlayerSender {
	private Player player;

	protected SpigotPlayerSender(Player player) {
		super(player);
		this.player = player;
	}

	@Override
	public void kick(String reason) {
		player.kickPlayer(reason);
	}
}
