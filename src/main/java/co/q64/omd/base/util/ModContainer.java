package co.q64.omd.base.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.omd.base.objects.Mod;

@Singleton
public class ModContainer {
	protected @Inject ModContainer() {}

	private Map<UUID, List<Mod>> internal = new HashMap<UUID, List<Mod>>();

	public List<Mod> getMods(UUID id) {
		List<Mod> mods = internal.get(id);
		if (mods == null) {
			return Collections.emptyList();
		}
		return mods;
	}

	public void addMod(UUID id, Mod mod) {
		List<Mod> mods = internal.get(id);
		if (mods == null) {
			mods = new ArrayList<Mod>();
			internal.put(id, mods);
		}
		for (Iterator<Mod> itr = mods.iterator(); itr.hasNext();) {
			Mod current = itr.next();
			if (current.getName().equalsIgnoreCase(mod.getName())) {
				if ((mod.getVersion().length() <= current.getVersion().length()) || current.getLogin() > mod.getLogin() || current.isCurrent()) {
					return;
				}
				itr.remove();
			}
		}
		mods.add(mod);
	}
	
	public Map<UUID, List<Mod>> getMods() {
		return internal;
	}

	public void addMods(UUID id, Collection<Mod> mods) {
		for (Mod m : mods) {
			addMod(id, m);
		}
	}

	public void remove(UUID id) {
		internal.remove(id);
	}
}
