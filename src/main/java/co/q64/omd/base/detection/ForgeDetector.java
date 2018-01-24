package co.q64.omd.base.detection;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.omd.base.util.ModUpdater;
import co.q64.omd.base.util.PlayerSender;

@Singleton
public class ForgeDetector implements Detector {
	protected @Inject ModUpdater updater;

	protected @Inject ForgeDetector() {}

	public void processPluginMessage(PlayerSender player, String channel, byte[] message) {

	}

	public void processJoin(PlayerSender player) {

	}
}
