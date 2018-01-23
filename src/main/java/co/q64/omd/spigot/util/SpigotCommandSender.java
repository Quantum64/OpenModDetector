package co.q64.omd.spigot.util;

import org.bukkit.command.CommandSender;

import com.google.auto.factory.AutoFactory;

import co.q64.omd.base.perm.Permission;
import co.q64.omd.base.util.Sender;

@AutoFactory
public class SpigotCommandSender implements Sender {
	private CommandSender sender;

	protected SpigotCommandSender(CommandSender sender) {
		this.sender = sender;
	}

	@Override
	public void sendMessage(String message) {
		sender.sendMessage(message);
	}

	@Override
	public String getName() {
		return sender.getName();
	}

	@Override
	public boolean hasPermission(Permission permission) {
		return sender.hasPermission(permission.getPermission());
	}
}
