package co.q64.omd.base.util;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.omd.base.perm.Permission;

@Singleton
public class MockSender implements PlayerSender {
	protected @Inject MockSender() {}

	@Override
	public void sendMessage(String message) {}

	@Override
	public String getName() {
		return "Offline";
	}

	@Override
	public void kick(String reason) {}

	@Override
	public boolean hasPermission(Permission permission) {
		return false;
	}
}
