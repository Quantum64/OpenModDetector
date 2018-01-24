package co.q64.omd.base.util;

import java.io.File;

public interface PluginFacade {
	public File getDataFolder();

	public void saveResource(String name);

	public void schedule(Runnable task, int ticks);

	public void sendPluginMessage(PlayerSender sender, String channel, byte[] message);
}
