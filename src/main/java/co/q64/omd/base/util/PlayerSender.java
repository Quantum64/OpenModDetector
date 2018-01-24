package co.q64.omd.base.util;

import java.util.UUID;

public interface PlayerSender extends Sender {
	public void kick(String reason);

	public UUID getUUID();

	public void sendPluginMessage(String channel, byte[] message);
}
