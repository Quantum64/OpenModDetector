package co.q64.omd.spigot.command;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import co.q64.omd.base.binders.ConstantBinders.ModListCommandName;
import co.q64.omd.base.manager.ModManager;
import co.q64.omd.spigot.util.SpigotCommandSenderFactory;
import lombok.Getter;

@Singleton
public class SpigotModListCommand implements SpigotCommand {
	protected @Inject ModManager modManager;
	protected @Inject SpigotCommandSenderFactory factory;
	protected @Inject @Getter @ModListCommandName String name;

	protected @Inject SpigotModListCommand() {}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String l, String[] args) {
		modManager.listMods(factory.create(sender), args);
		return true;
	}
}
