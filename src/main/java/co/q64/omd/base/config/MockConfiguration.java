package co.q64.omd.base.config;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.google.auto.factory.AutoFactory;

@AutoFactory
public class MockConfiguration implements Configuration {

	protected MockConfiguration() {}
	
	@Override
	public Optional<Configuration> getSection(String key) {
		return Optional.of(new MockConfiguration());
	}

	@Override
	public int getInteger(String key, int def) {
		return def;
	}

	@Override
	public String getString(String key, String def) {
		return def;
	}

	@Override
	public List<String> getStringList(String key) {
		return Collections.emptyList();
	}

	@Override
	public boolean getBoolean(String key, boolean def) {
		return def;
	}

	@Override
	public float getFloat(String ket, float def) {
		return def;
	}
}
