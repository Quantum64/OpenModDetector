package co.q64.omd.spigot.command;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import co.q64.omd.base.util.ArgumentIteratorFactory;

@Singleton
public class SpigotReloadCommand implements SpigotCommand {
	protected @Inject ArgumentIteratorFactory argumentIteratorFactory;

	protected @Inject SpigotReloadCommand() {}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String l, String[] args) {
		return true;
	}

	@Override
	public String getName() {
		return "moddetector";
	}
}
