package co.q64.omd.spigot.command;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import co.q64.omd.base.binders.ConstantBinders.ModFiltersCommandName;
import co.q64.omd.base.manager.ModManager;
import co.q64.omd.spigot.util.SpigotCommandSenderFactory;
import lombok.Getter;

@Singleton
public class SpigotListFiltersCommand implements SpigotCommand {
	protected @Inject ModManager modManager;
	protected @Inject SpigotCommandSenderFactory factory;
	protected @Inject @Getter @ModFiltersCommandName String name;

	protected @Inject SpigotListFiltersCommand() {}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String l, String[] args) {
		modManager.listFiltersCommand(factory.create(sender));
		return true;
	}
}
