package co.q64.omd.base.manager;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.omd.base.binders.ConstantBinders.AllModsCommandName;
import co.q64.omd.base.binders.ConstantBinders.ModFiltersCommandName;
import co.q64.omd.base.binders.ConstantBinders.ModHistoryCommandName;
import co.q64.omd.base.binders.ConstantBinders.ModListCommandName;
import co.q64.omd.base.blocker.Blocker;
import co.q64.omd.base.detection.Detector;
import co.q64.omd.base.objects.Mod;
import co.q64.omd.base.perm.PermissionFactory;
import co.q64.omd.base.type.ModType;
import co.q64.omd.base.util.ArgumentIterator;
import co.q64.omd.base.util.ArgumentIteratorFactory;
import co.q64.omd.base.util.ChatUtil;
import co.q64.omd.base.util.ModContainer;
import co.q64.omd.base.util.PlayerSender;
import co.q64.omd.base.util.PluginFacade;
import co.q64.omd.base.util.Sender;
import co.q64.omd.base.util.SenderFactory;

@Singleton
public class ModManager {
	protected @Inject Set<Detector> detectors;
	protected @Inject Set<Blocker> blockers;
	protected @Inject ModContainer container;
	protected @Inject PluginFacade plugin;
	protected @Inject DatabaseManager databaseManager;
	protected @Inject ArgumentIteratorFactory iteratorFactory;
	protected @Inject SenderFactory factory;
	protected @Inject PermissionFactory permissions;
	protected @Inject ChatUtil chat;

	protected @Inject @ModListCommandName String modListCommand;
	protected @Inject @ModHistoryCommandName String modHistoryCommand;
	protected @Inject @ModFiltersCommandName String modFiltersCommand;
	protected @Inject @AllModsCommandName String modListAllCommand;

	protected @Inject ModManager() {}

	public void modListCommand(Sender sender, String[] argArray) {
		if (checkPermission(sender, modListCommand)) {
			return;
		}
		ArgumentIterator args = iteratorFactory.create(argArray);
		if (args.hasNext()) {
			String name = args.next();
			PlayerSender target = factory.getSender(name);
			if (!target.isOnline()) {
				sender.sendFormatted(name + " is not online! Try /" + modHistoryCommand + " " + name);
				return;
			}
			ModType filter = null;
			if (args.hasNext()) {
				filter = checkFilter(sender, args.next());
				if (filter == null) {
					return;
				}
			}
			displayModList(sender, target, filter);
			return;
		}
		sender.sendFormatted("Format: /" + modListCommand + " <player> [filter]");
	}

	public void modHistoryCommand(Sender sender, String[] argArray) {
		if (checkPermission(sender, modListCommand)) {
			return;
		}
		ArgumentIterator args = iteratorFactory.create(argArray);
		if (args.hasNext()) {
			String name = args.next();
			UUID id = null;
			ModType filter = null;
			if (args.hasNext()) {
				filter = checkFilter(sender, args.next());
				if (filter == null) {
					return;
				}
			}
			final ModType lock = filter;
			databaseManager.getData(id, mods -> displayModHistory(sender, name, mods, lock));
			return;
		}
		sender.sendFormatted("Format: /" + modHistoryCommand + " <player> [filter]");
	}

	public void modListAllCommand(Sender sender, String[] argArray) {
		if (checkPermission(sender, modListAllCommand)) {
			return;
		}
		ArgumentIterator args = iteratorFactory.create(argArray);
		Map<UUID, List<Mod>> mods = container.getMods();
		if (mods.size() == 0) {
			sender.sendFormatted("There are no online players using mods.");
			return;
		}
		ModType filter = null;
		if (args.hasNext()) {
			filter = checkFilter(sender, args.next());
			if (filter == null) {
				return;
			}
		}
		sender.sendFormatted("Mods of all online players:");
		for (Entry<UUID, List<Mod>> e : mods.entrySet()) {
			StringBuilder sb = new StringBuilder(chat.color());
			sb.append(factory.getSender(e.getKey()).getName());
			sb.append(": ");
			for (Iterator<Mod> itr = e.getValue().iterator(); itr.hasNext();) {
				Mod mod = itr.next();
				if (filter != null) {
					if (mod.getType() != filter) {
						continue;
					}
				}
				sb.append(chat.accent());
				sb.append(mod.getName());
				if (itr.hasNext()) {
					sb.append(chat.color());
					sb.append(", ");
				}
			}
			sender.sendMessage(sb.toString());
		}
	}

