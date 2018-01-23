package co.q64.omd.base.perm;

import com.google.auto.factory.AutoFactory;
import com.google.auto.factory.Provided;

import co.q64.omd.base.annotation.BasePermissionNode;

@AutoFactory
public class Permission {
	private String permission;

	protected Permission(@Provided @BasePermissionNode String base, String node) {
		this.permission = base + node;
	}

	public String getPermission() {
		return permission;
	}
}
