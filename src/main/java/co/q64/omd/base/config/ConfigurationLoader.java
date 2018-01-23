package co.q64.omd.base.config;

import java.io.File;
import java.util.Optional;

public interface ConfigurationLoader {
	public Optional<Configuration> load(File file);

	public String getFileExtension();
}