	public void listFiltersCommand(Sender sender) {
		if (checkPermission(sender, modFiltersCommand)) {
			return;
		}
		displayFilterTypes(sender);
	}

	public void onPlayerJoin(PlayerSender player) {
		databaseManager.onPlayerJoin(player);
		plugin.schedule(() -> {
			for (Detector detector : detectors) {
				detector.processJoin(player);
			}
			for (Blocker blocker : blockers) {
				blocker.processJoin(player);
			}
		}, 20);
	}

	public void onPlayerDisconnect(PlayerSender player) {
		databaseManager.onPlayerQuit(player);
		container.remove(player.getUUID());
	}

	public void onPluginMessage(PlayerSender player, String channel, byte[] message) {
		for (Detector detector : detectors) {
			detector.processPluginMessage(player, channel, message);
		}
	}

	private void displayModList(Sender sender, PlayerSender target, ModType filter) {
		List<Mod> mods = container.getMods(target.getUUID());
		if (mods.isEmpty()) {
			sender.sendFormatted("That player doesn't seem to be using any mods.");
			return;
		}
		String modsLine = "mods:";
		if (filter != null) {
			modsLine = filter.getName() + " " + modsLine;
		}
		sender.sendMessage(chat.accent() + target.getName() + chat.color() + "'s " + modsLine);
		for (Mod mod : mods) {
			StringBuilder sb = new StringBuilder(chat.color());
			sb.append(" - ");
			sb.append(chat.accent());
			sb.append(mod.getName());
			sb.append(" ");
			if (mod.getVersion().length() > 0) {
				sb.append(chat.color());
				sb.append("version ");
				sb.append(chat.accent());
				sb.append(mod.getVersion());
				sb.append(" ");
			}
			if (filter == null) {
				sb.append(chat.color());
				sb.append("type ");
				sb.append(chat.accent());
				sb.append(mod.getType().getName());
			}
		}
	}

	private void displayModHistory(Sender sender, String name, List<Mod> mods, ModType filter) {
		if (mods.isEmpty()) {
			sender.sendFormatted("That player hasn't logged in with mods in the past 30 days.");
			return;
		}
		String modsLine = "mod history:";
		if (filter != null) {
			modsLine = filter.getName() + " " + modsLine;
		}
		sender.sendMessage(chat.accent() + name + chat.color() + "'s " + modsLine);
		for (Mod mod : mods) {
			StringBuilder sb = new StringBuilder(chat.color());
			sb.append(" - ");
			sb.append(chat.accent());
			sb.append(mod.getName());
			sb.append(" ");
			if (mod.getVersion().length() > 0) {
				sb.append(chat.color());
				sb.append("version ");
				sb.append(chat.accent());
				sb.append(mod.getVersion());
				sb.append(" ");
			}
			if (filter == null) {
				sb.append(chat.color());
				sb.append("type ");
				sb.append(chat.accent());
				sb.append(mod.getType().getName());
			}
		}
	}

	private void displayFilterTypes(Sender sender) {
		sender.sendMessage(chat.color() + "Mod list filters:");
		for (ModType type : ModType.values()) {
			sender.sendMessage(chat.color() + " - " + chat.accent() + type.getName());
		}
	}

	private boolean checkPermission(Sender sender, String command) {
		boolean has = permissions.create(command).has(sender);
		if (!has) {
			sender.sendFormatted("You don't have permission to use that command!");
		}
		return has;
	}

	private ModType checkFilter(Sender sender, String filter) {
		Optional<ModType> opt = ModType.of(filter);
		if (!opt.isPresent()) {
			sender.sendFormatted("That isn't a valid filter type! Try /" + modFiltersCommand);
			return null;
		}
		return opt.get();
	}
}
