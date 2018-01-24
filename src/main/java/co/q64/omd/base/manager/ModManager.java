package co.q64.omd.base.manager;

import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.omd.base.detection.Detector;
import co.q64.omd.base.util.ArgumentIterator;
import co.q64.omd.base.util.ArgumentIteratorFactory;
import co.q64.omd.base.util.ModContainer;
import co.q64.omd.base.util.PlayerSender;
import co.q64.omd.base.util.PluginFacade;
import co.q64.omd.base.util.Sender;

@Singleton
public class ModManager {
	protected @Inject Set<Detector> detectors;
	protected @Inject ModContainer container;
	protected @Inject PluginFacade plugin;
	protected @Inject DatabaseManager databaseManager;
	protected @Inject ArgumentIteratorFactory iteratorFactory;

	protected @Inject ModManager() {}

	public void listMods(Sender sender, String[] argArray) {
		ArgumentIterator args = iteratorFactory.create(argArray);
		if (args.hasNext()) {

		} else {
			
		}
	}

	public void onPlayerJoin(PlayerSender player) {
		databaseManager.onPlayerJoin(player);
		plugin.schedule(() -> {
			for (Detector detector : detectors) {
				detector.processJoin(player);
			}
		}, 20);
	}

	public void onPlayerDisconnect(PlayerSender player) {
		container.remove(player.getUUID());
	}

	public void onPluginMessage(PlayerSender player, String channel, byte[] message) {
		for (Detector detector : detectors) {
			detector.processPluginMessage(player, channel, message);
		}
	}
}
