package co.q64.omd.spigot.inject;

import javax.inject.Singleton;

import co.q64.omd.ModDetectorModule;
import co.q64.omd.OpenModDetector;
import dagger.Component;

@Singleton
@Component(modules = { ModDetectorModule.class, SpigotModule.class, SpigotBinders.class })
public interface SpigotComponent {
	public OpenModDetector getOpenModDetector();
}
