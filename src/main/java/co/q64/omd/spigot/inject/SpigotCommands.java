package co.q64.omd.spigot.inject;

import co.q64.omd.spigot.command.SpigotCommand;
import co.q64.omd.spigot.command.SpigotModListCommand;
import co.q64.omd.spigot.command.SpigotReloadCommand;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoSet;

@Module
public interface SpigotCommands {
	public @Binds @IntoSet SpigotCommand getModListCommand(SpigotModListCommand modListCommand);
	
	public @Binds @IntoSet SpigotCommand getReloadCommand(SpigotReloadCommand reloadCommand);
}
