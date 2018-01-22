package co.q64.omd.spigot.command;

import javax.inject.Inject;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import co.q64.omd.base.util.ArgumentIteratorFactory;

public class SpigotModListCommand implements CommandExecutor {
	protected @Inject ArgumentIteratorFactory argumentIteratorFactory;
	
	protected @Inject SpigotModListCommand() {}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String l, String[] args) {
		return true;
	}
}
