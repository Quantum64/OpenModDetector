package co.q64.omd.base.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.omd.base.manager.ConfigManager;
import co.q64.omd.base.util.ChatUtil;
import co.q64.omd.base.util.ConsoleEnhancedSenderFactory;
import co.q64.omd.base.util.Sender;

@Singleton
public class ConfigurationReloader {
	protected @Inject ConsoleEnhancedSenderFactory senderFactory;
	protected @Inject ConfigManager configManager;
	protected @Inject Set<Reloadable> reloadable;
	protected @Inject ChatUtil chatUtil;

	protected @Inject ConfigurationReloader() {}

	public void reload(Sender sender) {
		sender = senderFactory.create(sender);
		List<Reloadable> toReload = new ArrayList<Reloadable>(Arrays.asList(configManager));
		toReload.addAll(reloadable);
		for (Reloadable r : reloadable) {
			r.reload(senderFactory.create(sender));
		}
		sender.sendMessage(chatUtil.prefix() + "Open Mod Detector reloaded!");
	}
}
