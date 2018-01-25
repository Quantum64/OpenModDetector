package co.q64.omd.spigot.util;

import org.bukkit.command.CommandSender;

import com.google.auto.factory.AutoFactory;
import com.google.auto.factory.Provided;

import co.q64.omd.base.perm.Permission;
import co.q64.omd.base.util.ChatUtil;
import co.q64.omd.base.util.Sender;

@AutoFactory
public class SpigotCommandSender implements Sender {
	private CommandSender sender;
	private ChatUtil chatUtil;

	protected SpigotCommandSender(@Provided ChatUtil chatUtil, CommandSender sender) {
		this.chatUtil = chatUtil;
		this.sender = sender;
	}

	@Override
	public void sendFormatted(String message) {
		sender.sendMessage(chatUtil.prefix() + message);
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
