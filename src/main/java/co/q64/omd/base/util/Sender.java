package co.q64.omd.base.util;

import co.q64.omd.base.perm.Permission;

public interface Sender {
	public void sendMessage(String message);

	public void sendFormatted(String message);

	public String getName();

	public boolean hasPermission(Permission permission);
}
