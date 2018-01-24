package co.q64.omd.spigot.util;

import java.util.logging.Level;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.bukkit.plugin.Plugin;

import co.q64.omd.base.util.Logger;

@Singleton
public class SpigotLogger implements Logger {
	private static final String PREFIX = "[ModDetector] ";

	protected @Inject Plugin plugin;
	private java.util.logging.Logger logger;

	protected @Inject SpigotLogger() {}

	@Inject
	protected void init() {
		logger = plugin.getLogger();
	}

	@Override
	public void info(String message) {
		logger.info(PREFIX + message);
	}

	@Override
	public void error(String message) {
		logger.severe(PREFIX + message);
	}

	@Override
	public void error(String message, Throwable t) {
		logger.log(Level.SEVERE, PREFIX + message, t);
	}

	@Override
	public void warn(String message) {
		logger.warning(message);
	}

	@Override
	public void warn(String message, Throwable t) {
		logger.log(Level.WARNING, PREFIX + message, t);
	}
}
