package co.q64.omd.base.util;

import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.omd.base.perm.Permission;

@Singleton
public class MockSender implements PlayerSender {
	protected @Inject MockSender() {}

	@Override
	public String getName() {
		return "Offline";
	}

	@Override
	public boolean hasPermission(Permission permission) {
		return false;
	}

	@Override
	public UUID getUUID() {
		return UUID.randomUUID();
	}

	@Override
	public boolean isOnline() {
		return false;
	}

	@Override
	public void sendPluginMessage(String channel, byte[] message) {}

	@Override
	public void sendFormatted(String message) {}

	@Override
	public void kick(String reason) {}

	@Override
	public void sendMessage(String message) {}
}
