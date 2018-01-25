package co.q64.omd.base.util;

import com.google.auto.factory.AutoFactory;
import com.google.auto.factory.Provided;

import co.q64.omd.base.annotation.Console;
import co.q64.omd.base.perm.Permission;

@AutoFactory
public class ConsoleEnhancedSender implements Sender {
	private Sender[] senders;

	protected ConsoleEnhancedSender(@Provided @Console Sender console, Sender sender) {
		this.senders = new Sender[] { sender, console };
	}

	@Override
	public void sendMessage(String message) {
		for (Sender s : senders) {
			s.sendMessage(message);
		}
	}

	@Override
	public void sendFormatted(String message) {
		for (Sender s : senders) {
			s.sendFormatted(message);
		}
	}

	@Override
	public String getName() {
		return senders[0].getName();
	}

	@Override
	public boolean hasPermission(Permission permission) {
		return senders[0].hasPermission(permission);
	}
}
