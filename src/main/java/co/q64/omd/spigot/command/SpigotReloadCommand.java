package co.q64.omd.spigot.command;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import co.q64.omd.base.binders.ConstantBinders.ReloadCommandName;
import co.q64.omd.base.config.ConfigurationReloader;
import co.q64.omd.spigot.util.SpigotCommandSenderFactory;
import lombok.Getter;

@Singleton
public class SpigotReloadCommand implements SpigotCommand {
	protected @Inject @Getter @ReloadCommandName String name;
	protected @Inject ConfigurationReloader reloader;
	protected @Inject SpigotCommandSenderFactory factory;

	protected @Inject SpigotReloadCommand() {}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String l, String[] args) {
		reloader.reload(factory.create(sender));
		return true;
	}
}
