package co.q64.omd.base.inject;

import co.q64.omd.base.util.ArgumentIteratorDefinition;
import co.q64.omd.base.util.ArgumentIteratorFactory;
import dagger.Binds;
import dagger.Module;

@Module
public interface ModDetectorBindings {
	public @Binds ArgumentIteratorDefinition getArgumentIteratorDefinition(ArgumentIteratorFactory argumentIteratorFactory);
}
