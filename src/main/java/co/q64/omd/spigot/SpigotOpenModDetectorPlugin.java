package co.q64.omd.spigot;

import org.bukkit.plugin.java.JavaPlugin;

import co.q64.omd.OpenModDetector;
import co.q64.omd.OpenModDetectorPlugin;
import co.q64.omd.base.inject.ModDetectorModule;
import co.q64.omd.spigot.inject.*;

public class SpigotOpenModDetectorPlugin extends JavaPlugin implements OpenModDetectorPlugin {
	private OpenModDetector modDetector;

	@Override
	public void onEnable() {
		//formatter:off
		this.modDetector = DaggerSpigotComponent.builder()
			.modDetectorModule(new ModDetectorModule())
			.spigotModule(new SpigotModule(this))
			.build().getOpenModDetector();
		//formatter:on
		modDetector.enable();
	}

	@Override
	public void onDisable() {
		modDetector.disable();
	}
}
