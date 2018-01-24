package co.q64.omd.spigot;

import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.bukkit.Server;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import co.q64.omd.OpenModDetector;
import co.q64.omd.base.detection.Detector;
import co.q64.omd.spigot.command.SpigotCommand;
import co.q64.omd.spigot.listener.SpigotPluginMessageListener;

@Singleton
public class SpigotModDetector implements OpenModDetector {
	protected @Inject JavaPlugin plugin;
	protected @Inject Set<SpigotCommand> commands;
	protected @Inject Set<Listener> listeners;
	protected @Inject PluginManager pm;
	protected @Inject SpigotPluginMessageListener pmListener;
	protected @Inject Server server;
	protected @Inject Set<Detector> detectors;

	protected @Inject SpigotModDetector() {}

	@Override
	public void enable() {
		for (SpigotCommand command : commands) {
			plugin.getCommand(command.getName()).setExecutor(command);
		}

		for (Listener listener : listeners) {
			pm.registerEvents(listener, plugin);
		}

		for (Detector detector : detectors) {
			for (String incoming : detector.getIncomingChannels()) {
				server.getMessenger().registerIncomingPluginChannel(plugin, incoming, pmListener);
			}
			for (String outgoing : detector.getOutgoingChannels()) {
				server.getMessenger().registerOutgoingPluginChannel(plugin, outgoing);
			}
		}
	}

	@Override
	public void disable() {

	}
}
