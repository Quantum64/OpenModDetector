package co.q64.omd.base.util;

import java.util.Collection;
import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.omd.base.manager.DatabaseManager;
import co.q64.omd.base.objects.Mod;

@Singleton
public class ModUpdater {
	protected @Inject DatabaseManager databaseManager;
	protected @Inject ModContainer container;

	protected @Inject ModUpdater() {}

	public void addMod(UUID id, Mod mod) {
		container.addMod(id, mod);
		databaseManager.onModUpdate(id);
	}

	public void addMods(UUID id, Collection<Mod> mods) {
		for (Mod m : mods) {
			addMod(id, m);
		}
	}
}
