package co.q64.omd.base.util;

import java.util.UUID;

public interface SenderFactory {
	public PlayerSender getSender(UUID id);

	public PlayerSender getSender(String name);
}
