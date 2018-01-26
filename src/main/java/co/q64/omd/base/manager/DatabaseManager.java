package co.q64.omd.base.manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.omd.base.annotation.Console;
import co.q64.omd.base.config.Reloadable;
import co.q64.omd.base.database.ConnectionPool;
import co.q64.omd.base.database.ConnectionPoolFactory;
import co.q64.omd.base.database.DatabaseConfig;
import co.q64.omd.base.database.DatabaseConfigFactory;
import co.q64.omd.base.objects.Mod;
import co.q64.omd.base.objects.ModFactory;
import co.q64.omd.base.util.ChatUtil;
import co.q64.omd.base.util.Logger;
import co.q64.omd.base.util.ModContainer;
import co.q64.omd.base.util.PlayerSender;
import co.q64.omd.base.util.Sender;
import lombok.Getter;

@Singleton
public class DatabaseManager implements Reloadable {
	protected @Inject ConnectionPoolFactory connectionPoolFactory;
	protected @Inject DatabaseConfigFactory configFactory;
	protected @Inject ModContainer modContainer;
	protected @Inject Logger logger;
	protected @Inject ChatUtil chatUtil;
	protected @Inject ModFactory modFactory;

	private @Getter boolean useDatabase;
	private List<UUID> pendingTransactions = new CopyOnWriteArrayList<UUID>();
	private ScheduledExecutorService sceduler = Executors.newSingleThreadScheduledExecutor();
	private ExecutorService pool = Executors.newCachedThreadPool();
	private ConnectionPool connections;

	protected @Inject DatabaseManager() {
		final List<UUID> lock = new ArrayList<UUID>(pendingTransactions);
		final long now = System.currentTimeMillis();
		pendingTransactions.clear();
		sceduler.scheduleAtFixedRate(() -> {
			if (!useDatabase) {
				return;
			}
			pool.submit(() -> {
				for (UUID player : lock) {
					StringBuilder sb = new StringBuilder();
					List<Mod> mods = modContainer.getMods(player).stream().filter(mod -> mod.getLogin() > now - TimeUnit.DAYS.toMillis(30)).collect(Collectors.toList());
					for (Iterator<Mod> itr = mods.iterator(); itr.hasNext();) {
						Mod mod = itr.next();
						sb.append(mod.encode());
						if (itr.hasNext()) {
							sb.append(",");
						}
					}
					try {
						if (sb.length() > 0) {
							PreparedStatement statement = connections.getConnection().prepareStatement(DatabaseQueries.UPDATE);
							statement.setString(1, player.toString());
							statement.setString(2, sb.toString());
							statement.setString(3, sb.toString());
							statement.executeUpdate();
						} else {
							PreparedStatement statement = connections.getConnection().prepareStatement(DatabaseQueries.DELETE);
							statement.setString(1, player.toString());
							statement.executeUpdate();
						}
					} catch (SQLException e) {
						logger.error("Error updading player data!", e);
					}
				}
			});
		}, 3L, 3L, TimeUnit.SECONDS);
	}

	protected void onPlayerJoin(PlayerSender player) {
		if (!useDatabase) {
			return;
		}
		getData(player.getUUID(), mods -> modContainer.addMods(player.getUUID(), mods));
	}

	protected void onPlayerQuit(PlayerSender player) {
		if (!useDatabase) {
			return;
		}
		onModUpdate(player.getUUID());
	}

	public void getData(UUID player, Consumer<List<Mod>> callback) {
		pool.submit(() -> {
			try {
				PreparedStatement statement = connections.getConnection().prepareStatement(DatabaseQueries.GET);
				statement.setString(1, player.toString());
				ResultSet rs = statement.executeQuery();
				if (!rs.next()) {
					callback.accept(Collections.emptyList());
				}
				String mods = rs.getString(1);
				List<Mod> result = new ArrayList<Mod>();
				for (String mod : mods.split(Pattern.quote(","))) {
					if (mod.trim().isEmpty()) {
						continue;
					}
					result.add(modFactory.create(mod));
				}
				callback.accept(result);
			} catch (SQLException e) {
				logger.error("Error getting saved mod list!", e);
			}
		});
	}

	public void onModUpdate(UUID player) {
		if (!useDatabase) {
			return;
		}
		pendingTransactions.add(player);
	}

	@Override
	@Inject
	public void reload(@Console Sender sender) {
		DatabaseConfig config = configFactory.create();
		useDatabase = config.useDatabase();
		if (useDatabase) {
			String url = "jdbc:mysql://" + config.getHost() + ":" + config.getPort() + "/" + config.getDatabase();
			connections = connectionPoolFactory.create(url, config.getUsername(), config.getPassword());
			try {
				connections.getConnection();
			} catch (Exception e) {
				logger.error("Could not connect to database!", e);
				sender.sendMessage(chatUtil.prefix() + "Could not connect to database! Some features will be disabled.");
				useDatabase = false;
			}
		}
	}
}
