package co.q64.omd.base.manager;

import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.omd.base.detection.Detector;
import co.q64.omd.base.util.ModContainer;
import co.q64.omd.base.util.PlayerSender;

@Singleton
public class ModManager {
	protected @Inject Set<Detector> detectors;
	protected @Inject ModContainer container;

	protected @Inject ModManager() {}

	public void onPlayerJoin(PlayerSender player) {
		for (Detector detector : detectors) {
			detector.processJoin(player);
		}
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
