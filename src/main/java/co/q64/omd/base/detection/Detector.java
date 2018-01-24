package co.q64.omd.base.detection;

import co.q64.omd.base.util.PlayerSender;

public interface Detector {
	public default void processPluginMessage(PlayerSender player, String channel, byte[] message) {}

	public default void processJoin(PlayerSender player) {}
}
