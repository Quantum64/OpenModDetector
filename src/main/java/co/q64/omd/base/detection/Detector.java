package co.q64.omd.base.detection;

import java.util.Collections;
import java.util.List;

import co.q64.omd.base.util.PlayerSender;

public interface Detector {
	public default void processPluginMessage(PlayerSender player, String channel, byte[] message) {}

	public default void processJoin(PlayerSender player) {}

	public default List<String> getIncomingChannels() {
		return Collections.emptyList();
	}

	public default List<String> getOutgoingChannels() {
		return Collections.emptyList();
	}
}
