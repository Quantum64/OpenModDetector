package co.q64.omd.base.inject;

import co.q64.omd.base.config.Reloadable;
import co.q64.omd.base.manager.DatabaseManager;
import co.q64.omd.base.util.ArgumentIteratorDefinition;
import co.q64.omd.base.util.ArgumentIteratorFactory;
import co.q64.omd.base.util.ChatUtil;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoSet;

@Module
public interface ModDetectorBindings {
	public @Binds ArgumentIteratorDefinition getArgumentIteratorDefinition(ArgumentIteratorFactory argumentIteratorFactory);

	public @Binds @IntoSet Reloadable getDatabaseManager(DatabaseManager databaseManager);

	public @Binds @IntoSet Reloadable getChatUtil(ChatUtil chatUtil);
}
