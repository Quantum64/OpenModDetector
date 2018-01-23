package co.q64.omd.base.config;

import java.util.List;
import java.util.Optional;

public interface Configuration {
	public Optional<Configuration> getSection(String key);

	public int getInteger(String key, int def);

	public String getString(String key, String def);

	public List<String> getStringList(String key);

	public boolean getBoolean(String key, boolean def);

	public float getFloat(String ket, float def);
}
