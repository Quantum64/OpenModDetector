package co.q64.omd.spigot.inject;

import co.q64.omd.OpenModDetector;
import co.q64.omd.base.util.Logger;
import co.q64.omd.spigot.SpigotModDetector;
import co.q64.omd.spigot.util.SpigotLogger;
import dagger.Binds;
import dagger.Module;

@Module
public interface SpigotBinders {
	public @Binds OpenModDetector openModDetector(SpigotModDetector spigotModDetector);

	public @Binds Logger logger(SpigotLogger spigotLogger);
}
