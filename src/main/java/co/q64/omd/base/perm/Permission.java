package co.q64.omd.base.perm;

import com.google.auto.factory.AutoFactory;
import com.google.auto.factory.Provided;

import co.q64.omd.base.annotation.BasePermissionNode;
import co.q64.omd.base.util.Sender;

@AutoFactory
public class Permission {
	private String permission;

	protected Permission(@Provided @BasePermissionNode String base, String node) {
		this.permission = base + node;
	}

	public String getPermission() {
		return permission;
	}

	public boolean has(Sender sender) {
		return sender.hasPermission(this);
	}
}
