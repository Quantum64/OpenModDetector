package co.q64.omd.base.util.command;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ArgumentIteratorFactory {
	protected @Inject ArgumentIteratorFactory() {}

	public ArgumentIterator create(String[] args) {
		return new ArgumentIterator(args);
	}
}
