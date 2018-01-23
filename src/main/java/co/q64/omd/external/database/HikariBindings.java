package co.q64.omd.external.database;

import co.q64.omd.base.database.ConnectionPoolFactory;
import dagger.Binds;
import dagger.Module;

@Module
public interface HikariBindings {
	public @Binds ConnectionPoolFactory bindConnectionPoolFactory(HikariConnectionPoolFactory hikariConnectionPoolFactory);
}
