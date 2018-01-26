package co.q64.omd.base.inject;

import co.q64.omd.base.blocker.Blocker;
import co.q64.omd.base.blocker.SchematicaBlocker;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoSet;

@Module
public interface Blockers {
	public @Binds @IntoSet Blocker getSchematicaBlocker(SchematicaBlocker schematicaBlocker);
}
