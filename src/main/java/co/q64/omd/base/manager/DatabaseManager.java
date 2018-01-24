package co.q64.omd.base.manager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.omd.base.annotation.Console;
import co.q64.omd.base.config.Reloadable;
import co.q64.omd.base.database.ConnectionPool;
import co.q64.omd.base.database.ConnectionPoolFactory;
import co.q64.omd.base.database.DatabaseConfigWrapper;
import co.q64.omd.base.database.DatabaseConfigWrapperFactory;
import co.q64.omd.base.objects.Mod;
import co.q64.omd.base.util.ChatUtil;
import co.q64.omd.base.util.Logger;
import co.q64.omd.base.util.ModContainer;
import co.q64.omd.base.util.PlayerSender;
import co.q64.omd.base.util.Sender;
import lombok.Getter;

@Singleton
public class DatabaseManager implements Reloadable {
	protected @Inject ConnectionPoolFactory connectionPoolFactory;
	protected @Inject DatabaseConfigWrapperFactory configFactory;
	protected @Inject ModContainer modContainer;
	protected @Inject Logger logger;
	protected @Inject ChatUtil chatUtil;

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
			pool.submit(() -> {
				for (UUID player : lock) {
					StringBuilder sb = new StringBuilder();
					List<Mod> mods = modContainer.getMods(player).stream().filter(mod -> mod.getLogin() > now - TimeUnit.DAYS.toMillis(30)).collect(Collectors.toList());
					for (Iterator<Mod> itr = mods.iterator(); itr.hasNext();) {
						Mod mod = itr.next();
						sb.append(mod.encode());
						if (itr.hasNext()) {
							sb.append("^");
						}
					}
				}
			});
		}, 3L, 3L, TimeUnit.SECONDS);
	}

	protected void onPlayerJoin(PlayerSender player) {

	}

	public void onModUpdate(UUID player) {
		pendingTransactions.add(player);
	}

	@Override
	@Inject
	public void reload(@Console Sender sender) {
		DatabaseConfigWrapper config = configFactory.create();
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
