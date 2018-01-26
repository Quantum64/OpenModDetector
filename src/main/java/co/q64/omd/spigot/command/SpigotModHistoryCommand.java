package co.q64.omd.spigot.command;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import co.q64.omd.base.binders.ConstantBinders.ModHistoryCommandName;
import co.q64.omd.base.manager.ModManager;
import co.q64.omd.spigot.util.SpigotCommandSenderFactory;
import lombok.Getter;

@Singleton
public class SpigotModHistoryCommand implements SpigotCommand {
	protected @Inject ModManager modManager;
	protected @Inject SpigotCommandSenderFactory factory;
	protected @Inject @Getter @ModHistoryCommandName String name;

	protected @Inject SpigotModHistoryCommand() {}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String l, String[] args) {
		modManager.modHistoryCommand(factory.create(sender), args);
		return true;
	}
}
