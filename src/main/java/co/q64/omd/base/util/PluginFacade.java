package co.q64.omd.base.util;

import java.io.File;

public interface PluginFacade {
	public File getDataFolder();

	public void saveResource(String name);
}
