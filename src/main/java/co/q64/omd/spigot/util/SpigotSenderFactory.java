package co.q64.omd.spigot.util;

import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import co.q64.omd.base.util.MockSender;
import co.q64.omd.base.util.PlayerSender;
import co.q64.omd.base.util.SenderFactory;

@Singleton
public class SpigotSenderFactory implements SenderFactory {
	protected @Inject SpigotPlayerSenderFactory factory;
	protected @Inject MockSender mock;
	
	protected SpigotSenderFactory() {}

	@Override
	public PlayerSender getSender(UUID id) {
		Player p = Bukkit.getPlayer(id);
		if(p == null) {
			return mock;
		}
		return factory.create(p);
	}
}
