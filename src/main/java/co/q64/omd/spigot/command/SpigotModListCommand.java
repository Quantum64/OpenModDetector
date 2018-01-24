package co.q64.omd.spigot.command;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import co.q64.omd.base.manager.ModManager;
import co.q64.omd.spigot.util.SpigotCommandSenderFactory;

@Singleton
public class SpigotModListCommand implements SpigotCommand {
	protected @Inject ModManager modManager;
	protected @Inject SpigotCommandSenderFactory factory;

	protected @Inject SpigotModListCommand() {}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String l, String[] args) {
		modManager.listMods(factory.create(sender), args);
		return true;
	}

	@Override
	public String getName() {
		return "modlist";
	}
}
