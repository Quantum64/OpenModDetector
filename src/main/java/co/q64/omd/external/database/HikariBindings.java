package co.q64.omd.external.database;

import co.q64.omd.base.database.ConnectionPool;
import dagger.Binds;
import dagger.Module;

@Module
public interface HikariBindings {
	public @Binds ConnectionPool bindConnectionPool(HikariConnectionPool hikariConnectionPool);
}
