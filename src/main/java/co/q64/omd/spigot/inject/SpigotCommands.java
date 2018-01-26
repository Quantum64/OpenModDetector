package co.q64.omd.spigot.inject;

import co.q64.omd.spigot.command.SpigotCommand;
import co.q64.omd.spigot.command.SpigotListFiltersCommand;
import co.q64.omd.spigot.command.SpigotModHistoryCommand;
import co.q64.omd.spigot.command.SpigotModListAllCommand;
import co.q64.omd.spigot.command.SpigotModListCommand;
import co.q64.omd.spigot.command.SpigotReloadCommand;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoSet;

@Module
public interface SpigotCommands {
	//formatter:off
	public @Binds @IntoSet SpigotCommand getModListCommand(SpigotModListCommand modListCommand);
	public @Binds @IntoSet SpigotCommand getModListAllCommand(SpigotModListAllCommand modListAllCommand);
	public @Binds @IntoSet SpigotCommand getModHistoryCommand(SpigotModHistoryCommand modHistoryCommand);
	public @Binds @IntoSet SpigotCommand getListFiltersCommand(SpigotListFiltersCommand listFiltersCommand);
	public @Binds @IntoSet SpigotCommand getReloadCommand(SpigotReloadCommand reloadCommand);
	//formatter:on
}
