package co.q64.omd.spigot.inject;

import org.bukkit.event.Listener;

import co.q64.omd.spigot.listener.SpigotJoinListener;
import co.q64.omd.spigot.listener.SpigotQuitListener;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoSet;

@Module
public interface SpigotListeners {
	public @Binds @IntoSet Listener getJoinListener(SpigotJoinListener joinListener);

	public @Binds @IntoSet Listener getQuitListener(SpigotQuitListener quitListener);
}
