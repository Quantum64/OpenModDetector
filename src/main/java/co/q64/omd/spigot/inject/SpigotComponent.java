package co.q64.omd.spigot.inject;

import javax.inject.Singleton;

import co.q64.omd.OpenModDetector;
import co.q64.omd.base.inject.ModDetectorBindings;
import co.q64.omd.base.inject.ModDetectorModule;
import co.q64.omd.external.database.HikariBindings;
import dagger.Component;

@Singleton
@Component(modules = { 
//formatter:off
		
		// Base Modules
		ModDetectorModule.class, 
		ModDetectorBindings.class, 
		
		// Spigot Modules
		SpigotModule.class, 
		SpigotBinders.class,
		
		// External Dependencies
		HikariBindings.class
//formatter:on
})
public interface SpigotComponent {
	public OpenModDetector getOpenModDetector();
}
