package co.q64.omd.base.manager;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.omd.base.annotation.Console;
import co.q64.omd.base.database.ConnectionPool;
import co.q64.omd.base.database.ConnectionPoolFactory;
import co.q64.omd.base.database.DatabaseConfigWrapper;
import co.q64.omd.base.database.DatabaseConfigWrapperFactory;
import co.q64.omd.base.objects.Mod;
import co.q64.omd.base.util.Color;
import co.q64.omd.base.util.Logger;
import co.q64.omd.base.util.ModContainer;
import co.q64.omd.base.util.PlayerSender;
import co.q64.omd.base.util.Sender;
import lombok.Getter;

@Singleton
public class DatabaseManager {
	protected @Inject ConnectionPoolFactory connectionPoolFactory;
	protected @Inject DatabaseConfigWrapperFactory configFactory;
	protected @Inject ModContainer modContainer;
	protected @Inject Logger logger;

	private @Getter boolean useDatabase;
	private ExecutorService pool = Executors.newCachedThreadPool();
	private ConnectionPool connections;

	protected @Inject DatabaseManager() {}

	public void onPlayerJoin(PlayerSender player) {

	}

	public void onModUpdate(UUID player, Mod mod) {

	}

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
				sender.sendMessage(Color.RED + "Could not connect to database! Some features will be disabled.");
				useDatabase = false;
			}
		}
	}
}
